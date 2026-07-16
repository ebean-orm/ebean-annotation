package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Register one or more types whose public conversion methods should be applied automatically,
 * by exact (source type, target type) match, to any DTO property with no explicit per-property
 * {@link DtoConvert}.
 * <p>
 * Used with generated entity-to-DTO graph mapping (see {@code query.mapTo(SomeDto.class)}).
 * Declared on a neutral holder - typically a {@code package-info.java} - the same convention as
 * {@link DtoMapping}.
 * <p>
 * {@code @DtoConvert} alone requires repeating the same annotation on every property that needs
 * the same conversion - tedious when one conversion (e.g. {@code Instant -> Calendar}, or an enum
 * {@code <-> String}) recurs across many otherwise-unrelated properties on a target. Rather than
 * annotate each property individually, register the class(es) declaring those conversions once:
 * <pre>{@code
 * @DtoConverters({DateConversions.class, EnumConversions.class})
 * package org.example.dto;
 * }</pre>
 * Every public, single-parameter, non-{@code void}-returning method declared directly on a
 * registered type is indexed by its exact parameter type and return type. Whenever a DTO
 * property's resolved source value type doesn't already exactly match the target property's
 * type, and that property carries no explicit {@link DtoConvert}, the generator looks up the
 * (source type, target type) pair in this index and wires the matching method in automatically -
 * dispatched the same way as {@link DtoConvert} (a direct static call for a {@code static}
 * method, or an instance resolved via {@code io.ebean.DtoConverterManager} for an instance
 * method needing a real dependency).
 * <p>
 * Matching is by <b>exact</b> type equality on both the parameter and return type - no
 * widening/supertype matching - so which method applies (if any) stays fully predictable. An
 * explicit per-property {@link DtoConvert} always takes precedence and is never overridden by a
 * type-pair default. Ebean itself ships no built-in conversions (e.g. no implicit
 * {@code Enum.valueOf}/{@code .name()}) - the registered method fully owns its own semantics
 * (e.g. returning {@code null} rather than throwing on an unrecognised enum value).
 * <p>
 * This is purely a compile-time / codegen-time hint - it carries no runtime footprint (source
 * retention).
 */
@Target({ElementType.PACKAGE, ElementType.MODULE})
@Retention(RetentionPolicy.CLASS)
public @interface DtoConverters {

  /**
   * The type(s) declaring conversion methods to index by (parameter type, return type) - an
   * arbitrary app-defined shape, no ebean-mandated interface.
   */
  Class<?>[] value();
}
