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
        obj.put("long", longitude)
        obj.put("lat", latitude)
        obj.put("txt", txt)
        return obj
    }

}

enum class Status(val value: Int, val description: String) {
    Guvende(1, "Güvendeyim"),
    ZorDurumda(2, "Zor durumdayım"),
    GidaIhtiyaci(4, "Gıda ihtiyacım var"),
    BezIhtiyaci(8, "Bez ihtiyacım var"),
    CadirIhtiyaci(16, "Çadır ihtiyacım var"),
    EnkazAltindayim(32, "Enkaz altındayım"),
    KomsumdanSesGeliyor(64, "Komşumdan ses geliyor");
}