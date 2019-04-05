package com.example.projetmobile

import android.app.SearchManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.support.v7.widget.SearchView
import com.example.projetmobile.model.adapter.Annonce_Adapter
import com.example.projetmobile.model.entity.Annonce
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView



class MainActivity : AppCompatActivity() {

    lateinit var annonce_List: ArrayList<Annonce>
    lateinit var annonce_Adapter: Annonce_Adapter
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
        annonce_List = remplir()
        annonce_Adapter = Annonce_Adapter(annonce_List)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)


        recyclerView.setLayoutManager(layoutManager)
        recyclerView.setAdapter(annonce_Adapter)
    }

    fun remplir():ArrayList<Annonce>{
        val l : ArrayList<Annonce> = ArrayList<Annonce>()
        l.add(Annonce("f3 a vendre", Date(2019,4,5),"alger",70,
            2500000F,"","0551234548",""))
        l.add(Annonce("f4 a vendre", Date(2019,2,5),"bouira",80,
            4000000F,"","0556520874",""))
        l.add(Annonce("f5 a vendre", Date(2019,3,5),"bouira",100,
            1000000F,"","0556520874",""))
        l.add(Annonce("villa a vendre", Date(2019,4,6),"bejaia",100,
            20000000F,"","0556520874",""))
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
