package com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.view

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
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModelImp
import com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.viewmodel.EditMedicineViewModel
import com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.viewmodel.EditModelFactory
import kotlinx.android.synthetic.main.fragment_edit.*


class EditFragment : DialogFragment() {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { BuyModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
         val factory = EditModelFactory(model)
        ViewModelProvider(this,factory).get(EditMedicineViewModel::class.java)
    }

    private lateinit var dataChangeListner : DataChangeLIstner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataChangeLIstner){
            dataChangeListner= context
        }else{
            throw ClassCastException("Caller class must implement StudentCrudListener interface")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPreferance = AppPreferenceImp(requireContext().applicationContext)
        val medicine_name = appPreferance.getName(AppPreference.Name)
        val medicine_units = appPreferance.getUnits(AppPreference.Units)
        dialog?.setTitle(medicine_name)

        updateButton.setOnClickListener {
            val price = price_new_medicine.text.toString()
            val unit = unit_new_medicine.text.toString()
            val name = medicine_name

            if (name!!.isEmpty() || price.isEmpty() || unit.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val updateUnits = medicine_units?.plus(unit.toInt())
            val updateMedicine = BuyMedicineData(name = name!!,price = price.toInt(),unit = updateUnits!!)
            viewModel.updateMedicine(updateMedicine)
        }

        cancelbButton.setOnClickListener {
            dismiss()
        }
        viewModel.MedicineUpdateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.MedicineUpdateFailedLiveData.observe(this,{
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