package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assign to a property to be based on a logical formula using property paths.
 * <p>
 * Unlike {@link Formula} which requires physical SQL with explicit table aliases and
 * JOIN clauses, {@code @Formula2} accepts a logical expression using dot-notation
 * property paths. Ebean automatically resolves the required JOIN clauses and
 * translates the paths to the appropriate table aliases at query time.
 * </p>
 * <pre>{@code
 * // Ebean automatically joins to the 'parent' association
 * @Formula2("coalesce(familyName, parent.familyName)")
 * private String effectiveFamilyName;
 *
 * // Multi-level path: Ebean joins parent and parent.parent
 * @Formula2("coalesce(familyName, parent.familyName, parent.parent.familyName)")
 * private String deepEffectiveFamilyName;
 * }</pre>
 * <p>
 * The expression supports any SQL function whose arguments are logical property
 * paths. The required JOIN clauses are added automatically when this property
 * is included in a query.
 * </p>
 * <p>
 * Any property based on a formula becomes a read only property.
 * </p>
 * <p>
 * By default this formula property <b>is included</b> in queries (just like a normal
 * mapped property, and consistent with {@link Formula}). Note that because a path based
 * formula automatically adds the JOIN clauses it needs, every query that includes the
 * property by default also pays the cost of those joins.
 * </p>
 * <p>
 * Combine {@code @Formula2} with the {@code @Transient} annotation to make the property
 * opt-in. The effect of {@code @Transient} is that the formula is <b>NOT</b> included in
 * queries by default - it must be explicitly included via {@code Query.select()} or
 * {@code Query.fetch()}. This is useful when the formula (or the joins it requires) is
 * relatively expensive and you only want it included when explicitly requested.
 * </p>
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Formula2 {

  /**
   * The logical expression using property paths.
   * <p>
   * For example: {@code "coalesce(familyName, parent.familyName)"}
   * </p>
   */
  String value();

  /**
   * Optionally restrict this formula to specific database platforms.
   */
  Platform[] platforms() default {};
}
