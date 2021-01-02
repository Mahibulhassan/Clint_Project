package com.mahibul.phmarcymanagement.ui.selllist.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellMedicine


class SellAdapter(
    private val medicineDataList : MutableList<SellMedicine>,
    private val clicklistner : ListClickListener
) : RecyclerView.Adapter<SellViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sell_item_view,parent,false)
        return SellViewHolder(view)
    }

    override fun onBindViewHolder(holder: SellViewHolder, position: Int) {
        val medicine = medicineDataList[position]

        holder.name_TextView.text=medicine.name
        holder.price_TextView.text=medicine.price.toString()
        holder.unit_TextView.text=medicine.unit.toString()
        holder.itemView.setOnClickListener {
            clicklistner.onMedicineClicked(medicine.name,medicine.unit,medicine.price)
        }

    }

    override fun getItemCount(): Int {
        return medicineDataList.size
    }
    fun replaceData(medicineDataList : MutableList<SellMedicine>){
        this.medicineDataList.clear()
        this.medicineDataList.addAll(medicineDataList)
        notifyDataSetChanged()
    }

    interface ListClickListener {
        fun onMedicineClicked(medicine_name: String,medicine_units : Int,medcicine_price : Int)
    }
}