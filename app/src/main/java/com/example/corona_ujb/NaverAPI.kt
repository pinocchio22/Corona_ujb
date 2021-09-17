package com.example.corona_ujb

import java.util.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
interface NaverAPI {

    @GET("v1/search/news.json")
    fun getSearchNews(
        @Query("query") query: String,
        @Query("display") display: Int? = null,
        @Query("start") start: Int? = null
    ): Observable<ResultGetSearchNews>

    companion object {
        private const val BASE_URL_NAVER_API = "https://openapi.naver.com"
        private const val CLIENT_ID = "KPFtLtMd1aMnsMoweK6n"
        private const val CLIENT_SECRET = "X5HdAYeAb6"

        fun create(): NaverAPI {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                    .build()
                return@Interceptor it.proceed(request)
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL_NAVER_API)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NaverAPI::class.java)
        }
    }
}