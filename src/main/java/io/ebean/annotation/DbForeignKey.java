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
 * <h3>Example: On delete set null and on update cascade</h3>
 * <pre>{@code
 *
 * @DbForeignKey(onDelete = ConstraintMode.SET_NULL, onUpdate = ConstraintMode.CASCADE)
 * @ManyToOne
 * RelatedBean parent;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbForeignKey {

	/**
	 * Specify the onDelete mode. Typically will default to RESTRICT (aka No Action).
	 */
	ConstraintMode onDelete() default ConstraintMode.GLOBAL_DEFAULT;

	/**
	 * Specify the onUpdate mode. Typically will default to RESTRICT (aka No Action).
	 */
	ConstraintMode onUpdate() default ConstraintMode.GLOBAL_DEFAULT;

	/**
	 * Set to true when we do not wish any foreign key constraint to be created.
	 * When this is set to true the onDelete and onUpdate have no effect.
	 */
	boolean noConstraint() default false;

	/**
	 * Set to true when we do not wish an index to be created on the foreign key column(s).
	 */
	boolean noIndex() default false;
}
