package com.mahibul.phmarcymanagement.ui.selllist.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.core.BaseActivity
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreference
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellListModelImp
import com.mahibul.phmarcymanagement.data.reposotory.sell_medicine.SellMedicine
import com.mahibul.phmarcymanagement.ui.selllist.viewmodel.SellModelFactory
import com.mahibul.phmarcymanagement.ui.selllist.viewmodel.SellViewModel
import kotlinx.android.synthetic.main.activity_bye.*
import kotlinx.android.synthetic.main.activity_bye.bye_recyclerview
import kotlinx.android.synthetic.main.activity_sell.*
import kotlinx.android.synthetic.main.toolbar.*

class SellActivity : BaseActivity(),DataChangeLIstner {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { SellListModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory = SellModelFactory(model)
        ViewModelProvider(this,factory).get(SellViewModel::class.java)
    }
    private val medicineList by lazy { mutableListOf<SellMedicine>() }
    private val sellAdapter by lazy {
        SellAdapter(medicineList,object :SellAdapter.ListClickListener{
            override fun onMedicineClicked(medicine_name: String, medicine_units: Int) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun setLayoutId(): Int {
        return R.layout.activity_sell
    }

    override fun setToolbar(): Toolbar {
        toolbar.title = "Medicine List"
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRecyclerView()
        viewModel.getMedicineList()
        viewModel.medicineListLiveData.observe(this,{
            sellAdapter.replaceData(it)
        })
        viewModel.medicineListFailourLiveData.observe(this,{
            ShowToast(it)
        })
    }

    private fun initRecyclerView() {
        sell_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        sell_recyclerview.adapter= sellAdapter
    }

    override fun onDataChanged() {
        viewModel.getMedicineList()
    }

    override fun onDataSetChangeError(error: String) {
        ShowToast(error)
    }
}