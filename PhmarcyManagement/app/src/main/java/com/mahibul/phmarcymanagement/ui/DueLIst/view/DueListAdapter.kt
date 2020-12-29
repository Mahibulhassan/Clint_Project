package com.mahibul.phmarcymanagement.ui.DueLIst.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.Customer

class DueListAdapter(
    private val customerList : MutableList<Customer>,
    private val clickListener : CustomerClickListner
) : RecyclerView.Adapter<DueListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DueListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.due_item_view,parent,false)
        return DueListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DueListViewHolder, position: Int) {
        val customer = customerList[position]
        holder.due_nameTextView.text = customer.customer_name
        holder.due_priceTextView.text = customer.customer_due.toString()

        holder.due_btnDelete.setOnClickListener {
            clickListener.onDeleteButtonClicked(customer.customer_name)
        }
        holder.due_btnEdit.setOnClickListener {
            clickListener.onEditButtonClicked(customer.customer_name,customer.customer_due)
        }

    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    fun replaceData(customerList : MutableList<Customer>){
        this.customerList.clear()
        this.customerList.addAll(customerList)
        notifyDataSetChanged()
    }

    interface CustomerClickListner{
        fun onEditButtonClicked(customer_name: String,customer_due: Int)
        fun onDeleteButtonClicked(customer_name: String)
    }
}