package com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.view

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
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModelImp
import com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.viewmodel.EditDailySellViewModel
import com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.viewmodel.EditDailysellFactory
import kotlinx.android.synthetic.main.fragment_edit_daily_sell.*

class EditDailySell : DialogFragment(){
    private lateinit var appPreferance: AppPreference
    private val model by lazy { DailySellModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory = EditDailysellFactory(model)
        ViewModelProvider(this,factory).get(EditDailySellViewModel::class.java)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_daily_sell, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Edit Name and Price")
        appPreferance = AppPreferenceImp(requireContext().applicationContext)
        val product_id = appPreferance.getId(AppPreference.id)
        sell_updateButton.setOnClickListener {
            val name = product_new_name.text.toString()
            val price = product_new_price.text.toString()
            val id = product_id
            if (name.isEmpty() || price.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val data = DailySell(id = id!!,name = name,price = price.toInt())
            viewModel.updateDailySell(data)
        }
        sell_new_cancelbButton.setOnClickListener {
            dismiss()
        }
        viewModel.dailySellUpdateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.dailySellUpdateFailedLiveData.observe(this,{
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