package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A non-JPA standard alternative to using <code>@Column(nullable=false)</code>
 * or javax validation <code>@NotNull</code>.
 * <p>
 * We would typically prefer to use the standard annotations but with entity beans
 * written in Kotlin as constructor properties the javax validation <code>@NotNull</code>
 * is applied as parameter on the constructor rather than as a property mapping.
 * </p>
 * <p>
 * So it is generally not ideal to use this non-standard JPA annotation but some may prefer
 * it style wise - especially with Kotlin.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {

}
