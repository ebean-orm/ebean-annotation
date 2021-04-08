package io.ebean.annotation;

/**
 * Modes for the DbForeignKey onDelete and onUpdate clause of a foreign keys.
 * <p>
 * Note that we should never change the <code>onUpdate</code> clause as primary keys
 * should never change. Instead review the design ensuring the primary keys are immutable.
 * Always leave the onUpdate mode at it's default of RESTRICT ('No Action').
 * </p>
 *
 * @see DbForeignKey
 */
public enum ConstraintMode {

  /**
   * Restrict - The usual default behavior for foreign constraints (also known as 'No Action').
   */
  RESTRICT,

  /**
   * Set the value to null when a referenced id is deleted.
   */
  SET_NULL,

  /**
   * Set the value to 'default' when a referenced id is deleted.
   */
  SET_DEFAULT,

  /**
   * Cascade - To automatically cascade a delete of a referenced id.
   */
  CASCADE

}
