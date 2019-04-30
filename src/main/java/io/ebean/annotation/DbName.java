package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the name of the DB that the entity should use.
 * <p>
 * This should only used on beans that don't use the default database.
 * </p>
 * <p>
 * This annotation is used as part of query bean generation such that
 * the query beans are generated to use the named (non-default) database.
 * </p>
 * <p>
 * This annotation can be put on entity beans or MappedSuperclass beans.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DbName {

  /**
   * The name of the database.
   */
  String value();
}
