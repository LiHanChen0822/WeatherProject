package lihan.chen.weatherproject.feature.login.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import lihan.chen.weatherproject.feature.core.sharepreference.DefaultSharedPreference
import lihan.chen.weatherproject.feature.core.sharepreference.MSharedPreference
import lihan.chen.weatherproject.feature.login.data.repository.LoginRepositoryImpl
import lihan.chen.weatherproject.feature.login.domain.repository.LoginRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {

    @Singleton
    @Provides
    fun provideSharedPreference(
        @ApplicationContext context : Context
    ) : MSharedPreference = DefaultSharedPreference(context)

    @Singleton
    @Provides
    fun provideLoginRepository() : LoginRepository = LoginRepositoryImpl()

}