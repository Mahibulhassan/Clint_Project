package com.mahibul.phmarcymanagement.ui.selllist.sellMedicine.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreference
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreferenceImp
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellListModelImp
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellMedicine
import com.mahibul.phmarcymanagement.ui.selllist.sellMedicine.viewmodel.SellViewModel
import com.mahibul.phmarcymanagement.ui.selllist.sellMedicine.viewmodel.SellViewModelFactory
import kotlinx.android.synthetic.main.fragment_sell_medicine_update.*

class SellMedicineUpdate : DialogFragment() {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { SellListModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory = SellViewModelFactory(model)
        ViewModelProvider(this,factory).get(SellViewModel::class.java)
    }
    private lateinit var dataChangeListner : DataChangeLIstner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataChangeLIstner){
            dataChangeListner= context
        }else{
            throw ClassCastException("Caller class must implement in interface")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sell_medicine_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPreferance = AppPreferenceImp(requireContext().applicationContext)
        val medicine_name = appPreferance.getName(AppPreference.Name)
        val medicine_units = appPreferance.getUnits(AppPreference.Units)
        val medicine_price = appPreferance.getPrice(AppPreference.price)
        dialog?.setTitle(medicine_name)
        cancel_button.setOnClickListener {
            dismiss()
        }
        sell_button.setOnClickListener {
            val name = medicine_name
            val price = medicine_price.toString()
            val sell_units =units_medicine.text.toString()

            if (name!!.isEmpty() || price.isEmpty() || sell_units.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            //Here Update medicine Units.........

            val update_unites = medicine_units?.minus(sell_units.toInt())
            val updateData = SellMedicine(name = name,price = price.toInt(),unit = update_unites!!)
            viewModel.updateMedicine(updateData)
            //Here  daily sell Implement

            val daily_price = sell_units.toInt()*price.toInt()
            val daily_sell = DailySell(name = name,price = daily_price)
            viewModel.createDailysell(daily_sell)
        }
        viewModel.medicineUpdateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.medicineUpdateFailedLiveData.observe(this,{
            dataChangeListner.onDataSetChangeError(it)
        })
        viewModel.dailiSellCreateFailedLiveData.observe(this,{
            dataChangeListner.onDataSetChangeError(it)
        })
        viewModel.dailySellCreateLiveData.observe(this,{
            Toast.makeText(requireContext(),"Data Added",Toast.LENGTH_SHORT).show()
        })
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

}

