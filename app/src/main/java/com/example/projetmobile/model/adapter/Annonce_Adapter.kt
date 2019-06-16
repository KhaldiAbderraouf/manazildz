package com.example.projetmobile.model.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.projetmobile.AjouterActivity
import com.example.projetmobile.Detail
import com.example.projetmobile.R
import com.example.projetmobile.model.entity.Annonce
import java.util.*

class Annonce_Adapter(var items: ArrayList<Annonce>, var ctx : Context) : RecyclerView.Adapter<Annonce_Adapter.ViewHolder>(), Filterable  {

    var items_Filtered: ArrayList<Annonce>

    init {
        items_Filtered = items
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var titre:TextView? = null
        var type:TextView? = null
        var depot: TextView? = null
        var wilaya: TextView? = null
        var taille: TextView? = null
        var prix: TextView? = null
        var description: TextView? = null
        var numero: TextView? = null
        var photo: ImageView? = null
        var ouvrir: Button? = null
        init {
            this.titre = row?.findViewById<TextView>(R.id.soustitre)
            this.type = row?.findViewById<TextView>(R.id.type)
            this.depot = row?.findViewById<TextView>(R.id.depot)
            this.wilaya = row?.findViewById<TextView>(R.id.willaya)
            this.taille = row?.findViewById<TextView>(R.id.taille)
            this.prix = row?.findViewById<TextView>(R.id.prix)
            this.description = row?.findViewById<TextView>(R.id.description)
            this.numero = row?.findViewById<TextView>(R.id.numero)
            this.photo = row?.findViewById<ImageView>(R.id.photo)
            this.ouvrir = row?.findViewById<Button>(R.id.ouvrir)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0?.context)
            .inflate(R.layout.element, p0, false)

        return Annonce_Adapter.ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var annonce = items[p1]
        p0?.titre?.text = annonce.titre
        p0?.type?.text = annonce.type
        p0?.depot?.text = annonce.depot.toString()
        p0?.wilaya?.text = annonce.wilaya
        p0?.taille?.text = annonce.taille.toString()
        p0?.description?.text = annonce.description
        p0?.prix?.text = annonce.prix.toString()
        p0?.numero?.text = annonce.numero
        if(annonce.photo.isEmpty()){p0?.photo?.setImageResource(R.mipmap.ic_launcher)}
        else{p0?.photo?.setImageURI(Uri.parse(annonce.photo.get(0)))}
        p0?.ouvrir?.setOnClickListener {
            var clickwrkexp = Intent(ctx, Detail::class.java)
            clickwrkexp.putExtra("annonce",annonce.to_Json().toString())
            ctx.startActivity(clickwrkexp)
        }
    }

    override fun getItemCount(): Int = items_Filtered.size

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) items_Filtered = items else {
                    val filteredList = ArrayList<Annonce>()
                    items
                        .filter { it.titre!!.toLowerCase().contains(charString.toLowerCase()) or
                                it.depot.toString()!!.toLowerCase().contains(charString.toLowerCase()) or
                                it.description!!.toLowerCase().contains(charString.toLowerCase()) or
                                it.wilaya!!.toLowerCase().contains(charString.toLowerCase()) or
                                it.taille!!.toString().toLowerCase().contains(charString.toLowerCase()) or
                                it.prix.toString()!!.toLowerCase().contains(charString.toLowerCase()) or
                                it.numero!!.toLowerCase().contains(charString.toLowerCase())
                        }
                        .forEach { filteredList.add(it) }
                    items_Filtered = filteredList
                }
                return FilterResults().apply { values = items_Filtered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                items_Filtered = results?.values as ArrayList<Annonce>
                notifyDataSetChanged()
            }
        }
    }
}