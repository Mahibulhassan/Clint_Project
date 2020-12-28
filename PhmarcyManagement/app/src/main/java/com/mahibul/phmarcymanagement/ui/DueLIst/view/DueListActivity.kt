package com.mahibul.phmarcymanagement.ui.DueLIst.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.create_customer
import com.mahibul.phmarcymanagement.core.BaseActivity
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreference
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreferenceImp
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.Customer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModelImp
import com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.view.DueCustomerFragment
import com.mahibul.phmarcymanagement.ui.DueLIst.updateCustomrdetails.view.DueEditFragment
import com.mahibul.phmarcymanagement.ui.DueLIst.viewModel.DueModelFactory
import com.mahibul.phmarcymanagement.ui.DueLIst.viewModel.DueViewModel
import kotlinx.android.synthetic.main.activity_due_list.*
import kotlinx.android.synthetic.main.toolbar.*

class DueListActivity : BaseActivity(),DataChangeLIstner {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { DueListModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory =DueModelFactory(model)
        ViewModelProvider(this,factory).get(DueViewModel::class.java)
    }
    private val customerList by lazy { mutableListOf<Customer>() }
    private val dueCustomAdapter by lazy {
        DueListAdapter(customerList,object :DueListAdapter.CustomerClickListner{
            override fun onEditButtonClicked(customer_name: String, customer_due: Int) {
                showDueCustomer(customer_name,customer_due)
            }

            override fun onDeleteButtonClicked(customer_name: String) {
                showCustomerDelationDialouge(customer_name)
            }
        })
    }
    private fun showDueCustomer(customerName: String, customerDue: Int) {
        appPreferance = AppPreferenceImp(this)
        appPreferance.setName(AppPreference.Name,customerName)
        appPreferance.setPrice(AppPreference.price,customerDue)

        val dialogFragment = DueEditFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, create_customer)
    }

    private fun showCustomerDelationDialouge(id : String) {
        var dialog: AlertDialog? = null

        dialog = MaterialAlertDialogBuilder(this)
                .setMessage("Are You Sure to Delete ?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteCustomer(id)
                }
                .setNegativeButton("No") { _, _ ->
                    dialog?.dismiss()
                }
                .show()
    }


    override fun setLayoutId(): Int {
        return R.layout.activity_due_list
    }

    override fun setToolbar(): Toolbar {
        toolbar.title = "Due List"
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        viewModel.getCustomerList()
        viewModel.customerListLiveData.observe(this,{
            dueCustomAdapter.replaceData(it)
        })
        viewModel.customerListFailourLiveData.observe(this,{
            ShowToast(it)
        })
        viewModel.customerDeletionSuccessLiveData.observe(this,{
            viewModel.getCustomerList()
        })
        viewModel.customerDeletionFailedLiveData.observe(this,{
            ShowToast(it)
        })

        customar_btn_add.setOnClickListener {
            showCustomerCreateDialouge()
        }

    }

    private fun showCustomerCreateDialouge() {
        val dialougeFragment = DueCustomerFragment()
        dialougeFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog)
        dialougeFragment.show(supportFragmentManager, create_customer)

    }

    private fun initRecyclerView() {
        due_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        due_recyclerview.adapter = dueCustomAdapter
    }

    override fun onDataChanged() {
        viewModel.getCustomerList()
    }

    override fun onDataSetChangeError(error: String) {
        ShowToast(error)
    }
}