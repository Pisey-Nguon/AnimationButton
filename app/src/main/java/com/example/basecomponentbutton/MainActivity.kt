package com.example.basecomponentbutton

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDefaultSingleClick.setOnSingleClickListener{
            Toast.makeText(this@MainActivity, "onSingleClick", Toast.LENGTH_SHORT).show()
        }
        btnDefaultDoubleClick.setOnDoubleClickListener(object : DoubleClick.DoubleClickListener {
            override fun onDoubleClick() {
                Toast.makeText(this@MainActivity, "onDoubleClick", Toast.LENGTH_SHORT).show()
            }

            override fun onSingleClick() {
                Toast.makeText(this@MainActivity, "onSingleClick", Toast.LENGTH_SHORT).show()
            }

        })
        btnRedSingleClick.setOnSingleClickListener(colorFilter = getString(R.string.codeColorClick)){
            Toast.makeText(this@MainActivity, "onSingleClick", Toast.LENGTH_SHORT).show()
        }
        btnRedDoubleClick.setOnDoubleClickListener(colorFilter = getString(R.string.codeColorClick),object :DoubleClick.DoubleClickListener{
            override fun onDoubleClick() {
                Toast.makeText(this@MainActivity, "onDoubleClick", Toast.LENGTH_SHORT).show()
            }

            override fun onSingleClick() {
                Toast.makeText(this@MainActivity, "onSingleClick", Toast.LENGTH_SHORT).show()
            }

        })

    }
}