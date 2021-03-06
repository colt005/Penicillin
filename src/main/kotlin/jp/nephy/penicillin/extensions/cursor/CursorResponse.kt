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

@file:Suppress("UNUSED")

package jp.nephy.penicillin.extensions.cursor

import jp.nephy.penicillin.core.response.CursorJsonObjectResponse
import jp.nephy.penicillin.models.TwitterList
import jp.nephy.penicillin.models.User
import jp.nephy.penicillin.models.cursor.CursorIds
import jp.nephy.penicillin.models.cursor.CursorLists
import jp.nephy.penicillin.models.cursor.CursorUsers

/**
 * Returns flatten all the user ids.
 */
val Sequence<CursorJsonObjectResponse<CursorIds>>.allIds: List<Long>
    get() = toList().flatMap { it.result.ids }

/**
 * Returns flatten all the lists.
 */
val Sequence<CursorJsonObjectResponse<CursorLists>>.allLists: List<TwitterList>
    get() = toList().flatMap { it.result.lists }

/**
 * Returns flatten all the users.
 */
val Sequence<CursorJsonObjectResponse<CursorUsers>>.allUsers: List<User>
    get() = toList().flatMap { it.result.users }
