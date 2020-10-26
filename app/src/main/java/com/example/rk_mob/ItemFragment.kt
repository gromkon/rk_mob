package com.example.rk_mob

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_item.view.*


class ItemFragment() : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_item, container, false)
        view.lowValueView.text = arguments?.getString("LOW")
        view.highValueView.text = arguments?.getString("HIGH")
        view.openValueView.text = arguments?.getString("OPEN")
        view.closeValueView.text = arguments?.getString("CLOSE")
        return view
    }

}