package com.mahibul.phmarcymanagement.ui.selllist.view

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.core.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class SellActivity : BaseActivity() {
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
    }
}