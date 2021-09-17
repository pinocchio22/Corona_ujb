package com.example.corona_ujb

import android.annotation.SuppressLint
import android.app.Application
import android.net.Uri
import android.os.Looper
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Handler
import kotlin.collections.ArrayList

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */
class CoronaViewModel(application: Application): AndroidViewModel(application) {
    private var newsResult: MutableLiveData<ResultGetSearchNews> = MutableLiveData()
    private var newsItem: ArrayList<NewsItems> = arrayListOf()
    private val repo : CoronaRepository = CoronaRepository(application)
    private var newsAdapter = NewsAdapter(this)
    var n = 1

    var uri: MutableLiveData<Uri> = MutableLiveData<Uri>()

    @SuppressLint("CheckResult")
    fun getNews() {

        //newsResult = repo.getNews()
        repo.getNews(n).subcribe(
            { ResultGetSearchNews ->
                for (i in ArrayList(ResultGetSearchNews.item).indices) {
                    if (ResultGetSearchNews.item[i].title.contains("의정부") && ResultGetSearchNews.item[i].title.contains("코로나")) {
                        newsItem.add(ResultGetSearchNews.item[i])

                        Handler(Looper.getMainLooper()).post()
                        { newsAdapter.notifyDataSetChanged()} }
                        if (newsItem.size < 30) {
                            n = n + 100
                            getNews()
                        } else
                            n = 1
                }
            } , {throwable -> Log.d("error"," ")})
    }

    fun getTitle(pos: Int): Spanned {
        return Html.fromHtml(newsItem.get(pos).title)
    }

    fun getDate(pos: Int): String {
        return dateFormat(newsItem.get(pos).pubDate)
    }

    fun getNewsItem(): List<NewsItems> {
        return newsItem
    }

    fun toUri(pos: Int) {
        uri.setValue(Uri.parse(newItem.get(pos).originallink))
    }

    fun viewInit(recyclerView: RecyclerView) {
        recyclerView.adapter = newsAdapter
        recyclerView.layoutManager = LinearLayoutManager(getApplication())
    }
    fun dateFormat(str: String): String {

        val formatterCal = SimpleDateFormat("Em dd MMM yyyy HH:mm:ss Z", Locale.US)
        val date: Date = formatterCal.parse(str) // all done

        val formatterStr = SimpleDateFormat("yyyy년 MM월 dd일 (E) / HH:mm:ss", Locale.KOREAN)
        val strDate = formatterStr.format(date)
        return strDate
    }


}