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

package jp.nephy.penicillin.endpoints.oauth


import jp.nephy.penicillin.core.auth.AuthorizationType
import jp.nephy.penicillin.core.session.post
import jp.nephy.penicillin.endpoints.OAuth
import jp.nephy.penicillin.endpoints.Option
import jp.nephy.penicillin.extensions.await

/**
 * Represents "/oauth/request_token" response.
 */
data class RequestTokenResponse(
    /**
     * Request token.
     */
    val requestToken: String,

    /**
     * Request token secret.
     */
    val requestTokenSecret: String,

    /**
     * Whether if the callback was confirmed.
     */
    val callbackConfirmed: Boolean
)

/**
 * Allows a Consumer application to obtain an OAuth Request Token to request user authorization. This method fulfills Section 6.1 of the OAuth 1.0 authentication flow.
 * We require you use HTTPS for all OAuth authorization steps.
 * Usage Note: Only ASCII values are accepted for the oauth_nonce
 * 
 * [Twitter API reference](https://developer.twitter.com/en/docs/basics/authentication/api-reference/request_token)
 * 
 * @param callbackUrl For OAuth 1.0a compliance this parameter is required . The value you specify here will be used as the URL a user is redirected to should they approve your application's access to their account. Set this to oob for out-of-band pin mode. This is also how you specify custom callbacks for use in desktop/mobile applications. Always send an oauth_callback on this step, regardless of a pre-registered callback. We require that any callback URL used with this endpoint will have to be whitelisted within the app settings on developer.twitter.com*
 * @param xAuthAccessType Overrides the access level an application requests to a users account. Supported values are read or write . This parameter is intended to allow a developer to register a read/write application but also request read only access when appropriate.
 * @param options Optional. Custom parameters of this request.
 * @receiver [OAuth] endpoint instance.
 * @return [RequestTokenResponse].
 */
suspend fun OAuth.requestToken(
    callbackUrl: String = "oob",
    xAuthAccessType: String? = null,
    vararg options: Option
): RequestTokenResponse {
    val response = requestTokenInternal(callbackUrl, xAuthAccessType, *options).await()
    
    val result = response.content.split("&").map { parameter -> 
        parameter.split("=", limit = 2).let { it.first() to it.last() }
    }.toMap()

    val requestToken = result["oauth_token"] ?: throw IllegalStateException()
    val requestTokenSecret = result["oauth_token_secret"] ?: throw IllegalStateException()
    val callbackConfirmed = result["oauth_callback_confirmed"]?.toBoolean() ?: throw IllegalStateException()
    
    return RequestTokenResponse(requestToken, requestTokenSecret, callbackConfirmed)
}

private fun OAuth.requestTokenInternal(
    callbackUrl: String,
    xAuthAccessType: String? = null,
    vararg options: Option
) = client.session.post("/oauth/request_token") {
    authType(AuthorizationType.OAuth1a, callbackUrl)
    body {
        form {
            add(
                "x_auth_access_type" to xAuthAccessType, *options
            )
        }
    }
}.text()
