package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a DTO property as an id-only (or otherwise shallow) reference back to an already-visited
 * type in the DTO graph, deliberately breaking what would otherwise be a cycle.
 * <p>
 * Used with generated entity-to-DTO graph mapping (see {@code query.mapTo(SomeDto.class)}). The
 * DTO graph derived from a set of DTO types must form a DAG - generation fails at codegen time if
 * it doesn't. {@code @DtoRef} is the explicit escape hatch for an intentional back-reference,
 * e.g. a {@code Contact} DTO referencing its parent {@code Customer} by id only rather than
 * re-embedding the full {@code CustomerDto} (which would recreate the
 * {@code Customer -> Contact -> Customer} cycle):
 * <pre>{@code
 * public class ContactDto {
 *   int id;
 *   String firstName;
 *   String lastName;
 *
 *   @DtoRef
 *   Integer customerId;   // id-only back-reference, no CustomerDto re-embedded
 * }
 * }</pre>
 * <p>
 * This is purely a compile-time / codegen-time hint - the DTO itself remains a plain,
 * framework-free type with no runtime dependency on this annotation.
 * <p>
 * The association name (derived by stripping the {@code Id} suffix off the field/accessor name,
 * e.g. {@code customerId} -> {@code customer}) must name a real, fetchable Ebean relation (one
 * with a backing field) - if it's instead a computed/derived getter (e.g. a hand-written method
 * picking one entry out of a collection, with no backing field of its own), its own data
 * dependencies can't be inferred from the name alone, so {@link #requires()} must explicitly name
 * the real entity paths that need to be fetched for it to execute safely without triggering a lazy
 * load:
 * <pre>{@code
 * @DtoRef(requires = "contacts")
 * Integer primaryContactId;   // getPrimaryContact() derives its result from contacts
 * }</pre>
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoRef {

  /**
   * Real entity paths (dot-notation) that must be fetched to support a computed/derived
   * association getter - required whenever the association name has no backing field, since its
   * data dependencies can't otherwise be inferred. Ignored (and unnecessary) when the association
   * names a real, fetchable relation.
   * <p>
   * If the computed getter genuinely needs nothing extra fetched, set {@code requires = {}}
   * explicitly to confirm that - as opposed to omitting {@code requires} entirely, which is
   * treated as "not yet considered" and fails the build with a clear compile error.
   */
  String[] requires() default {};
}
