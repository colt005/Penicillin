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
import jp.nephy.penicillin.endpoints.PrivateEndpoint
import jp.nephy.penicillin.models.cursor.CursorUsers

/**
 * Returns users who follows specific user.
 *
 * @param userId The ID of the user for whom to return results.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonObjectApiAction] for [CursorUsers] model.
 * @see followingListByScreenName
 */
@PrivateEndpoint
fun Friends.followingListByUserId(
    userId: Long,
    count: Int? = null,
    vararg options: Option
) = followingList(userId, null, count, *options)

/**
 * Returns users who follows specific user.
 *
 * @param screenName The screen name of the user for whom to return results.
 * @param count The number of users to return per page, up to a maximum of 200.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Friends] endpoint instance.
 * @return [CursorJsonObjectApiAction] for [CursorUsers] model.
 * @see followingListByUserId
 */
@PrivateEndpoint
fun Friends.followingListByScreenName(
    screenName: String,
    count: Int? = null,
    vararg options: Option
) = followingList(null, screenName, count, *options)

private fun Friends.followingList(
    userId: Long? = null,
    screenName: String? = null,
    count: Int? = null,
    vararg options: Option
) = client.session.get("/1.1/friends/following/list.json") {
    parameter(
        "user_id" to userId,
        "screen_name" to screenName,
        "count" to count,
        *options
    )
}.cursorJsonObject<CursorUsers>()
