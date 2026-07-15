package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Overlay {@link DtoPath}/{@link DtoRef}/{@link DtoConvert} annotations onto a DTO type that
 * cannot be annotated directly - typically because it's generated elsewhere (e.g. from an
 * OpenAPI spec) and regenerated on every build.
 * <p>
 * Declared on a companion interface (or class) whose methods mirror the target DTO's property
 * names; the querybean-generator processor matches each mixin method to the corresponding
 * property on {@link #value()} by name, and applies whichever of {@code @DtoPath}/{@code @DtoRef}/
 * {@code @DtoConvert} is present on the mixin method as if it were declared on the target
 * property itself. Mirrors avaje-jsonb's {@code @Json.MixIn} mechanism, which solves the exact
 * same "can't annotate a generated/unowned type" problem.
 * <pre>{@code
 * @DtoMixin(Driver.class)
 * interface DriverDtoMixin {
 *
 *   @DtoPath("encryptedDriverId")
 *   @DtoConvert(value = DriverConversions.class, method = "decrypt")
 *   String driverId();
 * }
 * }</pre>
 * <p>
 * This is purely a compile-time / codegen-time hint - the mixin type itself is never
 * instantiated and carries no runtime footprint.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface DtoMixin {

  /**
   * The real DTO type this mixin overlays annotations onto.
   */
  Class<?> value();
}
