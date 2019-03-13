package io.ebean.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

/**
 * Specify a storage engine to use with a specific table.
 * <p>
 * Intended for databases like ClickHouse and MySql that have multiple storage engine options.
 * </p>
 * <pre>{@code
 *
 * @StorageEngine("ENGINE = MergeTree()")
 * @Entity
 * public class Orders {
 *   ...
 * }
 *
 * }</pre>
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface StorageEngine {

  /**
   * The platform specific storage engine clause that would be part of create table statements.
   */
  String value();
}
