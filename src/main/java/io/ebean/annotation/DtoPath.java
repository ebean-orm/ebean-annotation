package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a DTO field or accessor to be populated from an entity property path other than its own
 * name, or from a nested/flattened path on the source entity graph.
 * <p>
 * Used with generated entity-to-DTO graph mapping (see {@code query.mapTo(SomeDto.class)}). By
 * default a DTO property is matched to the source entity property (or nested DTO) of the same
 * name. {@code @DtoPath} overrides that default, allowing the DTO property to be renamed and/or
 * flattened from a nested path:
 * <pre>{@code
 * public class CustomerDto {
 *   Integer id;
 *   String name;
 *
 *   @DtoPath("billingAddress.line1")
 *   String billingLine1;   // flattened from a nested ToOne relationship
 * }
 * }</pre>
 * <p>
 * This is purely a compile-time / codegen-time hint consumed when generating the DTO mapper and
 * deriving the query's fetch spec - the DTO itself remains a plain, framework-free type with no
 * runtime dependency on this annotation.
 * <p>
 * When the path traverses an intermediate relation that can be {@code null} (e.g. {@code
 * billingAddress} above may itself be {@code null}) and the DTO field's type is a Java primitive
 * (e.g. {@code long}, {@code int}, {@code boolean}), the generated mapper defaults the value to
 * the primitive's zero-equivalent ({@code 0}/{@code false}/etc.) rather than throwing - set
 * {@link #failOnNull()} to {@code true} to instead throw a clear exception when that happens.
 * <p>
 * Every segment of the path must name a real, fetchable Ebean bean property (one with a backing
 * field) - if a segment is instead a computed/derived getter (e.g. a hand-written method deriving
 * a value from other properties, with no backing field of its own), its own data dependencies
 * can't be inferred from the path alone, so {@link #requires()} must explicitly name the real
 * entity paths that need to be fetched for it to execute safely without triggering a lazy load:
 * <pre>{@code
 * @DtoPath(value = "currentMachine.organisationMachine.registrationPlate",
 *   requires = "currentMachine.organisationMachines")
 * String machineLabel;   // getOrganisationMachine() derives its result from organisationMachines
 * }</pre>
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoPath {

  /**
   * The source property path to map from, using dot-notation (e.g. {@code "billingAddress.line1"}).
   */
  String value();

  /**
   * For a primitive-typed DTO field whose path traverses a nullable intermediate relation, set to
   * {@code true} to throw a clear exception when that relation is {@code null} at runtime, rather
   * than the default of silently defaulting to the primitive's zero-equivalent value.
   * <p>
   * Has no effect for non-primitive (reference-typed) DTO fields - {@code null} is always a valid
   * result for those regardless of this setting.
   */
  boolean failOnNull() default false;

  /**
   * Real entity paths (dot-notation, same convention as {@link #value()}) that must be fetched to
   * support a computed/derived getter segment within {@link #value()} - required whenever a
   * segment of the path has no backing field, since its data dependencies can't otherwise be
   * inferred. Ignored (and unnecessary) when every segment names a real, fetchable property.
   * <p>
   * If the computed getter genuinely needs nothing extra fetched (e.g. it only touches properties
   * already guaranteed to be selected), set {@code requires = {}} explicitly to confirm that - as
   * opposed to omitting {@code requires} entirely, which is treated as "not yet considered" and
   * fails the build with a clear compile error.
   */
  String[] requires() default {};
}
