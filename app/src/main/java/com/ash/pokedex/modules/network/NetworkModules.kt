package com.ash.pokedex.modules.network

import com.ash.pokedex.BuildConfig
import com.ash.pokedex.services.PokemonServices
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    private val jsonChecker by lazy {
        ResponseJsonChecker
    }
    private val timeOut: Int by lazy {
        60
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
    ): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(jsonChecker)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkhttpClient(
    ): OkHttpClient {
        val builder =
            OkHttpClient.Builder()
                .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
                .addInterceptor(
                    HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY),
                )
        return builder.build()
    }

    @Provides
    @Singleton
    fun providePokemonServices(retrofit: Retrofit): PokemonServices {
        return retrofit.create(PokemonServices::class.java)
    }
}