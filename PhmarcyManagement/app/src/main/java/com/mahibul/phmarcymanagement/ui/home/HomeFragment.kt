package com.mahibul.phmarcymanagement.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mahibul.phmarcymanagement.R
import com.mahibul.phmarcymanagement.ui.byelist.view.BuyActivity
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

  }
}