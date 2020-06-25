package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotated classes (like BeanPersistAdapter, BeanPersistListener etc) with
 * &#64;EbeanComponent and these will be registered with Ebean.
 * <p>
 * For projects using query beans the annotation processor (query bean generator)
 * finds entity beans to register and it will ALSO find classes annotated with
 * &#64;EbeanComponent such that these are registered with Ebean.
 * <p>
 * The query bean generator generates source code to register the entity classes
 * (such that Ebean does not have to scan the classpath for them). At the same time
 * the query bean generator generates will also find <code>@EbeanComponent</code> and
 * also register those with Ebean.
 * <p>
 * When Ebean is programmatically created and entity classes etc are all explicitly
 * registered or when the annotation process (query bean generator) is not used then
 * this annotation has no effect.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EbeanComponent {

}
