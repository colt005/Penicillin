package jp.nephy.penicillin.models

import com.google.gson.JsonObject
import jp.nephy.jsonkt.byString

data class UserMetadata(override val json: JsonObject): PenicillinModel {
    val resultType by json.byString("result_type")
}