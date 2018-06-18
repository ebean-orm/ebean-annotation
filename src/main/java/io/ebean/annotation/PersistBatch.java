package io.ebean.annotation;

/**
 * Defines the mode for JDBC batch processing.
 * <p>
 * Used both at a per transaction basis and per request basis.
 * </p>
 *
 * <pre>{@code
 *
 *   // set full jdbc batch as default
 *   serverConfig.setPersistBatch(PersistBatch.ALL);
 *
 *
 *   // set full jdbc batch per transaction
 *   transaction.setBatchMode(true);
 *
 * }</pre>
 *
 */
public enum PersistBatch {

  /**
   * Do not use JDBC Batch mode.
   */
  NONE,

  /**
   * Use JDBC Batch mode on Inserts, Updates and Deletes.
   */
  ALL,

  /**
   * You should not use this value explicitly. It should only used on the Transactional annotation
   * to indicate that the value should inherit from the ServerConfig setting.
   */
  INHERIT
}
