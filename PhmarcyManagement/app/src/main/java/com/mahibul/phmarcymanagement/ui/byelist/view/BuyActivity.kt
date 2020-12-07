package com.mahibul.phmarcymanagement.ui.byelist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.constants.CREATE_STUDENT
import com.mahibul.phmarcymanagement.ui.byelist.addmedicine.view.BuyMedicineFragment
import kotlinx.android.synthetic.main.activity_bye.*

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bye)
        setTitle("Buy Details")

        btn_add.setOnClickListener {
            showStudentCreationDialog()
        }
    }


    private fun showStudentCreationDialog() {

        val dialogFragment = BuyMedicineFragment()
        dialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.CustomDialog)
        dialogFragment.show(supportFragmentManager, CREATE_STUDENT)
    }

}