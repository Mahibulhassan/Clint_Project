package com.mahibul.phmarcymanagement.ui.DueLIst.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.create_customer
import com.mahibul.phmarcymanagement.core.BaseActivity
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DataCustomer
import com.mahibul.phmarcymanagement.data.reposotory.due_coustomer.DueListModelImp
import com.mahibul.phmarcymanagement.ui.DueLIst.addCustomer.view.DueCustomerFragment
import com.mahibul.phmarcymanagement.ui.DueLIst.viewModel.DueModelFactory
import com.mahibul.phmarcymanagement.ui.DueLIst.viewModel.DueViewModel
import kotlinx.android.synthetic.main.activity_due_list.*
import kotlinx.android.synthetic.main.toolbar.*

class DueListActivity : BaseActivity(),DataChangeLIstner {

    private val model by lazy { DueListModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory =DueModelFactory(model)
        ViewModelProvider(this,factory).get(DueViewModel::class.java)
    }
    private val customerList by lazy { mutableListOf<DataCustomer>() }
    private val dueCustomAdapter by lazy {
        DueListAdapter(customerList,object :DueListAdapter.CustomerClickListner{
            override fun onEditButtonClicked(customer_name: String, customer_due: Int) {
                TODO("Not yet implemented")
            }

            override fun onDeleteButtonClicked(customer_name: String) {
                TODO("Not yet implemented")
            }
        })
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