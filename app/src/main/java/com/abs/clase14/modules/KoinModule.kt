package com.abs.clase14.modules

import androidx.room.Room
import com.abs.clase14.configuration.BASE_URL
import com.abs.clase14.configuration.DB_NAME
import com.abs.clase14.model.Database
import com.abs.clase14.networking.GifAPI
import com.abs.clase14.service.GifService
import com.abs.clase14.service.GifServiceType
import com.abs.clase14.ui.history.GifHistoryViewModel
import com.abs.clase14.ui.main.MainViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val appModule = module {
    single { Room.databaseBuilder(get(), Database::class.java, DB_NAME).build()}
    single { get<Database>().gifDao() }
    factory<GifServiceType> { GifService(get(),get())}
    viewModel { MainViewModel(get()) }
    viewModel { GifHistoryViewModel(get()) }
}

val networkModule = module {
    factory { provideOkHttpClient() }
    factory { provideKitchenConnectApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return  Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.HEADERS
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient
        .Builder()
        .addInterceptor(object: Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("api_key", "rdsQpyhByexQhk59OFNaF4ypLAXufKY1")
                    .build()
                return chain.proceed(newRequest)
            }
        })
        .build()
}

fun provideKitchenConnectApi(retrofit: Retrofit): GifAPI = retrofit.create(GifAPI::class.java)
