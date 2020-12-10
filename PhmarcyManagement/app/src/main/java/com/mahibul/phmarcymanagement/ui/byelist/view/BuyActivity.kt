package com.mahibul.phmarcymanagement.ui.byelist.view

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.CREATE_medicine
import com.mahibul.phmarcymanagement.data.local.DataChangeLIstner
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyMedicine
import com.mahibul.phmarcymanagement.data.model.buy_medicine.BuyModelImp
import com.mahibul.phmarcymanagement.ui.byelist.addmedicine.view.BuyMedicineFragment
import com.mahibul.phmarcymanagement.ui.byelist.viewmodel.BuyMedicineFactory
import com.mahibul.phmarcymanagement.ui.byelist.viewmodel.BuyMedicineViewModel
import kotlinx.android.synthetic.main.activity_bye.*
import kotlinx.android.synthetic.main.medicine_item_view.*

class BuyActivity : AppCompatActivity(),DataChangeLIstner {
    private val model by lazy { BuyModelImp(applicationContext) }
    private val viewModel by lazy {
        val factory = BuyMedicineFactory(model)
        ViewModelProvider(this,factory).get(BuyMedicineViewModel::class.java)
    }

    private val MedicineList by lazy { mutableListOf<BuyMedicine>() }
    private val medicineListAdapter by lazy {
        MedicinelistAdapter(MedicineList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bye)
        setTitle("Buy Details")

        //incluse layout things..........
        nameTextView.text="Product Name"
        unitTextView.text="Units"
        priceTextView.text="Price"
        btnEdit.visibility = View.GONE
        btnDelete.visibility= View.GONE

        initRecyclerView()
        viewModel.getStudentList()
        viewModel.MedicineListLiveData.observe(this,{
            medicineListAdapter.replaceData(it)
        })

        viewModel.MedicineListFailourLiveData.observe(this,{
            Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
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
        Toast.makeText(this,error,Toast.LENGTH_SHORT).show()
    }

    private fun initRecyclerView() {
        bye_recyclerview.layoutManager =LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        bye_recyclerview.adapter= medicineListAdapter
    }

}

