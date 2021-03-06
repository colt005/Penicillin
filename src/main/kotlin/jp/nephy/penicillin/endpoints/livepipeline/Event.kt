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

package jp.nephy.penicillin.endpoints.livepipeline


import jp.nephy.penicillin.core.request.action.StreamApiAction
import jp.nephy.penicillin.core.session.get
import jp.nephy.penicillin.core.streaming.handler.LivePipelineHandler
import jp.nephy.penicillin.core.streaming.listener.LivePipelineListener
import jp.nephy.penicillin.endpoints.LivePipeline
import jp.nephy.penicillin.endpoints.Option
import jp.nephy.penicillin.endpoints.PrivateEndpoint

/**
 * Undocumented endpoint.
 * 
 * @param ids Array of status id to track.
 * @param options Optional. Custom parameters of this request.
 * @receiver [LivePipeline] endpoint instance.
 * @return [StreamApiAction] for [LivePipelineHandler] handler with [LivePipelineListener] listener.
 */
@PrivateEndpoint
fun LivePipeline.event(
    ids: List<Long>,
    vararg options: Option
) = client.session.get("/1.1/live_pipeline/events") {
    parameter(
        "topic" to ids.joinToString(",") { "/tweet_engagement/$it" },
        *options
    )
}.stream<LivePipelineListener, LivePipelineHandler>()
