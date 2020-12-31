package ru.rubt.data.local

import android.content.Context
import android.util.JsonReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.rubt.data.local.model.AccessKey
import java.io.FileReader
import javax.inject.Inject

class AccessKeyManager @Inject constructor(
        private val gson: Gson,
        private val context: Context
) {

    companion object {
        const val ACCESS_KEY_FILE = "access_key.json"
    }

    fun getAccessKey(): String {
        val accessKeyFile = context.assets.open(ACCESS_KEY_FILE)
        val accessKeyJson = String(accessKeyFile.readBytes())
        val accessKey = gson.fromJson(
                accessKeyJson,
                AccessKey::class.java)
        return accessKey.key
    }

}