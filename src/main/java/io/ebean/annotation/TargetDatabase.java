package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a target database name when the database used by this entity
 * is not the default database.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDatabase {

  /**
   * The name of the target database this entity should use.
   * <p>
   * This name is used by <code>DB.byName()</code> to obtain the database
   * that should be used with this entity.
   * </p>
   */
  String value();
}
