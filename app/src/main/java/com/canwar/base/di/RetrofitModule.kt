package com.canwar.base.di

import com.canwar.base.BuildConfig
import com.canwar.base.config.Config
import com.canwar.base.core.data.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    fun provideRequestInterceptor() = Interceptor { chain ->
        // use this to header api key
        // only for key in header
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("key", BuildConfig.API_KEY)

        return@Interceptor chain.proceed(request.build())
    }

    @Provides
    fun provideOkHttpClient(requestInterceptor: Interceptor): OkHttpClient {
        if (BuildConfig.DEBUG) {
            /* Enable logging in build debug */
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            return OkHttpClient.Builder()
                .connectTimeout(Config.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(requestInterceptor)
                .addInterceptor(loggingInterceptor)
                .build()
        } else {
            /* Disable Logging for Release */
            return OkHttpClient.Builder()
                .connectTimeout(Config.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(requestInterceptor)
                .build()
        }
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .serializeNulls()
        .setPrettyPrinting()
        .setLenient()
        .create()


    @Provides
    @Singleton
    fun provideServiceWithoutAuthorization(
        okHttpClient: OkHttpClient,
        gson: Gson,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

}