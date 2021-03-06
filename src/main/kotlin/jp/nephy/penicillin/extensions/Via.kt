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

package jp.nephy.penicillin.extensions

import jp.nephy.penicillin.models.Status

private val tagPattern = "^<a (.+?)>(.+?)</a>$".toRegex()
private val attributePattern = "^(.+?)=\"(.+?)\"$".toRegex()

/**
 * Parsed status source.
 */
val Status.via: Via
    get() {
        val matches = tagPattern.matchEntire(source)

        val tagAttributes = matches?.groupValues?.getOrNull(1)?.split(" ")?.map {
            val (k, v) = attributePattern.matchEntire(it)!!.destructured
            k to v
        }?.toMap()
        val tagValue = matches?.groupValues?.getOrNull(2)
        val href = tagAttributes?.get("href")

        if (tagAttributes == null || tagValue == null || href == null) {
            throw IllegalArgumentException("Invalid source html passed.")
        }

        return Via(href, tagValue, tagAttributes)
    }

/**
 * Represents <a> tag in "source".
 */
data class Via(
    /**
     * Source application url.
     */
    val url: String,

    /**
     * Source application name.
     */
    val name: String,

    /**
     * Source <a> tag attributes.
     */
    val attributes: Map<String, String>
)
