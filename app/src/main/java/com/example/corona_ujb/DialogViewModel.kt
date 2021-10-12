package com.example.corona_ujb

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_ujb.model.CoronaRepository
import com.example.corona_ujb.model.PharmacyItems
import com.example.corona_ujb.adapter.PharmacyAdapter

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class DialogViewModel(): ViewModel(){
    private val repo : CoronaRepository =
        CoronaRepository()
    var pharmacyList: MutableLiveData<ArrayList<PharmacyItems>> = MutableLiveData<ArrayList<PharmacyItems>>()
    private var pharmacyAdapter =
        PharmacyAdapter(this)
    var click: MutableLiveData<Int> = MutableLiveData<Int>(-1)
    var clickMap: MutableLiveData<Int> = MutableLiveData<Int>(-1)

    init {
        getPharmacy()
    }
    @SuppressLint("CheckResult")
    fun getPharmacy() {
        repo.getPharmacy().subscribe {
                list -> pharmacyList.value = list
        }
    }

    fun init(recyclerView: RecyclerView, adapter: PharmacyAdapter) {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

    fun getAdapter(): PharmacyAdapter = pharmacyAdapter
}