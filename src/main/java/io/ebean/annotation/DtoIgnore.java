package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a DTO field or accessor as permanently unmapped - always given its type's empty default
 * ({@code null}, or {@code List.of()} for a {@code java.util.List}-typed property) rather than
 * populated from the source entity, in every mapping (base and every named variant alike).
 * <p>
 * Used with generated entity-to-DTO graph mapping (see {@code query.mapTo(SomeDto.class)}) for a
 * DTO property that can never be derived from the registered source entity at all - typically
 * because it's populated later, by application code, from an entirely different source (e.g. a
 * separate ad-hoc query, a service call, or a value only known post-construction) rather than a
 * plain getter or entity relation:
 * <pre>{@code
 * @DtoMixin(Fleet.class)
 * interface FleetMixin {
 *   @DtoIgnore
 *   List<OrgMachineSummary> assignedMachines();
 *   @DtoIgnore
 *   List<DriverSummary> assignedDrivers();
 * }
 * }</pre>
 * <p>
 * When the target uses builder-based construction (see {@code @DtoMapping#builder()}), the
 * generated mapper additionally exposes a {@code mapToBuilder(source)} method returning the
 * target's builder one step before its final {@code build()} call, so callers can set an
 * {@code @DtoIgnore} property's real value (typically sourced from another query/service call
 * entirely outside this mapper's own fetch graph) before finishing construction:
 * <pre>{@code
 * FleetBuilder b = mapper.mapToBuilder(cFleet);
 * if (withMachines) b.assignedMachines(loadAssignedMachines(cFleet));
 * Fleet fleet = b.build();
 * }</pre>
 * <p>
 * Distinct from a named {@code @DtoMapping(name = ..., exclude = ...)} variant, which
 * conditionally omits a property that the base mapping otherwise does populate -
 * {@code @DtoIgnore} is unconditional, applying to every variant including the base, because the
 * property can never be populated from the source at all, under any circumstance.
 * <p>
 * A primitive-typed property cannot be marked {@code @DtoIgnore} - there's no type-safe "absent"
 * value for it.
 * <p>
 * This is purely a compile-time / codegen-time hint - the DTO itself remains a plain,
 * framework-free type with no runtime dependency on this annotation.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
public @interface DtoIgnore {
}
