package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a property to be an SUM aggregation.
 * <p>
 * <code>@Sum</code> is short hand for <code>@Aggregation("sum(...propertyName...)")</code>
 * </p>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * @Sum
 * BigDecimal distance;
 *
 * // is the same as:
 *
 * @Aggregation("sum(distance)")
 * BigDecimal distance;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Sum {

}
