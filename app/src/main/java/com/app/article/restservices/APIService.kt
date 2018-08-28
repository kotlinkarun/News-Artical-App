package com.app.article.restservices
import com.app.article.model.ResponseModels
import io.reactivex.Observable
import retrofit2.http.GET


interface APIService {
    @GET("/v2/top-headlines?country=us&category=business&apiKey=a8c4a8a84e3c45b6bd291ab7a355cf73")
    fun getCustomerAll(): Observable<ResponseModels>
}