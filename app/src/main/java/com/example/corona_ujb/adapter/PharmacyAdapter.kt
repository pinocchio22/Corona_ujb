package com.example.corona_ujb.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.corona_ujb.DialogViewModel
import com.example.corona_ujb.model.PharmacyItems
import com.example.corona_ujb.R
import kotlinx.android.synthetic.main.pharmacy_itemview.view.*

/**
 * @author CHOI
 * @email vviian.2@gmail.com
 * @created 2021-09-17
 * @desc
 */

class PharmacyAdapter(vm: DialogViewModel) : RecyclerView.Adapter<PharmacyAdapter.ViewHolder>() {
    var items = ArrayList<PharmacyItems>()
    val vm = vm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.pharmacy_itemview, parent, false)
        return ViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        fun bind(pos: Int) {
            itemView.pharmacy_name.text = items[pos].title
            itemView.setOnClickListener {
                vm.click.value = pos
            }
            itemView.maplink.setOnClickListener {
                vm.clickMap.value = pos
            }
        }
    }
    fun addItem(items : ArrayList<PharmacyItems>){
        this.items = items
        notifyDataSetChanged()
    }

}
