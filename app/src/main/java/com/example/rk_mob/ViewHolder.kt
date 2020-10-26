package com.example.rk_mob


import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var date: TextView? = null
    var value: TextView? = null
    var valuateName: TextView? = null

    init {
        date = itemView.findViewById(R.id.date)
        value = itemView.findViewById(R.id.value)
        valuateName = itemView.findViewById(R.id.valuateName)
    }

    fun bind(item: Item) {
        itemView.date.text = item.date
        itemView.value.text = item.value
        itemView.valuateName.text = item.valuateName

        itemView.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val itemFragment = ItemFragment()
                val bundle = Bundle()
                bundle.putString("LOW", item.low)
                bundle.putString("HIGH", item.value)
                bundle.putString("OPEN", item.open)
                bundle.putString("CLOSE", item.close)
                itemFragment.arguments = bundle
                activity.supportFragmentManager.beginTransaction().replace(R.id.host_fragment, itemFragment)
                    .addToBackStack(null).commit()
            }
        })
    }
}