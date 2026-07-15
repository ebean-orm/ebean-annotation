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
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoPath {

  /**
   * The source property path to map from, using dot-notation (e.g. {@code "billingAddress.line1"}).
   */
  String value();
}
