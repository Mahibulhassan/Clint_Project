package com.mahibul.phmarcymanagement.ui.sell_byDay.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.dailysell_item_view.view.*

class DailysellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameTextView = itemView.dsell_nameTextView as TextView
    val priceTextView = itemView.dsell_priceTextView as TextView
    val btnEdit = itemView.dsell_btnEdit as MaterialButton
    val btnDelete = itemView.dsell_btnDelete as MaterialButton
}