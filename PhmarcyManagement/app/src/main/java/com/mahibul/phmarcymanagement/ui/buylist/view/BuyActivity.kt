package com.mahibul.phmarcymanagement.ui.buylist.view

import android.annotation.SuppressLint
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
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyMedicineData
import com.mahibul.phmarcymanagement.data.reposotory.buy_medicine.BuyModelImp
import com.mahibul.phmarcymanagement.ui.buylist.addmedicine.view.BuyMedicineFragment
import com.mahibul.phmarcymanagement.ui.buylist.updatemedicine.view.EditFragment
import com.mahibul.phmarcymanagement.ui.buylist.viewmodel.BuyMedicineFactory
import com.mahibul.phmarcymanagement.ui.buylist.viewmodel.BuyMedicineViewModel
import kotlinx.android.synthetic.main.activity_bye.*
import kotlinx.android.synthetic.main.toolbar.*

class BuyActivity : BaseActivity(),DataChangeLIstner {
    private lateinit var appPreferance: AppPreference
    private val model by lazy { BuyModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory = BuyMedicineFactory(model)
        ViewModelProvider(this,factory).get(BuyMedicineViewModel::class.java)
    }

    private val MedicineList by lazy { mutableListOf<BuyMedicineData>() }
    private val medicineListAdapter by lazy {
        MedicinelistAdapter(MedicineList,object : MedicinelistAdapter.MedicineListClickListener{
            override fun onEditButtonClicked(medicine_name: String,medicine_unit : Int) {
                showEditDialouge(medicine_name,medicine_unit)
            }

            override fun onDeleteButtonClicked(medicine_name: String) {
                showMedicineDeliteDialouge(medicine_name)
            }
        })
    }
    override fun setLayoutId(): Int {
        return R.layout.activity_bye
    }

    override fun setToolbar(): Toolbar {
        toolbar.title = "Store List"
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
        viewModel.getMedicineList()
        viewModel.medicineListLiveData.observe(this, {
            medicineListAdapter.replaceData(it)
        })

        viewModel.medicineListFailourLiveData.observe(this,{
            ShowToast(it)
        })

        viewModel.medicineDeletionSuccessLiveData.observe(this, {
            viewModel.getMedicineList()
        })
        viewModel.medicineDeletionFailedLiveData.observe(this,{
            ShowToast(it)
        })

        btn_add.setOnClickListener {
            showMedicineCreationDialog()
        }
    }
    private fun showEditDialouge(medicineName: String, medicineunit: Int) {
        //Sharepreference save data name and price
        appPreferance = AppPreferenceImp(this)
        appPreferance.setName(AppPreference.Name,medicineName)
        appPreferance.setUnits(AppPreference.Units,medicineunit)

        val dialogFragment = EditFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL,R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_medicine)
    }


    private fun showMedicineCreationDialog() {
        val dialogFragment = BuyMedicineFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_medicine)
    }


    override fun onDataChanged() {
        viewModel.getMedicineList()
    }

    override fun onDataSetChangeError(error: String) {
        ShowToast(error)
    }

    private fun initRecyclerView() {
        bye_recyclerview.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bye_recyclerview.adapter= medicineListAdapter
    }

    fun showMedicineDeliteDialouge(item : String) {

        var dialog: AlertDialog? = null

        dialog = MaterialAlertDialogBuilder(this)
                .setMessage("Are You Sure to Delete ?")
                .setPositiveButton("Yes") { _, _ ->
                    viewModel.deleteMedicine(item)
                }
                .setNegativeButton("No") { _, _ ->
                    dialog?.dismiss()
                }
                .show()
    }
}

