package com.mahibul.phmarcymanagement.ui.buylist.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.CREATE_medicine
import com.mahibul.phmarcymanagement.core.BaseActivity
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModelImp
import com.mahibul.phmarcymanagement.ui.buylist.addmedicine.view.BuyMedicineFragment
import com.mahibul.phmarcymanagement.ui.buylist.viewmodel.BuyMedicineFactory
import com.mahibul.phmarcymanagement.ui.buylist.viewmodel.BuyMedicineViewModel
import kotlinx.android.synthetic.main.activity_bye.*
import kotlinx.android.synthetic.main.toolbar.*

class BuyActivity : BaseActivity(),DataChangeLIstner {
    private val model by lazy { BuyModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory = BuyMedicineFactory(model)
        ViewModelProvider(this,factory).get(BuyMedicineViewModel::class.java)
    }

    private val MedicineList by lazy { mutableListOf<BuyMedicineData>() }
    private val medicineListAdapter by lazy {
        MedicinelistAdapter(MedicineList)
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_bye
    }

    override fun setToolbar(): Toolbar {
        toolbar.title = "Sore List"
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = true

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Buy Details")
        //Init Recycler View
        initRecyclerView()
        viewModel.getStudentList()
        viewModel.MedicineListLiveData.observe(this,{
            medicineListAdapter.replaceData(it)
        })

        viewModel.MedicineListFailourLiveData.observe(this,{
            ShowToast(it)
        })
        btn_add.setOnClickListener {
            showStudentCreationDialog()
        }
    }

    private fun showStudentCreationDialog() {

        val dialogFragment = BuyMedicineFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_medicine)
    }

    override fun onDataChanged() {
        viewModel.getStudentList()
    }

    override fun onDataSetChangeError(error: String) {
        ShowToast(error)
    }

    private fun initRecyclerView() {
        bye_recyclerview.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bye_recyclerview.adapter= medicineListAdapter
    }
}

