package com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.view

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
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DataCustomer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModelImp
import com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.viewmodel.AddCustomerFactory
import com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.viewmodel.AddCustomerViewModel
import kotlinx.android.synthetic.main.fragment_due_customer.*


class DueCustomerFragment : DialogFragment() {
    private val model by lazy { DueListModelImp(requireContext().applicationContext) }
    private val viewModel by lazy {
        val factory =AddCustomerFactory(model)
        ViewModelProvider(this,factory).get(AddCustomerViewModel::class.java)
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
        return inflater.inflate(R.layout.fragment_due_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.setTitle("Add Due List")
        due_createButton.setOnClickListener {
            val name = Customer_name.text.toString()
            val price =Customer_price.text.toString()
            if (name.isEmpty() || price.isEmpty()) {
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val customer = DataCustomer(customer_name = name , customer_due = price.toInt())
            viewModel.addCustomer(customer)
        }
        due_cancelButton.setOnClickListener {
            dismiss()
        }
        viewModel.customerCreateLiveData.observe(this,{
            dataChangeListner.onDataChanged()
            dismiss()
        })
        viewModel.customerCreateFailedLiveData.observe(this,{
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