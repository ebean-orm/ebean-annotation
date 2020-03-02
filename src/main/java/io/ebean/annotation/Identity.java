package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines identity generation for an associated entity bean.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE})
public @interface Identity {

  /**
   * The type of identity for the associated entity bean.
   */
  IdentityType type() default IdentityType.AUTO;

  /**
   * For IDENTITY type specify the generated mode of ALWAYS or BY DEFAULT.
   */
  IdentityGenerated generated() default IdentityGenerated.AUTO;

  /**
   * Sequence name when using type SEQUENCE. When not specified the
   * naming convention will define the sequence name.
   */
  String sequenceName() default "";

  /**
   * Optional START WITH value of an IDENTITY or SEQUENCE.
   */
  int start() default 0;

  /**
   * Optional INCREMENT BY value of an IDENTITY or SEQUENCE.
   */
  int increment() default 0;

  /**
   * Optional CACHE option for database IDENTITY or SEQUENCE.
   * <p>
   * Used by some databases for a performance improvement providing identity
   * and sequence values with the potential for gaps and non strict ordering
   * of values being used.
   */
  int cache() default 0;
}
