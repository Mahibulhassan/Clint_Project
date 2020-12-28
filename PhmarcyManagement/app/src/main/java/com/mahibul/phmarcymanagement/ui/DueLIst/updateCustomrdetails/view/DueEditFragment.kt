package com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.view

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
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.Customer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModelImp
import com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.viewModel.DueEditModelFactory
import com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.viewModel.DueEditViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_due_edit.*

class DueEditFragment : DialogFragment() {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { DueListModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory = DueEditModelFactory(model)
        ViewModelProvider(this, factory).get(DueEditViewModel::class.java)
    }
    private lateinit var dataChangeListner: DataChangeLIstner

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataChangeLIstner) {
            dataChangeListner = context
        } else {
            throw ClassCastException("Caller class must implement StudentCrudListener interface")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_due_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        appPreferance = AppPreferenceImp(requireContext().applicationContext)
        val customer_name = appPreferance.getName(AppPreference.Name)
        val customer_price = appPreferance.getPrice(AppPreference.price)
        dialog?.setTitle(customer_name)
        due_updateButton.setOnClickListener {
            val name = customer_name
            val price = price_new_due.text.toString()
            if (name!!.isEmpty() || price.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            var updatePrice = customer_price!!
            if (radio_groupid.checkedRadioButtonId != -1) {
                if (due_radioid.isChecked)
                    updatePrice = customer_price.plus(price.toInt())
                else
                    updatePrice = customer_price.minus(price.toInt())
            }
            val customerUpdate = Customer(customer_name = name, customer_due = updatePrice)
            viewModel.updateMedicine(customerUpdate)
        }

        due_cancelbButton.setOnClickListener {
            dismiss()
        }
        viewModel.CustomerUpdateLiveData.observe(this, {
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.CustomerUpdateFailedLiveData.observe(this, {
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