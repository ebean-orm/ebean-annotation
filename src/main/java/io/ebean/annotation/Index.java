package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * An annotation for declaring an index.
 *
 * @author rvbiljouw
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Indices.class)
public @interface Index {

  /**
   * Name of the index. If left blank a name is derived using the built in naming convention.
   */
  String name() default "";

  /**
   * If set true indicates this is a unique index.
   */
  boolean unique() default false;

  /**
   * When placed on the class (rather than field) you can specify the columns
   * to include in the index in order.
   * <p>
   * When placed on a field, and columnNames are specified, the field-column has to be included.
   * You can use "${fa}" for alias.
   */
  String[] columnNames() default {};

  /**
   * Platforms this index applies to - default is all platforms.
   * <p>
   * This provides an alternative to using extra-dll.xml to specify
   * platform specific index ddl.
   * <p>
   * Changing platforms is NOT detected as part of DB migration
   * generation (no platform specific DIFF) so using extra-ddl.xml
   * may be preferred.
   */
  Platform[] platforms() default {};

}
