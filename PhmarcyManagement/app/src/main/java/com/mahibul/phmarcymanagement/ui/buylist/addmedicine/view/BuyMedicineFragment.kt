   package com.mahibul.phmarcymanagement.ui.buylist.addmedicine.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModelImp
import com.mahibul.phmarcymanagement.ui.buylist.addmedicine.viewmodel.AddMedicineFactory
import com.mahibul.phmarcymanagement.ui.buylist.addmedicine.viewmodel.AddMedicineViewModel
import kotlinx.android.synthetic.main.fragment_buy_medicine_fragment.*

class BuyMedicineFragment : DialogFragment(){

    private val model by lazy { BuyModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory = AddMedicineFactory(model)
        ViewModelProvider(this,factory).get(AddMedicineViewModel::class.java)
    }
    private lateinit var dataChangeListner : DataChangeLIstner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataChangeLIstner){
            dataChangeListner= context
        }else{
            throw ClassCastException("Caller class must implement BuyCrudListener interface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_buy_medicine_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Add Medicine ")

        createButton.setOnClickListener {
            val name =medicine_name.text.toString()
            val price = price_medicine.text.toString()
            val unit = unit_medicine.text.toString()

            if (name.isEmpty() || price.isEmpty() || unit.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val buymedicine = BuyMedicineData(name = name, price = price.toInt(), unit = unit.toInt())

            viewModel.addMedicine(buymedicine)
        }
        cancelButton.setOnClickListener {
            dismiss()
        }

        viewModel.medicineCreateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.medicineCreateFailedLiveData.observe(this,{
            dataChangeListner.onDataSetChangeError(it)
        })
    }

    override fun onStart() {
        super.onStart()

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

}