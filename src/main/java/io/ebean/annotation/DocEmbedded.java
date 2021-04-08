package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify the property is included in the parent document store index.
 *
 * <h3>Example</h3>
 * <pre>{@code
 *
 *   @DocStore
 *   @Entity
 *   @Table(name = "o_order")
 *   public class Order {
 *
 *     ...
 *     @DocEmbedded(doc = "id,status,name,billingAddress(*,country(*)")
 *     @ManyToOne
 *     Customer customer;
 *
 *   }
 *
 * }</pre>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DocEmbedded {

  /**
   * The properties on the embedded bean to include in the index.
   */
  String doc() default "";

}
