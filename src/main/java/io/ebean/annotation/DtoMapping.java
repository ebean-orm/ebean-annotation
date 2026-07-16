package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Register a source entity type and a target DTO type as a pair for which a DTO graph mapper
 * should be generated (see {@code query.mapTo(SomeDto.class)}).
 * <p>
 * Declared on a neutral holder - typically a {@code package-info.java} - rather than on the DTO
 * or the entity itself. This matters because:
 * <ul>
 *   <li>The DTO type is often owned/generated elsewhere (e.g. from an OpenAPI spec) and shouldn't
 *   need to know about, or be annotated with, an internal persistence/entity type.</li>
 *   <li>One entity may be the source for several different DTOs (e.g. a summary vs. a detail
 *   view), and one entity/DTO pair may need re-registering from multiple consuming modules.</li>
 * </ul>
 * <pre>{@code
 * @DtoMapping(source = Customer.class, target = CustomerDto.class)
 * @DtoMapping(source = Contact.class, target = ContactDto.class)
 * package org.example.dto;
 * }</pre>
 * <p>
 * Purely a compile-time / codegen-time trigger - never needed at runtime, so this annotation
 * itself carries no runtime footprint (source retention).
 */
@Target({ElementType.PACKAGE, ElementType.MODULE})
@Retention(RetentionPolicy.SOURCE)
@Repeatable(DtoMapping.List.class)
public @interface DtoMapping {

  /**
   * The source entity (or embeddable) type to map from.
   */
  Class<?> source();

  /**
   * The target DTO type to map to.
   */
  Class<?> target();

  /**
   * Override the package the generated mapper is written to.
   * <p>
   * Defaults to the target DTO's own package - unless the source and/or target type belongs to a
   * different module than the one being processed, in which case the generated mapper is placed
   * in a package derived from the processing module's own name instead, to avoid a Java module
   * "split package" violation (the same fallback {@code avaje-jsonb} uses for
   * {@code @Json.Import} of external types).
   */
  String mapperPackage() default "";

  /**
   * Override the simple class name of the generated mapper.
   * <p>
   * Defaults to the target DTO's own simple name suffixed with {@code Mapper} (e.g. a mapping
   * targeting {@code Fleet} generates {@code FleetMapper}). Set this when that default name would
   * clash with an existing hand-written class of the same name (e.g. a legacy mapper still in use
   * elsewhere that can't be renamed/removed yet) - the generated mapper can then be given a
   * distinct name (and, if needed, paired with {@link #mapperPackage()} too) so both classes can
   * coexist.
   */
  String mapperName() default "";

  /**
   * Name this mapping as a named variant of an already-registered {@code (source, target)} pair,
   * rather than the primary/base mapping.
   * <p>
   * The same {@code (source, target)} pair may be declared more than once - one base declaration
   * (leaving {@code name()} empty) plus any number of named variants - all generated into a
   * single shared mapper class (one class per target, not one per variant). Each variant excludes
   * one or more nested {@code ToOne}/{@code ToMany} DTO properties (see {@link #exclude()}) from
   * both its own {@code fetchGroup} and its mapped output, letting the same target DTO type be
   * populated in more than one shape (e.g. with vs. without an expensive nested collection)
   * without generating a second target type.
   * <p>
   * The generated mapper exposes each variant as a same-named method returning its own
   * {@code DtoMapper<SOURCE, TARGET>} view, e.g. {@code name = "noFleets"} generates a
   * {@code noFleets()} accessor method - pass its result to
   * {@code query.mapTo(Target.class, mapper.noFleets())}.
   */
  String name() default "";

  /**
   * Nested {@code ToOne}/{@code ToMany} DTO property names (declared field names on the target,
   * not source paths), or non-primitive scalar property names (e.g. a {@code @DtoConvert}-backed
   * {@code List} property with no registered nested DTO mapping of its own), to exclude from this
   * named variant - only meaningful when {@link #name()} is non-empty. Excluded properties are
   * omitted from this variant's {@code fetchGroup} (no fetch/join/select is issued for them) and
   * mapped to {@code null} (a single-valued property) or an empty list (a {@code List}-typed
   * property) in this variant's output, rather than being populated from the source graph.
   * <p>
   * A primitive-typed scalar property cannot be excluded - there's no type-safe "absent" value
   * for it.
   */
  String[] exclude() default {};

  /**
   * Controls whether the generated mapper constructs the target DTO via a positional constructor
   * call or via a detected builder (a static {@code Target.builder()} factory returning a type
   * with fluent per-property setters and a {@code build()} method - the shape
   * {@code avaje-recordbuilder}'s {@code @RecordBuilder} generates).
   */
  Builder builder() default Builder.AUTO;

  /**
   * Target construction strategy - see {@link #builder()}.
   */
  enum Builder {
    /**
     * Use a detected builder when the target has more than a threshold number of properties
     * (default 5), falling back to a positional constructor otherwise.
     */
    AUTO,
    /**
     * Always use the detected builder - a codegen-time error if the target has no builder
     * matching the required shape.
     */
    ALWAYS,
    /**
     * Always use a positional constructor, even when the target has a usable builder.
     */
    NEVER
  }

  /**
   * Container annotation allowing {@code @DtoMapping} to be repeated on the same element.
   */
  @Target({ElementType.PACKAGE, ElementType.MODULE})
  @Retention(RetentionPolicy.SOURCE)
  @interface List {

    DtoMapping[] value();
  }
}
