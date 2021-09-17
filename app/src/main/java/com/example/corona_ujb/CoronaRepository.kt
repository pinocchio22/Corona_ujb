package com.example.corona_ujb

import android.app.Application
import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
class CoronaRepository(application: Application) {

    private val api = NaverAPI.create()
    private var newsData: MutableLiveData<ResultGetSearchNews> = MutableLiveData()

    fun getNews(n: Int): Observable<ResultGetSearchNews> = api
        .getSearchNews("코로나 의정부", 100, n)
        .subscribeOn(Schedulers.io())
        .obserceOn(AndroidSchedulers.mainThread())
}