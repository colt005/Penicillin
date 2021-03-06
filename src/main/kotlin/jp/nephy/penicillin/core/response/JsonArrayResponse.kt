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

package jp.nephy.penicillin.core.response

import io.ktor.client.request.HttpRequest
import io.ktor.client.response.HttpResponse
import jp.nephy.jsonkt.JsonArray
import jp.nephy.jsonkt.toJsonArray
import jp.nephy.penicillin.core.request.action.ApiAction
import jp.nephy.penicillin.core.session.ApiClient
import jp.nephy.penicillin.models.PenicillinModel
import kotlin.reflect.KClass

/**
 * The [ApiResponse] that provides parsed json array with json model.
 */
data class JsonArrayResponse<M: PenicillinModel>(
    override val client: ApiClient,
    override val model: KClass<M>,

    /**
     * Results of response.
     */
    val results: List<M>,

    override val request: HttpRequest,
    override val response: HttpResponse,
    override val content: String,
    override val action: ApiAction<JsonArrayResponse<M>>
): ApiResponse<JsonArrayResponse<M>>, JsonResponse<M, JsonArray>, CompletedResponse, List<M> by results {

    override val json: JsonArray
        get() = map { it.json }.toJsonArray()

    override fun close() {
        response.close()
    }
}
