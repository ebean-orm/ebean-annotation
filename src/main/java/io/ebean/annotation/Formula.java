package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Assign to a property to be based on a SQL formula.
 * <p>
 * This is typically a SQL Literal value, SQL case statement, SQL function or
 * similar.
 * </p>
 * <p>
 * Any property based on a formula becomes a read only property.
 * </p>
 * <p>
 * You may also put use the Transient annotation with the Formula annotation.
 * The effect of the Transient annotation in this case is that the formula will
 * <b>NOT</b> be included in queries by default - you have to explicitly include
 * it via <code>Query.select()</code> or <code>Query.fetch()</code>.
 * You may want to do this if the Formula is relatively expensive and only want
 * it included in the query when you explicitly state it.
 * </p>
 * <p>
 * <pre>{@code
 * // On the Order "master" bean
 * // ... a formula using the Order details
 * // ... sum(order_qty*unit_price)
 * @Transient
 * @Formula(select = "_b${ta}.total_amount", join = "join (select order_id, sum(order_qty*unit_price) as total_amount from o_order_detail group by order_id) as _b${ta} on _b${ta}.order_id = ${ta}.id")
 * Double totalAmount;
 *
 * }</pre>
 * <p>
 * As the totalAmount formula is also Transient it is not included by default in
 * queries - it needs to be explicitly included.
 * </p>
 * <pre>{@code
 *
 * // find by Id
 * Order o1 = Ebean.find(Order.class)
 *     .select("id, totalAmount")
 *     .setId(1).findUnique();
 *
 * // find list ... using totalAmount in the where clause
 * List<Order> list = Ebean.find(Order.class)
 *     .select("id, totalAmount")
 *     .where()
 *     .eq("status", Order.Status.NEW)
 *     .gt("totalAmount", 10)
 *     .findList();
 *
 * // as a join from customer
 * List<Customer> l0 = Ebean.find(Customer.class)
 *     .select("id, name")
 *     .fetch("orders", "status, totalAmount")
 *     .where()
 *     .gt("id", 0)
 *     .gt("orders.totalAmount", 10)
 *     .findList();
 *
 * }</pre>
 */
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Formula.List.class)
public @interface Formula {

  /**
   * The SQL to be used in the SELECT part of the SQL to populate a property.
   */
  String select();

  /**
   * OPTIONAL - the SQL to be used in the JOIN part of the SQL to support the
   * formula.
   * <p>
   * This is commonly used to join a 'dynamic view' to support aggregation such
   * as count, sum etc.
   * </p>
   * <p>
   * The join string should start with either "left join" or "join".
   * </p>
   * <p>
   * You will almost certainly use the "${ta}" as a place holder for the table
   * alias of the table you are joining back to (the "base table" of the entity
   * bean).
   * </p>
   * <p>
   * The example below is used to support a total count of topics created by a
   * user.
   * </p>
   * <p>
   * <pre>{@code
   *
   * join (select user_id, count(*) as topic_count from f_topic group by user_id) as _tc on _tc.user_id = ${ta}.id
   *
   * }</pre>
   */
  String join() default "";

  Platform[] platforms() default {};

  /**
   * Repeatable support for {@link Formula}.
   */
  @Target({ElementType.FIELD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {

    Formula[] value() default {};
  }

}
