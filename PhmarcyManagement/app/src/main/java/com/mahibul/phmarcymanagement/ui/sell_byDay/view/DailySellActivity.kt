package com.mahibul.phmarcymanagement.ui.sell_byDay.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.core.BaseActivity
import kotlinx.android.synthetic.main.toolbar.*

class DailySellActivity : BaseActivity() {
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
    }
}