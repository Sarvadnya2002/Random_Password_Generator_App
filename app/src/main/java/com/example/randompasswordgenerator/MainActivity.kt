package com.example.randompasswordgenerator

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private var tvPassword : TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLength: EditText=findViewById(R.id.etLength)
        val btnGenerate : MaterialButton =findViewById(R.id.btnGenerate)
        val ibCopy : ImageButton =findViewById(R.id.ibCopy)
        tvPassword=findViewById(R.id.tvPassword)

        btnGenerate.setOnClickListener{
            if(etLength.text.isEmpty()){
                Toast.makeText(
                    this,
                    "Enter length First", Toast.LENGTH_LONG
                ).show()
            } else{
                val length=Integer.parseInt(etLength.text.toString())
                val pass=getRandPassword(length) //to avoid going back to main activity
                tvPassword?.text=pass.toString()
            }
        }

        ibCopy.setOnClickListener {
            copyTextToClipboard()
        }


    }

    private fun copyTextToClipboard() {
        val textToCopy = tvPassword?.text
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", textToCopy)
        clipboardManager.setPrimaryClip(clipData)
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_LONG).show()
    }
    fun getRandPassword(n: Int): String
    {
        val characterSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        val symbolSet="~`!@#\$%^&*()_-+={[}]|\\:;\"'<,>.?/"

        val random = Random(System.nanoTime())
        val password = StringBuilder()

        val rN=random.nextInt(n)

        for (i in 0 until n)
        {
            if(i!=rN) {
                val rIndex = random.nextInt(characterSet.length)
                password.append(characterSet[rIndex])
            } else{
                val rIndex1 = random.nextInt(symbolSet.length)
                password.append(symbolSet[rIndex1])
            }
        }

        return password.toString()
    }
}

