package io.ebean.annotation;

/**
 * The type of identity to be used.
 */
public enum IdentityType {

  /**
   * Use the platform default identity type (database Identity or Sequence).
   */
  AUTO,

  /**
   * Use database IDENTITY column.
   */
  IDENTITY,

  /**
   * Using a database SEQUENCE for the identity value.
   */
  SEQUENCE,

  /**
   * Application supplies the identity value.
   * <p>
   * No database sequence or identity column will be generated in DDL and instead
   * the value is always expected to be supplied by the application or custom generator.
   */
  APPLICATION
}
