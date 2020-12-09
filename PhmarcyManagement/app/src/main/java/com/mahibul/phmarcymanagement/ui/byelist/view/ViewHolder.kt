package com.mahibul.phmarcymanagement.ui.byelist.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.medicine_item_view.view.*

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView = itemView.nameTextView as TextView
    val priceTextView = itemView.priceTextView as TextView
    val unitTextView = itemView.unitTextView as TextView
}