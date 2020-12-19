package com.mahibul.phmarcymanagement.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.ui.DueLIst.view.DueListActivity
import com.mahibul.phmarcymanagement.ui.buylist.view.BuyActivity
import com.mahibul.phmarcymanagement.ui.sell_byDay.view.DailySellActivity
import com.mahibul.phmarcymanagement.ui.selllist.view.SellActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_home, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    btn_buy.setOnClickListener {
       val intent = Intent(context,BuyActivity::class.java)
      startActivity(intent)
    }

    btn_sell.setOnClickListener {
      val intent = Intent(context,SellActivity::class.java)
      startActivity(intent)
    }

    btn_day_sell.setOnClickListener {
      val intent = Intent(context,DailySellActivity::class.java)
      startActivity(intent)
    }

    btn_Due_list.setOnClickListener {
      val intent = Intent(context,DueListActivity::class.java)
      startActivity(intent)
    }
  }
}