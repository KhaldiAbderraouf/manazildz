package com.example.projetmobile

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.support.v7.widget.SearchView
import com.example.projetmobile.model.adapter.Annonce_Adapter
import com.example.projetmobile.model.entity.Annonce
import java.util.*
import kotlin.collections.ArrayList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.json.JSONObject
import java.lang.Exception
import org.json.JSONException
import org.json.JSONArray




class MainActivity : AppCompatActivity() {

    lateinit var annonce_List: ArrayList<Annonce>
    lateinit var annonce_Adapter: Annonce_Adapter
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()
        val ajouter = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        ajouter.setOnClickListener {
            to_add()
        }

    }

    private fun to_add(){
        var clickwrkexp = Intent(this@MainActivity, AjouterActivity::class.java)
        startActivityForResult(clickwrkexp,10001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 10001) {
            // make use of "data" = profit
            var s = data?.getStringExtra("annonce")
            try{
                var int: JSONObject = JSONObject(s)
                annonce_List.add(to_Annonce(int))
                annonce_Adapter.notifyItemInserted(annonce_List.size-1)
            }catch (e:Exception){
                e.stackTrace
            }
        }
    }

    private fun to_Annonce(j:JSONObject):Annonce{

        var a = Annonce(
            j.get("titre").toString(),
            Calendar.getInstance().time,
            j.get("willaya").toString(),
            j.get("taille").toString().toInt(),
            j.get("prix").toString().toFloat(),
            j.get("description").toString(),
            j.get("numero").toString(),
            to_array(j.get("photo").toString())
            )
        return a
    }

    private fun to_array(s:String): ArrayList<String> {
        var str = s
        str = str.replace("[","")
        str = str.replace("]","")
        str = str.replace("\"","")
        var k = str.split(";")
        return ArrayList(k)
    }

    private fun setUpRecyclerView() {
        annonce_List = remplir()
        annonce_Adapter = Annonce_Adapter(annonce_List,this@MainActivity)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)


        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(annonce_Adapter)
    }

    fun remplir():ArrayList<Annonce>{
        val l : ArrayList<Annonce> = ArrayList<Annonce>()
        val p : ArrayList<String> = ArrayList<String>()
        p.add("")
        l.add(Annonce("f3 a vendre", Date(2019,4,5),"alger",70,
            2500000F,"Affaire a saisir vend un tres bel appartement richement meublé avec jakousi des grandes terrasses. bien placé a el achour dans une residence cloturé et gardée avec parking au sous sol ,grand espace de jeux pour enfants toutes commodités a coté superette ,pharmacie , patissiers , les ecoles ,",
            "0551234548",p))
        l.add(Annonce("f4 a vendre", Date(2019,2,5),"bouira",80,
            4000000F,"","0556520874",p))
        l.add(Annonce("f5 a vendre", Date(2019,3,5),"bouira",100,
            1000000F,"","0556520874",p))
        l.add(Annonce("villa a vendre", Date(2019,4,6),"bejaia",100,
            20000000F,"","0556520874",p))
        return l
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater : MenuInflater = getMenuInflater()
        inflater.inflate(R.menu.menu_main, menu)

        var searchItem : MenuItem = menu!!.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Int.MAX_VALUE

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                annonce_Adapter.filter.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                annonce_Adapter.filter.filter(newText)
                return true
            }

        } )

        return true
    }
}
