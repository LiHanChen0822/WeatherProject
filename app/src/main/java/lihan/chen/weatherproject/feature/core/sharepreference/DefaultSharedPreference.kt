package lihan.chen.weatherproject.feature.core.sharepreference

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class DefaultSharedPreference(
    context : Context
) : MSharedPreference {

    companion object{
        private const val EMAIL_KEY = "emailKey"
        private const val PASSWORD_KEY = "passwordKey"
    }

    private val sharedPreferences : SharedPreferences = EncryptedSharedPreferences.create(
        "mydata",
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM,
    )

    override fun setEmail(email: String) {
        sharedPreferences.edit().putString(EMAIL_KEY,email).apply()
    }

    override fun getEmail(): String {
        return sharedPreferences.getString(EMAIL_KEY,"")?:""
    }

    override fun setPassword(password: String) {
        sharedPreferences.edit().putString(PASSWORD_KEY,password).apply()
    }

    override fun getPassword(): String {
        return sharedPreferences.getString(PASSWORD_KEY,"")?:""
    }

    override fun clearAll() {
        setEmail("")
        setPassword("")
    }
}