package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specify that the underlying table is partitioned.
 * <p>
 * This is currently only useful for Postgres 10 range partitioned tables and not used for any other database
 * or ElasticSearch or other non-range partitioning.
 * </p>
 * <p>
 * This modifies the Postgres DDL generation only adding range partitioning clause and suppressing foreign
 * key constraints to partitioned tables.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DbPartition {

  /**
   * The partition mode.
   */
  PartitionMode mode();

  /**
   * The property or column to partition on.
   */
  String property();
}
