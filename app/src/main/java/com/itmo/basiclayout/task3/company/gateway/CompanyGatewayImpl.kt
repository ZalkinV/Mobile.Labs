package com.itmo.basiclayout.task3.company.gateway

import com.google.gson.GsonBuilder
import com.itmo.basiclayout.task3.company.Consts
import com.itmo.basiclayout.task3.company.domain.CompanyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class CompanyGatewayImpl : CompanyGateway{

    companion object {
        const val PATH_SEGMENT = "company"
    }

    private val httpClient = OkHttpClient()
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

            gson.fromJson(responseBody, CompanyApiModel::class.java)
        }

        company.toEntity()
    }
}
