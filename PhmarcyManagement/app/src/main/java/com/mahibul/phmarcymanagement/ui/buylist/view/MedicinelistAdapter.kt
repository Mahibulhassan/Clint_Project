package com.mahibul.phmarcymanagement.ui.buylist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData

class MedicinelistAdapter(
        private val medicineDataList : MutableList<BuyMedicineData>,
        private val clicklistner : MedicineListClickListener
        ):RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.medicine_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val medicine = medicineDataList[position]
        holder.nameTextView.text=medicine.name
        holder.priceTextView.text=medicine.price.toString()
        holder.unitTextView.text=medicine.unit.toString()

        holder.btnDelete.setOnClickListener {
            clicklistner.onDeleteButtonClicked(medicine.name!!)
        }
        holder.btnEdit.setOnClickListener {
            clicklistner.onEditButtonClicked(medicine.name!!, medicine.unit!!)
        }
    }

    override fun getItemCount(): Int {
        return medicineDataList.size
    }

    fun replaceData(medicineDataList : MutableList<BuyMedicineData>){
        this.medicineDataList.clear()
        this.medicineDataList.addAll(medicineDataList)
        notifyDataSetChanged()
    }

    interface MedicineListClickListener {
        fun onEditButtonClicked(medicine_name: String,medicine_units : Int)
        fun onDeleteButtonClicked(medicine_name: String)
    }
}