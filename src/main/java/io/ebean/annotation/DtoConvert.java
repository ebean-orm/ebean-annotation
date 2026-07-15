package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a DTO field or accessor to be populated via a custom conversion method rather than a
 * plain getter copy.
 * <p>
 * Used with generated entity-to-DTO graph mapping (see {@code query.mapTo(SomeDto.class)}).
 * Combinable with {@link DtoPath} when the source value also needs a path/rename override.
 * <p>
 * The referenced {@link #method()} on {@link #value()} is resolved at codegen time and
 * dispatched one of two ways:
 * <ul>
 *   <li><b>Static method</b> - the generated mapper emits a direct static call
 *   ({@code ConverterType.method(source.getX())}). No registration required - intended for
 *   common, reusable, dependency-free scalar coercions (e.g. {@code short} to {@code boolean},
 *   an enum to/from a {@code String}) that may apply across many unrelated entity/DTO pairs.</li>
 *   <li><b>Instance method</b> - the generated mapper resolves an instance of {@link #value()}
 *   via {@code io.ebean.DtoConverterManager.get(ConverterType.class)} once (wired as a
 *   constructor parameter/field, the same shape as nested-mapper constructor injection), then
 *   calls {@code conversions.method(source.getX())}. Intended for conversions that need a real
 *   dependency (e.g. a cipher) - the instance must be registered via
 *   {@code DtoConverterManager.put(...)} before the {@code Database} is built.</li>
 * </ul>
 * <pre>{@code
 * public record Driver(
 *   ...
 *   @DtoPath("encryptedDriverId")
 *   @DtoConvert(value = DriverConversions.class, method = "decrypt")
 *   String driverId,
 *   ...
 * ) { }
 * }</pre>
 * <p>
 * This is purely a compile-time / codegen-time hint - the DTO itself remains a plain,
 * framework-free type with no runtime dependency on this annotation.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoConvert {

  /**
   * The type declaring the conversion method - an arbitrary app-defined shape, no ebean-mandated
   * interface.
   */
  Class<?> value();

  /**
   * The name of the conversion method on {@link #value()} to invoke, taking the source property
   * value and returning the converted DTO property value.
   */
  String method();
}
