package com.kevin.zooapidemo.Network

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import kotlinx.serialization.json.Json
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit


object HttpApiClient{

    var client: OkHttpClient? = null
    var retrofit: Retrofit? = null
    var api_Url: API_Url? = null

    fun getInstance(): API_Url {
        initialize()
        if (api_Url == null) {
            api_Url = getRetrofita().create(API_Url::class.java)
        }
        return api_Url!!/* return getRetrofit().create(API_Url.class) */
    }



    fun getRetrofita(): Retrofit {
        initialize()
        return retrofit!!
    }

    fun initialize() {

        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient().newBuilder()
            .readTimeout((180 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout((180 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .connectTimeout((180 * 1000).toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(logging)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(API_Url.BaseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            //.addConverterFactory(TikXmlConverterFactory.create())
            //.addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(client)
            .build()
    }


}

