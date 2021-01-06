package com.mahibul.phmarcymanagement.ui.sell_byDay.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.CREATE_medicine
import com.mahibul.phmarcymanagement.core.BaseActivity
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreference
import com.mahibul.phmarcymanagement.data.SharePreference.AppPreferenceImp
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySell
import com.mahibul.phmarcymanagement.data.reposotory.sell_byDay.DailySellModelImp
import com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.view.EditFragment
import com.mahibul.phmarcymanagement.ui.sell_byDay.addselldata.view.AddSellFragment
import com.mahibul.phmarcymanagement.ui.sell_byDay.editselldata.view.EditDailySell
import com.mahibul.phmarcymanagement.ui.sell_byDay.viewModel.DailySellViewFactory
import com.mahibul.phmarcymanagement.ui.sell_byDay.viewModel.DailySellViewModel
import kotlinx.android.synthetic.main.activity_daily_sell.*
import kotlinx.android.synthetic.main.toolbar.*

class DailySellActivity : BaseActivity(),DataChangeLIstner{
    private lateinit var appPreferance: AppPreference
    private val model by lazy { DailySellModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory = DailySellViewFactory(model)
        ViewModelProvider(this,factory).get(DailySellViewModel::class.java)
    }
    private val sellList by lazy { mutableListOf<DailySell>() }

    private val dailyselladapter by lazy {
        DailySellAdapter(sellList,object : DailySellAdapter.dailysellListClickListener{
            override fun onEditButtonClicked(id: Long) {
                showEditDialuge(id)
            }

            override fun onDeleteButtonClicked(id: Long) {
                showDeletionDialouge(id)
            }
        })
    }

    private fun showEditDialuge(id: Long) {
        appPreferance = AppPreferenceImp(this)
        appPreferance.setId(AppPreference.id,id)

        val dialogFragment = EditDailySell()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_medicine)

    }

    override fun setLayoutId(): Int {
        return R.layout.activity_daily_sell
    }

    override fun setToolbar(): Toolbar {
        toolbar.title = "Today Sell List"
        return toolbar
    }

    override val isHomeUpButtonEnable: Boolean
        get() = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        viewModel.getSellList()
        total_price.text=0.toString()
        viewModel.dailysellListLiveData.observe(this,{
            dailyselladapter.replaceData(it)
        })
        viewModel.dailySellListFailourLiveData.observe(this,{
            ShowToast(it)
        })
        viewModel.dailySellDeletionSuccessLiveData.observe(this,{
            viewModel.getSellList()
        })
        viewModel.dailySellDeletionFailedLiveData.observe(this,{
            ShowToast(it)
        })
        viewModel.tabbleAllDataDelationSuccess.observe(this,{
            viewModel.getSellList()
        })
        viewModel.tabbleAllDataDelationFailor.observe(this,{
            ShowToast(it)
        })
        viewModel.priceCalculateSuccess.observe(this,{
            total_price.text=it.toString()
        })
        btn_tabbleclear.setOnClickListener {
            viewModel.deleteAllItem()
        }
        sell_btn_add.setOnClickListener {
            showDailySell()
        }
    }

    fun showDailySell(){
        val dialogFragment = AddSellFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_medicine)
    }

    override fun onDataChanged() {
        viewModel.getSellList()
    }

    override fun onDataSetChangeError(error: String) {
        ShowToast(error)
    }
    private fun initRecyclerView() {
        dailysell_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        dailysell_recyclerview.adapter= dailyselladapter
    }

    private fun showDeletionDialouge(id: Long) {

        var dialog: AlertDialog? = null

        dialog = MaterialAlertDialogBuilder(this)
            .setMessage("Are You Sure to Delete ?")
            .setPositiveButton("Yes") { _, _ ->
                viewModel.deleteItem(id)
            }
            .setNegativeButton("No") { _, _ ->
                dialog?.dismiss()
            }
            .show()
    }
}