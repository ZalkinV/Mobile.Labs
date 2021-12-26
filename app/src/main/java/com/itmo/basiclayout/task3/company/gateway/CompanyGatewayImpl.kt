package com.itmo.basiclayout.task3.company.gateway

import com.google.gson.GsonBuilder
import com.itmo.basiclayout.task3.company.Consts
import com.itmo.basiclayout.task3.company.domain.CompanyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class CompanyGatewayImpl : CompanyGateway{

    companion object {
        const val PATH_SEGMENT = "company"
    }

    // OkHTtpLoggingInterceptor: https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor
    // https://square.github.io/okhttp/interceptors/
    // https://howtodoinjava.com/retrofit2/logging-with-retrofit2/
    private val interceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BASIC)
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    private val gson = GsonBuilder()
        .create()

    // Suppress as it is wrong in withContext: https://stackoverflow.com/questions/58680028/how-to-make-inappropriate-blocking-method-call-appropriate
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun get(id: Int): CompanyEntity = withContext(Dispatchers.IO) {

        val requestUrl = Consts.COMPANY_GATEWAY_BASE_URL.toHttpUrl().newBuilder()
            .addPathSegment(PATH_SEGMENT)
            .addQueryParameter("id", id.toString())
            .build()

        val request = Request.Builder()
            .url(requestUrl)
            .get()
            .build()

        val company = httpClient.newCall(request).execute().use { response ->
            if (!response.isSuccessful)
                throw IOException("No response from API")

            val responseBody = response.body?.string()
                ?: throw IOException("No response body from API")

            gson.fromJson(responseBody, CompanyApiResponse::class.java)
        }

        company.toEntity()
    }
}
