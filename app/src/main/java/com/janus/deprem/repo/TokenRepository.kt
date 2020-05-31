package com.janus.deprem.repo

import android.content.SharedPreferences
import android.text.TextUtils
import com.janus.deprem.data.OAuthResponse
import com.janus.deprem.remote.DataHolder
import com.janus.deprem.util.SharedPrefKey
import com.janus.deprem.util.Status
import javax.inject.Inject

class TokenRepository @Inject constructor(val sharedPref: SharedPreferences) {
    private var inMemoryAccessToken: String = ""
    private var inMemoryRefreshToken: String = ""

    fun saveToken(dataHolder: DataHolder<OAuthResponse>): Status {
        when (dataHolder) {
            is DataHolder.Success -> {
                dataHolder.data.let {
                    inMemoryAccessToken = it.accessToken
                    sharedPref.edit().putString(SharedPrefKey.KEY_ACCESS_TOKEN, it.accessToken).apply()
                    if (!TextUtils.isEmpty(it.refreshToken)) {
                        inMemoryRefreshToken = it.refreshToken
                        sharedPref.edit().putString(SharedPrefKey.KEY_REFRESH_TOKEN, it.refreshToken).apply()
                    }
                    return Status.FINISHED
                }
            }
            is DataHolder.Error -> {
                return Status.FAILED.withReason(dataHolder.error.message)
            }
        }
    }

    fun clearTokens() {
        inMemoryAccessToken = ""
        inMemoryRefreshToken = ""
        sharedPref.edit().remove(SharedPrefKey.KEY_ACCESS_TOKEN).apply()
        sharedPref.edit().remove(SharedPrefKey.KEY_REFRESH_TOKEN).apply()
    }


    fun getAccessToken(): String {
        if (inMemoryAccessToken.isEmpty()) {
            inMemoryAccessToken = sharedPref.getString(SharedPrefKey.KEY_ACCESS_TOKEN, "")
        }
        return inMemoryAccessToken
    }

    fun getRefreshToken(): String {
        if (inMemoryRefreshToken.isEmpty()) {
            inMemoryRefreshToken = sharedPref.getString(SharedPrefKey.KEY_REFRESH_TOKEN, "")
        }
        return inMemoryRefreshToken
    }

}