package com.example.corona_ujb

import android.annotation.SuppressLint
import android.net.Uri
import android.text.Html
import android.text.Spanned
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class SearchViewModel(): ViewModel(){
    var newsItem: ArrayList<NewsItems> = arrayListOf()
    private val repo : CoronaRepository =
        CoronaRepository()
    private var newsAdapter = NewsAdapter(this)
    var n = 1
    var max = 10
    var check = 0
    var searchType: MutableLiveData<String> = MutableLiveData<String>("")
    var uri: MutableLiveData<Uri> = MutableLiveData<Uri>("".toUri())

    init {
        getNews("코로나 의정부 확진")
    }

    fun getNews(str: String) {
        check = 1
        searchType.value = when {
            str.contains("의정부") -> {
                "의정부"
            }
            str.contains("세계") -> {
                "세계"
            }
            else -> {
                "국내"
            }
        }
        getNewsItem(str)
    }

    @SuppressLint("CheckResult")
    fun getNewsItem(str: String) {
        repo.getNews(n, str).subscribe(
            { ResultGetSearchNews ->
                for(i in ArrayList(ResultGetSearchNews.items).indices) {
                    if (ResultGetSearchNews.items[i].title.contains(searchType.value!!)) {
                        newsItem.add(ResultGetSearchNews.items[i])
                        newsAdapter.notifyDataSetChanged()
                    }
                    n++
                    if (newsItem.size < max && i == ResultGetSearchNews.items.size - 1) {
                        getNewsItem(str)
                    } else if (newsItem.size >= max) {
                        max += 10
                        break
                    }
                }
            }

            , { throwable -> Log.d("Error!"," ") })
    }
    fun getAdapter(): NewsAdapter{
        return newsAdapter
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
        uri.setValue(Uri.parse(newsItem.get(pos).link))
    }

    fun dateFormat(str: String): String {

        val formatterCal = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US)
        val date: Date = formatterCal.parse(str) // all done

        val formatterStr = SimpleDateFormat("yyyy년 MM월 dd일 (E) / HH:mm", Locale.KOREAN)
        val strDate = formatterStr.format(date)
        return strDate
    }
}