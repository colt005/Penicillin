package jp.nephy.penicillin.models

import com.google.gson.JsonObject
import jp.nephy.jsonkt.byNullableInt
import jp.nephy.jsonkt.byString


data class Embed(override val json: JsonObject): PenicillinModel {
    val authorName by json.byString("author_name")
    val authorUrl by json.byString("author_url")
    val cacheAge by json.byString("cache_age")
    val height by json.byNullableInt
    val html by json.byString
    val providerName by json.byString("provider_name")
    val providerUrl by json.byString("provider_url")
    val type by json.byString
    val url by json.byString
    val version by json.byString
    val width by json.byNullableInt
}
