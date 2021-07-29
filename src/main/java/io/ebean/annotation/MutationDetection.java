package io.ebean.annotation;

/**
 * Mutation detection mode used on {@code @DbJson} properties.
 * <p>
 * The {@code @DbJson} properties are often plain beans and we use Jackson to store them in
 * json form. These are the options for detecting if a bean as been mutated typically by
 * comparing the json content (SOURCE mode) or comparing a hash/checksum of the json content
 * (HASH mode).
 */
public enum MutationDetection {

  /**
   * Defaults to using HASH mode for plain Jackson beans. Collection types (List, Set, Map) use
   * ModifyAware wrapper types to detect when the collection has been mutated but may not be
   * aware of mutations of the elements.
   */
  DEFAULT,

  /**
   * No dirty detection. The property is only included in an update when the property itself has
   * been set. There is no attempt to detect if the bean has been mutated.
   */
  NONE,

  /**
   * Dirty detection using a hash checksum function on json content.
   * <p>
   * A CRC32 checksum value of the json content is computed and held. For updates the checksum
   * is compared and when it is different the property is deemed dirty and included in an update.
   * <p>
   * Unlike SOURCE mode this does not support generating an 'oldValue' for change logs and
   * persist listeners.
   */
  HASH,

  /**
   * Dirty detection using the original json content.
   * <p>
   * The json content is kept for this property and dirty detection is performed by comparing
   * the json content. As such this approach uses more memory than HASH mode.
   * <p>
   * This mode supports 'oldValue' for change logs and persist listeners. The 'oldValue'
   * of a mutated json bean can be rebuilt using the original json content. This 'oldValue'
   * is then available for change logs and persist listeners.
   */
  SOURCE
}
