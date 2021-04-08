package io.ebean.annotation;

/**
 * Defines the mode for autoPersistUpdates.
 */
public enum TxOption {

  /**
   * Use the default setting from DatabaseConfig.
   */
  DEFAULT(null),

  /**
   * Enable / turn on.
   */
  ON(Boolean.TRUE),

  /**
   * Disable / turn off.
   */
  OFF(Boolean.FALSE);

  private final Boolean val;

  TxOption(Boolean val) {
    this.val = val;
  }

  /**
   * Return as nullable Boolean value.
   */
  public Boolean asBoolean() {
    return val;
  }
}
