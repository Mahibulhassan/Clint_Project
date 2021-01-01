package com.mahibul.phmarcymanagement.ui.selllist.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.sell_item_view.view.*

class SellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val name_TextView = itemView.name_TextView as TextView
    val price_TextView = itemView.price_TextView as TextView
    val unit_TextView = itemView.unit_TextView as TextView

}