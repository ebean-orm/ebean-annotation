package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify in which tablespaces the data (tables, indices and lobs) will be stored. The annotation can be used for entities.
 * <p>
 * This is currently only useful for DB2 and not used for any other database.
 * </p>
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTablespace {

  /**
   * Tablespace for tables.
   */
  String value();
  
  /**
   * Tablespace for indices.
   */
  String index() default "$DEFAULT";
  
  /**
   * Tablespace for lob fields.
   */
  String lob() default "$DEFAULT";

}

