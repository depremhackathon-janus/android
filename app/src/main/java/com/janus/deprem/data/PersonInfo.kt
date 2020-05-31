package com.janus.deprem.data

import org.json.JSONException
import org.json.JSONObject

data class PersonInfo constructor(
    var number: Long,
    var status: Int,
    var longitude: Float = 45.56f,
    var latitude: Float = 36.09f,
    var txt: String = ""
) {
    @Throws(JSONException::class)
    fun toJson(): JSONObject {
        val obj = JSONObject()
        obj.put("num", number)
        obj.put("stat", status)
        obj.put("long", longitude.toDouble())
        obj.put("lat", latitude.toDouble())
        obj.put("txt", txt)
        return obj
    }

}

enum class Status(val value: Int) {
    Guvende(1),
    ZorDurumda(2),
    GidaIhtiyaci(4),
    BezIhtiyaci(8),
    CadirIhtiyaci(16),
    EnkazAltindayim(32),
    KomsumdanSesGeliyor(64);
}