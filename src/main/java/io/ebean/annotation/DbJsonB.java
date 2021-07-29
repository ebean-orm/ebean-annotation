package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify a property holding JSON content.
 * <p>
 * The content will be stored on Postgres using it's JSONB type and as Clob for other databases.
 * </p>
 * <p>
 * This is equivalent to using <code>@DbJson(storage = DbJsonType.JSONB)</code>
 * </p>
 * <p>
 * <h3>Example:</h3>
 * <pre>{@code
 *
 * // Store as JSONB on Postgres or Clob on other databases
 * @DbJsonB
 * Map<String, Object> content;
 *
 * }</pre>
 * <p>
 * <h3>Equivalent to:</h3>
 * <pre>{@code
 *
 * // Store as JSONB on Postgres or Clob on other databases
 * @DbJson(storage = DbJsonType.JSONB)
 * Map<String,Object> content;
 *
 * }</pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DbJsonB {

  /**
   * The name of the column (Optional).
   */
  String name() default "";

  /**
   * The mutation detection mode to use.
   * <p>
   * This is used to handle if and how it is detected that the json property
   * has been mutated and hence should be included in an update.
   */
  MutationDetection mutationDetection() default MutationDetection.DEFAULT;

  /**
   * For VARCHAR storage specify the column length.
   */
  int length() default 0;
}
