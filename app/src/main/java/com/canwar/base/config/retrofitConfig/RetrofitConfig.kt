package com.canwar.base.config.retrofitConfig

import com.canwar.base.BuildConfig
import com.canwar.base.config.Config
import com.canwar.base.dataSource.ApiService
import com.canwar.base.dataSource.ApiServiceAuthorization
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
class RetrofitConfig {

    fun provideRequestInterceptor(isUseAuthorization: Boolean) = Interceptor { chain ->
        // use this to header api key
        // only for key in header
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("key", BuildConfig.API_KEY)

        if (isUseAuthorization) {
            // use this to header authorization
            // only for key in header
            request.addHeader("Authorization", "Bearer ${Config.provideTokenAuthorization()}")
        }


        return@Interceptor chain.proceed(request.build())
    }

    fun provideOkHttpClient(isUseAuthorization: Boolean): OkHttpClient {
        val requestInterceptor: Interceptor = provideRequestInterceptor(isUseAuthorization)
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
        gson: Gson,
    ): ApiService {
        val okHttpClient: OkHttpClient = provideOkHttpClient(false)
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideServiceWithAuthorization(
        gson: Gson
    ): ApiServiceAuthorization {
        val okHttpClient: OkHttpClient = provideOkHttpClient(true)
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiServiceAuthorization::class.java)
    }

}