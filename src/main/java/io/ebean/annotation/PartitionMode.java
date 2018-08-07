package io.ebean.annotation;

/**
 * Common partition modes.
 */
public enum PartitionMode {

  /**
   * Daily partitioning.
   */
  DAY,

  /**
   * Weekly partitioning.
   */
  WEEK,

  /**
   * Month based partitioning.
   */
  MONTH,

  /**
   * Year based partitioning.
   */
  YEAR
}
