package com.example.corona_ujb

import android.annotation.SuppressLint

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


import java.util.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class MapViewModel(): ViewModel() {

    private val repo: CoronaRepository =
        CoronaRepository()
    var maskLatlngList: MutableLiveData<ArrayList<MaskLatlon>> =
        MutableLiveData<ArrayList<MaskLatlon>>()
    var List: ArrayList<MaskLatlon> = arrayListOf()
    var legacylat: Double = 35.157662
    var legacylng: Double = 129.059111

    @SuppressLint("CheckResult")
    fun getMaskLatlng(lat: Double, lng: Double) {
        List.clear()
        maskLatlngList.value = arrayListOf(MaskLatlon("","","","","",0.0,0.0,"",""))
        repo.getMaskLatlng(lat, lng)?.subscribe { ResultGetMaskData ->
            for (i in ArrayList(ResultGetMaskData.stores).indices) {
                if(ResultGetMaskData.stores[i].remain_stat != "break") {
                    List.add(ResultGetMaskData.stores[i])
                    maskLatlngList.value = List
                }
            }
        }
    }
}