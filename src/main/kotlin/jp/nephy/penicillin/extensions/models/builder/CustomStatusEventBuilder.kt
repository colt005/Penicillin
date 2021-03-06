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

package jp.nephy.penicillin.extensions.models.builder

import jp.nephy.jsonkt.toJsonObject
import jp.nephy.penicillin.core.experimental.PenicillinExperimentalApi
import jp.nephy.penicillin.core.streaming.handler.UserStreamEvent
import jp.nephy.penicillin.extensions.parseModel
import jp.nephy.penicillin.models.Stream
import java.util.*

/**
 * Custom payload builder for [Stream.StatusEvent].
 */
class CustomStatusEventBuilder(type: UserStreamEvent): JsonBuilder<Stream.StatusEvent>, JsonMap by jsonMapOf(
    "event" to type.key,
    "source" to null,
    "target" to null,
    "target_object" to null,
    "created_at" to null
) {
    private var source = CustomUserBuilder()
    /**
     * Sets source.
     */
    fun source(builder: CustomUserBuilder.() -> Unit) {
        source.apply(builder)
    }

    private var target = CustomUserBuilder()
    /**
     * Sets target.
     */
    fun target(builder: CustomUserBuilder.() -> Unit) {
        target.apply(builder)
    }

    private var targetObject = CustomStatusBuilder()
    /**
     * Sets target_object.
     */
    fun targetObject(builder: CustomStatusBuilder.() -> Unit) {
        targetObject.apply(builder)
    }

    private var createdAt: Date? = null
    /**
     * Sets created_at.
     */
    fun createdAt(date: Date? = null) {
        createdAt = date
    }

    @UseExperimental(PenicillinExperimentalApi::class)
    override fun build(): Stream.StatusEvent {
        val source = source.build()
        val target = target.build()
        val targetObject = targetObject.build()
        
        this["source"] = source
        this["target"] = target
        this["target_object"] = targetObject
        this["created_at"] = createdAt.toCreatedAt()

        return toJsonObject().parseModel()
    }
}
