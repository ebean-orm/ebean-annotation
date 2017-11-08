package io.ebean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to control which ToMany relationships are preferred as 'joins' rather than
 * as 'secondary joins' (query fetches).
 * <p>
 * Ebean automatically limits the number of SQL joins to ToMany relationships to avoid
 * SQL cartesian product and to honor SQL first row / max rows limits. It does this by
 * allowing the first ToMany relationship/path that has been defined on a query to be
 * included in the main query as a join and then converting any remaining ToMany
 * relationship/path to "query joins".
 * </p>
 * <p>
 * Without explicit use of <code>@FetchPreference</code> the first path that contains a
 * ToMany is included in the main query as a join.
 * </p>
 *
 * <p>
 * Indicate to Ebean the preferred relationships to join to.
 * </p>
 * <h2>Example</h2>
 * <p>
 * Prefer joins to 'participants' over 'messages'
 * </p>
 * <pre>{@code
 *
 *     @FetchPreference(1)
 *     @OneToMany(mappedBy = "conversation")
 *     List<Participation> participants;
 *
 *     @FetchPreference(2)
 *     @OneToMany(mappedBy = "conversation")
 *     List<Message> messages;
 *
 * }</pre>
 *
 * <h2>Example query that is impacted</h2>
 * <pre>{@code
 *
 *  // Without @FetchPreference the first ToMany which
 *  // is "messages" is joined and "participants" is
 *  // query joined (executed as a secondary query)
 *
 *  Ebean.find(Conversation.class)
 *      .fetch("messages")         // <- first ToMany path Ebean sees
 *      .fetch("participants")     // <- second ToMany path Ebean sees
 *      .findList();
 *
 * }</pre>
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface FetchPreference {

	/**
	 * The fetch preference used - low value means higher preference.
	 */
	int value();
}