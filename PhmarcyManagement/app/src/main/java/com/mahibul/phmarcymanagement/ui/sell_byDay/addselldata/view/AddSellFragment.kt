package com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.view

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
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModelImp
import com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.viewmodel.AddSellViewFactory
import com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.viewmodel.AddSellViewModel
import kotlinx.android.synthetic.main.fragment_add_sell.*

class AddSellFragment : DialogFragment() {

    private val model by lazy { DailySellModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory = AddSellViewFactory(model)
        ViewModelProvider(this,factory).get(AddSellViewModel::class.java)
    }
    private lateinit var dataChangeListner : DataChangeLIstner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataChangeLIstner){
            dataChangeListner= context
        }else{
            throw ClassCastException("Caller class must implement  interface")
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_sell, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Add Sell Product ")
        sell_createButton.setOnClickListener { 
            val name =product_name.text.toString()
            val price = product_price_medicine.text.toString()
            if (name.isEmpty() || price.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            
            val data = DailySell(name = name,price = price.toInt())
            viewModel.addSellItem(data)
        }
        viewModel.dailysellCreateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.dailysellCreateFailedLiveData.observe(this,{
            dataChangeListner.onDataSetChangeError(it)
        })
        
        sell_cancelButton.setOnClickListener { 
            dismiss()
        }
        
    }

    override fun onStart() {
        super.onStart()

        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }
}