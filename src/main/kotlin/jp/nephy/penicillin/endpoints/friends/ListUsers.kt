/*
 * The MIT License (MIT)
 *
 *     Copyright (c) 2017-2019 Nephy Project Team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

@file:Suppress("UNUSED", "PublicApiImplicitType")

package jp.nephy.penicillin.endpoints.friends

import jp.nephy.penicillin.core.request.action.CursorJsonObjectApiAction
import jp.nephy.penicillin.core.session.get
import jp.nephy.penicillin.endpoints.Friends
import jp.nephy.penicillin.endpoints.Option
import jp.nephy.penicillin.models.cursor.CursorUsers

/**
 * Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their "friends").
 * At this time, results are ordered with the most recent following first — however, this ordering is subject to unannounced change and eventual consistency issues. Results are given in groups of 20 users and multiple "pages" of results can be navigated through using the next_cursor value in subsequent requests. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-list)
 *
 * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a previous_cursor and next_cursor to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param skipStatus When set to either true, t or 1 statuses will not be included in the returned user objects.
 * @param includeUserEntities    The user object entities node will not be included when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonObjectApiAction] for [CursorUsers] model.
 */
fun Friends.listUsers(
    cursor: Long? = null,
    count: Int? = null,
    skipStatus: Boolean? = null,
    includeUserEntities: Boolean? = null,
    vararg options: Option
) = listUsersInternal(null, null, cursor, count, skipStatus, includeUserEntities, *options)

/**
 * Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their "friends").
 * At this time, results are ordered with the most recent following first — however, this ordering is subject to unannounced change and eventual consistency issues. Results are given in groups of 20 users and multiple "pages" of results can be navigated through using the next_cursor value in subsequent requests. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-list)
 *
 * @param userId The ID of the user for whom to return results.
 * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a previous_cursor and next_cursor to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param skipStatus When set to either true, t or 1 statuses will not be included in the returned user objects.
 * @param includeUserEntities    The user object entities node will not be included when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonObjectApiAction] for [CursorUsers] model.
 * @see listIdsByScreenName
 * @see listIds
 */
fun Friends.listUsersByUserId(
    userId: Long,
    cursor: Long? = null,
    count: Int? = null,
    skipStatus: Boolean? = null,
    includeUserEntities: Boolean? = null,
    vararg options: Option
) = listUsersInternal(userId, null, cursor, count, skipStatus, includeUserEntities, *options)

/**
 * Returns a cursored collection of user objects for every user the specified user is following (otherwise known as their "friends").
 * At this time, results are ordered with the most recent following first — however, this ordering is subject to unannounced change and eventual consistency issues. Results are given in groups of 20 users and multiple "pages" of results can be navigated through using the next_cursor value in subsequent requests. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/follow-search-get-users/api-reference/get-friends-list)
 *
 * @param screenName The screen name of the user for whom to return results.
 * @param cursor Causes the list of connections to be broken into pages of no more than 5000 IDs at a time. The number of IDs returned is not guaranteed to be 5000 as suspended users are filtered out after connections are queried. If no cursor is provided, a value of -1 will be assumed, which is the first "page." The response from the API will include a previous_cursor and next_cursor to allow paging back and forth. See [Using cursors to navigate collections](https://developer.twitter.com/en/docs/basics/cursoring) for more information.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param skipStatus When set to either true, t or 1 statuses will not be included in the returned user objects.
 * @param includeUserEntities    The user object entities node will not be included when set to false.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonObjectApiAction] for [CursorUsers] model.
 * @see listIdsByUserId
 * @see listIds
 */
fun Friends.listUsersByScreenName(
    screenName: String,
    cursor: Long? = null,
    count: Int? = null,
    skipStatus: Boolean? = null,
    includeUserEntities: Boolean? = null,
    vararg options: Option
) = listUsersInternal(null, screenName, cursor, count, skipStatus, includeUserEntities, *options)

private fun Friends.listUsersInternal(
    userId: Long? = null,
    screenName: String? = null,
    cursor: Long? = null,
    count: Int? = null,
    skipStatus: Boolean? = null,
    includeUserEntities: Boolean? = null,
    vararg options: Option
) = client.session.get("/1.1/friends/list.json") {
    parameter(
        "user_id" to userId,
        "screen_name" to screenName,
        "cursor" to cursor,
        "count" to count,
        "skip_status" to skipStatus,
        "include_user_entities" to includeUserEntities,
        *options
    )
}.cursorJsonObject<CursorUsers>()

/**
 * Shorthand property to [Friends.listUsers].
 * @see Friends.listUsers
 */
val Friends.listUsers
    get() = listUsers()
