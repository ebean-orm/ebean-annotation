package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a property to be an AVG aggregation.
 * <p>
 * <code>@Avg</code> is short hand for <code>@Aggregation("avg(...propertyName...)")</code>
 * </p>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * @Avg
 * BigDecimal distance;
 *
 * // is the same as:
 *
 * @Aggregation("avg(distance)")
 * BigDecimal distance;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Aggregation("avg($1)")
public @interface Avg {

}
