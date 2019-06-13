package com.example.projetmobile

import android.Manifest
import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityCompat
import android.widget.TextView
import org.json.JSONObject
import java.util.*
import java.util.logging.Logger

class Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details)

        var s = getIntent().getStringExtra("annonce")
        var int: JSONObject = JSONObject(s)


        findViewById<TextView>(R.id.soustitre_detail).setText(int.get("titre").toString())
        findViewById<TextView>(R.id.date_detail).setText(int.get("depot").toString())
        findViewById<TextView>(R.id.willaya_detail).setText(int.get("willaya").toString())
        findViewById<TextView>(R.id.taille_detail).setText(int.get("taille").toString())
        findViewById<TextView>(R.id.prix_detail).setText(int.get("prix").toString())
        findViewById<TextView>(R.id.description_detail).setText(int.get("description").toString())
        findViewById<TextView>(R.id.numero_detail).setText(int.get("numero").toString())

        findViewById<FloatingActionButton>(R.id.button_detail).setOnClickListener{
            if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 5)
            }
            else {
                startCall(int)
            }
        }
    }

    private fun startCall(int: JSONObject) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + int.get("numero").toString()))

        startActivity(intent)
    }
}
