package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify a default value for DDL-generation &amp; Migration.
 * This annotation is <b>EXPERIMENTAL</b> and may change.
 *
 * @author Roland Praml, FOCONIS AG
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbDefault {
  /**
   * The defaultValue for the column.
   */
  String value();
}
