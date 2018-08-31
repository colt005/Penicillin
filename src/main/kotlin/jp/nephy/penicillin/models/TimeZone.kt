package jp.nephy.penicillin.models

import com.google.gson.JsonObject
import jp.nephy.jsonkt.byInt
import jp.nephy.jsonkt.byString


data class TimeZone(override val json: JsonObject): PenicillinModel {
    val name by json.byString
    val utcOffset by json.byInt("utc_offset")
    val tzinfoName by json.byString("tzinfo_name")
}