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

package jp.nephy.penicillin.endpoints.media

import jp.nephy.penicillin.core.request.EndpointHost
import jp.nephy.penicillin.core.request.action.JsonObjectApiAction
import jp.nephy.penicillin.core.session.get
import jp.nephy.penicillin.endpoints.Media
import jp.nephy.penicillin.endpoints.Option

/**
 * The STATUS command is used to periodically poll for updates of media processing operation. After the STATUS command response returns succeeded, you can move on to the next step which is usually create Tweet with media_id.
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/media/upload-media/api-reference/get-media-upload-status)
 * 
 * @param mediaId The media_id returned from the INIT command.
 * @param options Optional. Custom parameters of this request.
 * @receiver [Media] endpoint instance.
 * @return [JsonObjectApiAction] for [jp.nephy.penicillin.models.Media] model.
 */
fun Media.uploadStatus(
    mediaId: Long,
    mediaKey: String? = null,
    vararg options: Option
) = client.session.get("/1.1/media/upload.json", EndpointHost.MediaUpload) {
    parameter(
        "command" to "STATUS",
        "media_id" to mediaId,
        "media_key" to mediaKey,
        *options
    )
}.jsonObject<jp.nephy.penicillin.models.Media>()
