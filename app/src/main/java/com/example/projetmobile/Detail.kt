package com.example.projetmobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.util.*

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        var int: Intent = getIntent()

        findViewById<TextView>(R.id.soustitre_detail).setText(int.getStringExtra("titre"))

        val date: Date = Date(int.getLongExtra("depot",0))
        findViewById<TextView>(R.id.date_detail).setText(date.toString())
        findViewById<TextView>(R.id.willaya_detail).setText(int.getStringExtra("willaya"))
        findViewById<TextView>(R.id.taille_detail).setText(int.getStringExtra("taile"))
        findViewById<TextView>(R.id.prix_detail).setText(int.getStringExtra("prix"))
        findViewById<TextView>(R.id.description_detail).setText(int.getStringExtra("description"))
        findViewById<TextView>(R.id.numero_detail).setText(int.getStringExtra("numero"))
    }
}
