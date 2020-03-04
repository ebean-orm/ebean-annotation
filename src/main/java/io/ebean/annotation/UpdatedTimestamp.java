package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Deprecated migrate to <code>@WhenModified</code>.
 * <p>
 * For a timestamp property that is set to the datetime when the entity was last
 * updated.
 * <p>
 * This is effectively an alias for @WhenModified which was added as it hints
 * towards a better naming convention (WhenCreated, WhenModified).
 * </p>
 */
@Deprecated
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UpdatedTimestamp {

}
