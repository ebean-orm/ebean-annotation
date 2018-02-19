package io.ebean.annotation;

/**
 * Modes for the DbForeignKey onDelete and onUpdate clause of a foreign keys.
 *
 * @see DbForeignKey
 */
public enum ConstraintMode {

	/**
	 * Mode defined as the global default which is usually RESTRICT.
	 */
	GLOBAL_DEFAULT,

	/**
	 * Set the value to null when a referenced id is deleted and or updated.
	 */
	SET_NULL,

	/**
	 * Set the value to 'default' when a referenced id is deleted and or updated.
	 */
	SET_DEFAULT,

	/**
	 * Cascade - To automatically cascade a delete and or an update of a referenced id.
	 */
	CASCADE,

	/**
	 * Restrict - The usual default behavior for foreign constraints.
	 */
	RESTRICT
}
