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
import jp.nephy.jsonkt.delegation.byString
import jp.nephy.jsonkt.delegation.jsonObject
import jp.nephy.jsonkt.delegation.long
import jp.nephy.jsonkt.delegation.string
import jp.nephy.penicillin.core.session.ApiClient
import jp.nephy.penicillin.extensions.nullablePenicillinModel
import jp.nephy.penicillin.extensions.penicillinModel
import jp.nephy.penicillin.extensions.penicillinModelList

data class MomentGuide(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
    private val category by jsonObject
    val categoryId by category.byString("category_id")
    val categoryName by category.byString("name")
    val categoryUri by category.byString("uri")
    val impressionId by long("impression_id")
    val cursor by string("scroll_cursor")
    val modules by penicillinModelList<Module>()
    val trendModule by nullablePenicillinModel<TrendModule>()

    data class Module(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val moduleType by string("module_type")
        val moments by penicillinModelList<Moment>()
    }

    data class TrendModule(override val json: JsonObject, override val client: ApiClient): PenicillinModel {
        val metadata by penicillinModel<TrendMetadata>()
        val trends by penicillinModelList<TrendType>()
    }
}
