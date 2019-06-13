package com.example.projetmobile

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.example.projetmobile.model.adapter.Image_Upl_Adapter
import org.json.JSONObject

class AjouterActivity : AppCompatActivity() {

    lateinit var List: ArrayList<ImageView>
    lateinit var Adapter: Image_Upl_Adapter
    lateinit var int: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajouter_annonce)
        setUpRecyclerView()

        findViewById<Button>(R.id.valider_ajt).setOnClickListener {
            return_main()
        }

    }

    private fun setUpRecyclerView() {
        List = remplir_img()
        Adapter = Image_Upl_Adapter(List)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_ajt)
        recyclerView.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(this,3)

        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(Adapter)
    }

    private fun remplir_img(): ArrayList<ImageView> {
        val l : ArrayList<ImageView> = ArrayList<ImageView>()
        var photo = ImageView(this)
        photo.setImageResource(R.mipmap.ic_launcher)

        l.add(photo)
        l.add(photo)
        l.add(photo)
        l.add(photo)

        return l
    }

    private fun valider(){

    }
    private fun return_main(){
        var data:Intent = getIntent();
        data.putExtra("annonce",to_Json().toString())
        setResult(RESULT_OK, data)
        finish(); // ends current activity
    }

    fun to_Json(): JSONObject {
        var j : JSONObject = JSONObject()

        j.put("titre",findViewById<EditText>(R.id.Titre_ajt).text)
        j.put("willaya",findViewById<EditText>(R.id.willaya_ajt).text)
        j.put("taille",findViewById<EditText>(R.id.taille_ajt).text)
        j.put("prix",findViewById<EditText>(R.id.prix_ajt).text)
        j.put("description",findViewById<EditText>(R.id.desc_ajt).text)
        j.put("numero", findViewById<EditText>(R.id.num_ajt).text)
        j.put("photo","[]")

        return j
    }
}
