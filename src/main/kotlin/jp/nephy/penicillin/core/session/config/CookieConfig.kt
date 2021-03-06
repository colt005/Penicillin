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

package jp.nephy.penicillin.core.session.config

import io.ktor.http.Cookie
import jp.nephy.penicillin.core.session.ApiClientDsl
import jp.nephy.penicillin.core.session.SessionBuilder

/**
 * Configures [CookieConfig].
 */
@ApiClientDsl
fun SessionBuilder.cookie(block: CookieConfig.Builder.() -> Unit) {
    getOrPutBuilder { 
        CookieConfig.Builder()
    }.apply(block)
}

internal fun SessionBuilder.createCookieConfig(): CookieConfig {
    return getOrPutBuilder {
        CookieConfig.Builder()
    }.build()
}

/**
 * Represents Cookie config.
 */
data class CookieConfig(
    /**
     * If true, accepts cookies.
     */
    val acceptCookie: Boolean,

    /**
     * Custom cookies.
     */
    val cookies: Map<String, List<Cookie>>
): SessionConfig {
    /**
     * Cookie config builder.
     */
    class Builder: SessionConfigBuilder<CookieConfig> {
        private var acceptCookie = false
        /**
         * Accepts cookies.
         */
        fun acceptCookie() {
            acceptCookie = true
        }

        private val cookies = mutableMapOf<String, MutableList<Cookie>>()
        /**
         * Adds custom cookie.
         */
        fun addCookie(host: String, cookie: Cookie) {
            cookies.getOrPut(host) { mutableListOf() } += cookie
        }

        override fun build(): CookieConfig {
            return CookieConfig(acceptCookie, cookies)
        }
    }
}
