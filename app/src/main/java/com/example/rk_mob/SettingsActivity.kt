package com.example.rk_mob

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    companion object {
        const val VALUATE_NAME = "RUB"
        const val VALUATE_DAYS_COUNT = "5"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val adapter = ArrayAdapter.createFromResource(this, R.array.values_names, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        if (intent.extras != null) {
            daysCount.setText(intent.getStringExtra(VALUATE_DAYS_COUNT).toString())
            val valuateName = intent.getStringExtra(VALUATE_NAME).toString()
            when (valuateName) {
                "RUB" -> spinner.setSelection(0)
                "USD" -> spinner.setSelection(1)
                "EUR" -> spinner.setSelection(2)
            }

        }

        saveBtn.setOnClickListener {
            val selected = spinner.selectedItem.toString()
            val days = daysCount.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(MainActivity.VALUATE_NAME, selected)
            intent.putExtra(MainActivity.VALUATE_DAYS_COUNT, days)
            startActivity(intent)
        }
    }


}