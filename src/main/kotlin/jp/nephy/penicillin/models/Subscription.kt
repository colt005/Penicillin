package jp.nephy.penicillin.models

import jp.nephy.jsonkt.JsonObject
import jp.nephy.jsonkt.delegation.modelList
import jp.nephy.jsonkt.delegation.string

object Subscription {
    data class Count(override val json: JsonObject): PenicillinModel {
        val accountName by string("account_name")
        val subscriptionsCountAll by string("subscriptions_count_all")
        val subscriptionsCountDirectMessages by string("subscriptions_count_direct_messages")
    }

    data class List(override val json: JsonObject): PenicillinModel {
        val environment by string
        val applicationId by string("application_id")
        val subscriptions by modelList<Subscription>()

        data class Subscription(override val json: JsonObject): PenicillinModel {
            val userId by string("user_id")
        }
    }
}