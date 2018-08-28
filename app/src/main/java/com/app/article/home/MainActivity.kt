package com.app.article.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.app.article.R
import com.app.article.adapter.MyAdapter
import com.app.article.model.Article
import com.app.article.restservices.APIService
import com.app.article.restservices.ApiUtils
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var apiServices: APIService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiServices= ApiUtils.apiService
        recycler_view.layoutManager = LinearLayoutManager(this)

        initJson()
    }


    private fun initJson(){

        progress.visibility= View.VISIBLE

        apiServices!!.getCustomerAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result-> val data=result.articles
                            val datas=Gson().toJson(data)
                            Log.d("TAHS","Response JSON = $datas")
                            recycler_view.adapter= MyAdapter(data as List<Article>)
                            progress.visibility= View.GONE

                        },
                        { error ->
                            Log.d("TAHS","ERROR ${error.message }")
                            progress.visibility= View.GONE

                        }
                )
    }
}
