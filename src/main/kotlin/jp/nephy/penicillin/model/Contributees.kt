package jp.nephy.penicillin.model

import com.github.salomonbrys.kotson.byBool
import com.google.gson.JsonElement
import jp.nephy.penicillin.converter.byModel

class Contributees(val json: JsonElement) {
    val admin by json.byBool
    val user by json.byModel<User>()
}