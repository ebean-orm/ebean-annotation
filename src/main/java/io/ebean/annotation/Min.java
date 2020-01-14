package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a property to be an MIN aggregation.
 * <p>
 * <code>@Min</code> is short hand for <code>@Aggregation("min(...propertyName...)")</code>
 * </p>
 *
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * @Min
 * BigDecimal distance;
 *
 * // is the same as:
 *
 * @Aggregation("min(distance)")
 * BigDecimal distance;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Aggregation("min($1)")
public @interface Min {

}
