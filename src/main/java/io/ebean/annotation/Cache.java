package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the default cache use specific entity type.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

  /**
   * If set to true additionally use a near cache (for Redis and similar L2 cache options).
   * <p>
   * This does not apply to query caches (as they are always near caches) but applies to
   * the bean caching (bean cache, natural key cache and collection ids cache).
   * </p>
   * <p>
   * Near caches are fast in that they are in local memory avoid going over the network to the
   * remote cache with the downside of using more local memory and increasing the cache invalidation
   * costs for updates and deletes (as cache invalidations need to propagate with near caching turned on).
   * </p>
   * <p>
   * Near caching is best with relatively many reads to few writes.
   * </p>
   */
  boolean nearCache() default false;

  /**
   * Set this to true to enable the use of query cache.
   * <p>
   * By default query caching is disabled as the query cache invalidates
   * frequently and so it is typically used for specific bean types and cases.
   * </p>
   */
  boolean enableQueryCache() default false;

  /**
   * Set this to false to disable the use of bean cache.
   * <p>
   * By default bean caching is expected so this defaults to true.  We might
   * set this to false on a bean type that we want to use query caching but no
   * bean caching (and this is expected to be a rare case).
   * </p>
   * <p>
   * When bean caching is enabled by default "find by id" and "find by unique natural key"
   * queries will try to use the bean cache. We use Query.setUseCache(boolean)
   * with <code>false</code> for the case when we do NOT want to use the bean cache.
   * </p>
   */
  boolean enableBeanCache() default true;

  /**
   * Specify the properties that is a natural unique identifier for the bean.
   * <p>
   * When a findOne() query is used with this property as the sole expression
   * then there will be a lookup into the L2 natural key cache.
   * </p>
   */
  String[] naturalKey() default {};

  /**
   * When set to true the beans returned from a query will default to be
   * readOnly.
   * <p>
   * If the bean is readOnly and has no relationships then it may be sharable.
   * </p>
   * <p>
   * If you try to modify a readOnly bean it will throw an
   * IllegalStateException.
   * </p>
   */
  boolean readOnly() default false;

  /**
   * Specify a named cache region.
   * <p>
   * Regions can be turned on and off dynamically at runtime.
   * </p>
   * <p>
   * Depending on the cache implementation a region can potentially have different
   * deployment targets - for example, with the Ebean redis cache a region could be
   * configured to use a different redis server.
   * </p>
   * <p>
   * The "default" region is called <code>r0</code>.
   * </p>
   */
  String region() default "r0";
}
