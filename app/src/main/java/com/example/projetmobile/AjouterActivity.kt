package com.example.projetmobile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.projetmobile.model.adapter.Image_Upl_Adapter
import kotlinx.android.synthetic.main.ajouter_annonce.*
import org.json.JSONObject

class AjouterActivity : AppCompatActivity() {

    lateinit var List: ArrayList<ImageView>
    lateinit var List_img: ArrayList<String>
    lateinit var Adapter: Image_Upl_Adapter
    lateinit var int: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajouter_annonce)
        setUpRecyclerView()

        findViewById<Button>(R.id.valider_ajt).setOnClickListener {
            return_main()
        }
        btn_clik()

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
        List_img = ArrayList<String>()

//        l.add(photo)
//        l.add(photo)
//        l.add(photo)
//        l.add(photo)

        return l
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
        j.put("photo",List_img.toString())

        return j
    }

    private fun btn_clik() {
        //BUTTON CLICK
        img_pick_btn.setOnClickListener {
            //check runtime permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE);
                }
                else{
                    //permission already granted
                    pickImageFromGallery();
                }
            }
            else{
                //system OS is < Marshmallow
                pickImageFromGallery();
            }
        }
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            var image_view = ImageView(this)
                image_view.setImageURI(data?.data)
            List_img.add(data?.data.toString())
//            Toast.makeText(this, List_img.toString(), Toast.LENGTH_SHORT).show()
            List.add(image_view)
            Adapter.notifyItemInserted(List.size-1)
        }
    }

}
