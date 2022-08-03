package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify how a foreign key constraint should be defined.
 * <p>
 * We can specify if a constraint should not be defined at all or control the
 * onDelete and onUpdate modes used.
 * <p>
 * <h3>Example: On delete cascade</h3>
 * <pre>{@code
 *
 * @DbForeignKey(onDelete = ConstraintMode.CASCADE)
 * @ManyToOne
 * RelatedBean parent;
 *
 * }</pre>
 * <p>
 * <h3>Example: No foreign key</h3>
 * <pre>{@code
 *
 * // No FK Constraint
 * @DbForeignKey(noConstraint=true)
 * @ManyToOne
 * RelatedBean parent;
 *
 * }</pre>
 * <p>
 * <p>
 * <h3>Example: On delete set null</h3>
 * <pre>{@code
 *
 * @DbForeignKey(onDelete = ConstraintMode.SET_NULL)
 * @ManyToOne
 * RelatedBean parent;
 *
 * }</pre>
 * <p>
 * <p>
 * <h3>Example: Allow inconsistency:</h3>
 * <pre>{@code
 * // disable foreign keys and force ebean to use left joins
 * @DbForeignKey(noConstraint=true, assumeConsistency=false)
 * // the reference column in the database will be NOT NULL
 * @ManyToOne(optional=false)
 * RelatedBean parent;
 * }</pre>
 * To create such a bean, 'parent' must not be null (because of 'optional=false').
 * There is no FK constraint, that enforces that 'parent' exists, so you can even use
 * a non existent (or not yet existent) reference bean with a particular ID and create.
 * that bean in a later step.
 * <p>
 * As 'assumeConsistency=false' Ebean will now use LEFT JOINs instead  of INNER JOINS,
 * so that the query result wouldn't skip entries if a non-existent relation is involved.
 * <br>
 * Note: In this case, the 'parent' may not be <code>null</code>, but you'll
 * get an <code>EntityNotFoundException</code> when accessing properties of 'parent'
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbForeignKey {

  /**
   * Specify the onDelete mode. Typically will default to RESTRICT (aka No Action).
   */
  ConstraintMode onDelete() default ConstraintMode.RESTRICT;

  /**
   * Do NOT change this - seriously, don't do it.
   * <p>
   * Your primary keys should never change by design. We should orientate the design to
   * support primary keys that change (and instead find better non mutating primary keys).
   * Oracle go to the point of actively not supporting "on update" for this reason.
   * </p>
   * <p>
   * So yes, we can specify the onUpdate mode but I don't expect anyone to change this.
   * </p>
   */
  ConstraintMode onUpdate() default ConstraintMode.RESTRICT;

  /**
   * Set to true when we do not wish any foreign key constraint to be created.
   * (either for performance reasons, or data may be inconsistent)
   * When this is set to true the onDelete and onUpdate have no effect.
   * <p>
   * Note: This setting controls only ddl-generation. By default, we still assume,
   * that the data is consistent and we may use INNER JOINs.
   */
  boolean noConstraint() default false;

  /**
   * Should ebean assume, that the data is consistent (default) or not.
   * If this is set to false, LEFT JOIN is always used in queries, because
   * we cannot guarantee, that we have a matching join partner.
   * <p>
   * Note: This setting controls only sql-generation (left join vs. join).
   * You may need <code>noConstraint = true</code> in most cases.
   */
  boolean assumeConsistency() default true;

  /**
   * Set to true when we do not wish an index to be created on the foreign key column(s).
   */
  boolean noIndex() default false;
}
