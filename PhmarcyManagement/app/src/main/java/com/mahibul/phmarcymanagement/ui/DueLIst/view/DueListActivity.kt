package com.mahibul.phmarcymanagement.ui.DueLIst.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.core.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class DueListActivity : BaseActivity() {
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
    }
}