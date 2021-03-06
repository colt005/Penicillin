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

package jp.nephy.penicillin.endpoints.lists

import jp.nephy.penicillin.core.request.action.JsonObjectApiAction
import jp.nephy.penicillin.core.session.post
import jp.nephy.penicillin.endpoints.Lists
import jp.nephy.penicillin.endpoints.Option
import jp.nephy.penicillin.models.TwitterList

/**
 * Unsubscribes the authenticated user from the specified list.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-destroy)
 * 
 * @param listId The numerical id of the list.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonObjectApiAction] for [TwitterList] model.
 */
fun Lists.unsubscribe(
    listId: Long,
    vararg options: Option
) = unsubscribe(listId, null, null, null, *options)

/**
 * Unsubscribes the authenticated user from the specified list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-destroy)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerScreenName The screen name of the user who owns the list being requested by a slug.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonObjectApiAction] for [TwitterList] model.
 */
fun Lists.unsubscribeByOwnerScreenName(
    slug: String,
    ownerScreenName: String,
    vararg options: Option
) = unsubscribe(null, slug, ownerScreenName, null, *options)

/**
 * Unsubscribes the authenticated user from the specified list.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/create-manage-lists/api-reference/post-lists-subscribers-destroy)
 *
 * @param slug You can identify a list by its slug instead of its numerical id. If you decide to do so, note that you'll also have to specify the list owner using the owner_id or owner_screen_name parameters.
 * @param ownerId The user ID of the user who owns the list being requested by a slug.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Lists] endpoint instance.
 * @return [JsonObjectApiAction] for [TwitterList] model.
 */
fun Lists.unsubscribeByOwnerId(
    slug: String,
    ownerId: Long,
    vararg options: Option
) = unsubscribe(null, slug, null, ownerId, *options)

private fun Lists.unsubscribe(
    listId: Long? = null,
    slug: String? = null,
    ownerScreenName: String? = null,
    ownerId: Long? = null,
    vararg options: Option
) = client.session.post("/1.1/lists/subscribers/destroy.json") {
    body {
        form {
            add(
                "list_id" to listId,
                "slug" to slug,
                "owner_screen_name" to ownerScreenName,
                "owner_id" to ownerId,
                *options
            )
        }
    }
}.jsonObject<TwitterList>()
