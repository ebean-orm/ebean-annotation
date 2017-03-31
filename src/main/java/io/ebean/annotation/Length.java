package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A non-JPA standard alternative to using <code>@Column(length)</code>.
 * <p>
 * We would typically set the length of a varchar column using <code>@Column(length)</code>
 * or via javax validation <code>@Size(max)</code> annotations.
 * </p>
 * <p>
 * In Kotlin we tend NOT to use the <code>@Size(max)</code> annotation as it has more
 * targets (including parameter) and so when beans are written in Kotlin constructor form
 * the <code>@Size</code> is not read as a mapping.
 * </p>
 * <p>
 * So it is generally not ideal to use this non-standard JPA annotation but some may prefer
 * it style wise - especially with Kotlin.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Length {

  int value();
}
