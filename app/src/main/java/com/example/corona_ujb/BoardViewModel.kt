package com.example.corona_ujb

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_ujb.adapter.BoardAdapter
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class BoardViewModel(): ViewModel(){
    private val repo : CoronaRepository =
        CoronaRepository()
    @SuppressLint("SimpleDateFormat")
    val sdfFormat: SimpleDateFormat = SimpleDateFormat("MM월 dd일 (E) HH:mm:ss")
    var nowTimer: MutableLiveData<String> = MutableLiveData<String>("")

    var ujbList: MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()
    var koreaList: MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()
    var worldList: MutableLiveData<ArrayList<String>> = MutableLiveData<ArrayList<String>>()
    var peopleList: MutableLiveData<ArrayList<CoronaPeople>> = MutableLiveData<ArrayList<CoronaPeople>>()
    var clickPeople: MutableLiveData<Int> = MutableLiveData<Int>(-1)
    var checkGetPeople: Int = 0

    private var boardAdapter =
        BoardAdapter(this)

    init {
        getBoard()
        getUjbNum()
    }
    @SuppressLint("CheckResult")
    fun getBoard() {
        val temp = arrayListOf<String>("","","","","")
        ujbList.value = temp
        koreaList.value = temp
        worldList.value = temp

        ujbList = repo.getUjb()
        koreaList = repo.getKorea()
        worldList = repo.getWorld()
    }

    fun getNowTime() {
        val time = sdfFormat.format(Date(System.currentTimeMillis()))
        Handler(Looper.getMainLooper()).post { nowTimer.value = time }
    }

    @SuppressLint("CheckResult")
    fun getUjbNum() {
        peopleList = repo.getUjbNum()
    }

    fun init(recyclerView: RecyclerView, adapter: BoardAdapter) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    fun getAdapter(): BoardAdapter = boardAdapter

}