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

package jp.nephy.penicillin.endpoints.accountactivity

import jp.nephy.penicillin.core.request.action.EmptyApiAction
import jp.nephy.penicillin.core.session.post
import jp.nephy.penicillin.endpoints.AccountActivity
import jp.nephy.penicillin.endpoints.Option

/**
 * Subscribes the provided application to all events for the provided environment for all message types. After activation, all events for the requesting user will be sent to the application’s webhook via POST request.
 * Subscriptions are limited to a maximum of 15 unique users per application in the free (sandbox) tier.
 *
 * [Twitter API reference](https://developer.twitter.com/en/docs/accounts-and-users/subscribe-account-activity/api-reference/aaa-premium#post-account-activity-all-env-name-subscriptions)
 *
 * @param envName Environment name.
 * @param options Optional. Custom parameters of this request.
 * @receiver [AccountActivity] endpoint instance.
 * @return [EmptyApiAction].
 */
fun AccountActivity.subscribe(
    envName: String,
    vararg options: Option
) = client.session.post("/1.1/account_activity/all/$envName/subscriptions.json") {
    body {
        form {
            add(*options)
        }
    }
}.empty()
