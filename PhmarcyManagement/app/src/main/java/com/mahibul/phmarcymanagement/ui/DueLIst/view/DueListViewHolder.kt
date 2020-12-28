package com.mahibul.phmarcymanagement.ui.DueLIst.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.due_item_view.view.*

class DueListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val due_nameTextView = itemView.due_nameTextView as TextView
    val due_priceTextView = itemView.due_priceTextView as TextView
    val due_btnEdit = itemView.due_btnEdit as MaterialButton
    val due_btnDelete = itemView.due_btnDelete as MaterialButton
}