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

package jp.nephy.penicillin.extensions.models

import jp.nephy.penicillin.endpoints.lists
import jp.nephy.penicillin.endpoints.lists.addMember
import jp.nephy.penicillin.endpoints.lists.addMembersByUserIds
import jp.nephy.penicillin.endpoints.lists.removeMember
import jp.nephy.penicillin.endpoints.lists.removeMembersByUserIds
import jp.nephy.penicillin.extensions.await
import jp.nephy.penicillin.models.TwitterList
import jp.nephy.penicillin.models.User

/**
 * Adds the user to this list.
 * This function is suspend-function.
 */
suspend operator fun TwitterList.plusAssign(user: User) {
    client.lists.addMember(id, user.id).await()
}

/**
 * Adds the users to this list.
 * This function is suspend-function.
 */
suspend operator fun TwitterList.plusAssign(users: Iterable<User>) {
    users.map { it.id }.chunked(100).forEach {
        client.lists.addMembersByUserIds(id, it).await()
    }
}

/**
 * Removes the user from this list.
 * This function is suspend-function.
 */
suspend operator fun TwitterList.minusAssign(user: User) {
    client.lists.removeMember(id, user.id).await()
}

/**
 * Removes the users from this list.
 * This function is suspend-function.
 */
suspend operator fun TwitterList.minusAssign(users: Iterable<User>) {
    users.map { it.id }.chunked(100).forEach {
        client.lists.removeMembersByUserIds(id, it).await()
    }
}

