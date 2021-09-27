package com.example.corona_ujb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.board_itemview.view.*

class BoardAdapter(vm: BoardViewModel) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {
    var items = ArrayList<CoronaPeople>()
    val vm = vm

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.board_itemview, parent, false)
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
            itemView.ujb_name.text = items[pos].name
            itemView.ujb_route.text = items[pos].route
            itemView.ujb_num.text = items[pos].num
            itemView.ujb_hospital.text = items[pos].hospital
            itemView.ujb_date.text = items[pos].date
            itemView.setOnClickListener {
                vm.clickPeople.value = pos
            }
        }
    }
    fun addItem(items : ArrayList<CoronaPeople>){
        this.items = items
        notifyDataSetChanged()
    }

}