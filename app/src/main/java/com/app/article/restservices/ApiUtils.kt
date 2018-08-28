package com.app.article.restservices

object ApiUtils {

    val BASE_URL="https://newsapi.org"
    val apiService: APIService
        get() = RetrofitClient.getClient(BASE_URL).create(APIService::class.java)
}