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

@file:Suppress("UNUSED", "PublicApiImplicitType", "KDocMissingDocumentation")

package jp.nephy.penicillin.models

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.boolean
import jp.nephy.jsonkt.delegation.long
import jp.nephy.jsonkt.delegation.string
import jp.nephy.penicillin.core.session.ApiClient
import jp.nephy.penicillin.extensions.penicillinModel

data class Relationship(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
    val source by penicillinModel<Source>()
    val target by penicillinModel<Target>()

    data class Source(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val allReplies by boolean("all_replies")
        val blockedBy by boolean("blocked_by")
        val blocking by boolean
        val canDm by boolean("can_dm")
        val canMediaTag by boolean("can_media_tag")
        val followedBy by boolean("followed_by")
        val following by boolean
        val followingReceived by boolean("following_received")
        val followingRequested by boolean("following_requested")
        val id by long
        val idStr by string("id_str")
        val liveFollowing by boolean("live_following")
        val markedSpam by boolean("marked_spam")
        val muting by boolean
        val notificationsEnabled by boolean("notifications_enabled")
        val screenName by string("screen_name")
        val wantRetweets by boolean("want_retweets")
    }

    data class Target(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val followedBy by boolean("followed_by")
        val following by boolean
        val followingReceived by boolean("following_received")
        val followingRequested by boolean("following_requested")
        val id by long
        val idStr by string("id_str")
        val screenName by string("screen_name")
    }
}
