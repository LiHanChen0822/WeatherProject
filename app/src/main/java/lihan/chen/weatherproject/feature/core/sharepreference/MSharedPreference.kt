package lihan.chen.weatherproject.feature.core.sharepreference

interface MSharedPreference {
    fun setEmail(email : String)
    fun getEmail() : String
    fun setPassword(password : String)
    fun getPassword() : String
    fun clearAll()
}