package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is put on entity bean types that themselves are not L2 cached directly but are
 * instead joined to other entity beans that are query cached.
 * <p>
 * For example, we could put this on an Address entity bean that is not cached but is joined
 * to a Customer bean that is query cached.
 * </p>
 * <p>
 * When this is put on an entity bean it means that Insert, Update or Delete changes result in
 * a single table modification event that is sent around the cluster. This event is used to
 * invalidate query caches where the entries have joined to this table.
 * </p>
 * <p>
 * For example, a query on Customer that joins to Address is cached. When an Address is changed
 * that address table modification event is propagated and effectively invalidates query cache
 * entries that are dependent on the address table.
 * </p>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface InvalidateQueryCache {

  /**
   * Specify a named cache region.
   * <p>
   * The "default" region is called <code>r0</code>.
   * </p>
   */
  String region() default "r0";
}
