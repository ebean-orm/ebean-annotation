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
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoRef {
}
