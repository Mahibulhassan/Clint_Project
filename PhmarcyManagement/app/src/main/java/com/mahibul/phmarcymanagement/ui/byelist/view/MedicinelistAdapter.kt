package com.mahibul.phmarcymanagement.ui.byelist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyMedicine

class MedicinelistAdapter(private val medicineList : MutableList<BuyMedicine>):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicine_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = medicineList[position]
        holder.nameTextView.text=medicine.name
        holder.priceTextView.text=medicine.price.toString()
        holder.unitTextView.text=medicine.unit.toString()
    }

    override fun getItemCount(): Int {
        return medicineList.size
    }

    fun replaceData(medicineList : MutableList<BuyMedicine>){
        this.medicineList.clear()
        this.medicineList.addAll(medicineList)
        notifyDataSetChanged()
    }
}