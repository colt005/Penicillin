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

package jp.nephy.penicillin.extensions.models

import jp.nephy.penicillin.endpoints.favorites
import jp.nephy.penicillin.endpoints.favorites.create
import jp.nephy.penicillin.endpoints.favorites.destroy
import jp.nephy.penicillin.endpoints.statuses
import jp.nephy.penicillin.endpoints.statuses.create
import jp.nephy.penicillin.endpoints.statuses.delete
import jp.nephy.penicillin.endpoints.statuses.show
import jp.nephy.penicillin.models.Status

/**
 * Creates an action to retrieve this status.
 */
fun Status.refresh() = client.statuses.show(id = id)

/**
 * Creates an action to favorite this status.
 */
fun Status.favorite() = client.favorites.create(id = id)

/**
 * Creates an action to unfavorite this status.
 */
fun Status.unfavorite() = client.favorites.destroy(id = id)

/**
 * Creates an action to delete this status.
 */
fun Status.delete() = client.statuses.delete(id = id)

/**
 * Creates an action to send a mention to this status.
 *
 * @param text Status body.
 */
fun Status.mention(text: String) = client.statuses.create(text, inReplyToStatusId = id)
