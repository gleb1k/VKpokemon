package ru.glebik.core.network.di


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

private const val BASE_URL = "https://pokeapi.co/api/v2/"
private const val CONTENT_TYPE = "application/json; charset=UTF8"

val networkModule = module {

    single<Json> {
        Json { ignoreUnknownKeys = true }
    }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addNetworkInterceptor(get<HttpLoggingInterceptor>())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(BASE_URL)
            .addConverterFactory(
                get<Json>().asConverterFactory(
                    CONTENT_TYPE.toMediaType()
                )
            )
            .build()
    }

}




