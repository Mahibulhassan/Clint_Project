package com.mahibul.phmarcymanagement.ui.sell_byDay.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell

class DailySellAdapter (
    private val dailysellDataList : MutableList<DailySell>,
    private val clicklistner : dailysellListClickListener
): RecyclerView.Adapter<DailysellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailysellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.dailysell_item_view,parent,false)
        return DailysellViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailysellViewHolder, position: Int) {
        val item = dailysellDataList[position]
        holder.nameTextView.text = item.name
        holder.priceTextView.text = item.price.toString()
        holder.btnDelete.setOnClickListener {
            clicklistner.onDeleteButtonClicked(item.id)
        }
        holder.btnEdit.setOnClickListener {
            clicklistner.onEditButtonClicked(item.id)
        }
    }

    override fun getItemCount(): Int {
        return dailysellDataList.size
    }

    fun replaceData(dailysellDataList : MutableList<DailySell>){
        this.dailysellDataList.clear()
        this.dailysellDataList.addAll(dailysellDataList)
        notifyDataSetChanged()
    }

    interface dailysellListClickListener {
        fun onEditButtonClicked(id:Long)
        fun onDeleteButtonClicked(id: Long)
    }
}