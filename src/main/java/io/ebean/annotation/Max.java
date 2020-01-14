package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a property to be an MAX aggregation.
 * <p>
 * <code>@Max</code> is short hand for <code>@Aggregation("max(...propertyName...)")</code>
 * </p>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * @Max
 * BigDecimal distance;
 *
 * // is the same as:
 *
 * @Aggregation("max(distance)")
 * BigDecimal distance;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Aggregation("max($1)")
public @interface Max {

}
