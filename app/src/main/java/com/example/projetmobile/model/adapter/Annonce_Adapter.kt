package com.example.projetmobile.model.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.projetmobile.R
import com.example.projetmobile.model.entity.Annonce
import java.util.*

class Annonce_Adapter(val context: Context,
                      var items: ArrayList<Annonce>,
                      val itemTap: (Annonce) -> Unit) : RecyclerView.Adapter<Annonce_Adapter.ViewHolder>(), Filterable  {

    var items_Filtered: ArrayList<Annonce>

    init {
        items_Filtered = items
    }

    class ViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        var titre:TextView? = null
        var depot: TextView? = null
        var wilaya: TextView? = null
        var taille: TextView? = null
        var prix: TextView? = null
        var description: TextView? = null
        var numero: TextView? = null
        var photo: ImageView? = null
        init {
            this.titre = row?.findViewById<TextView>(R.id.titre)
            this.depot = row?.findViewById<TextView>(R.id.depot)
            this.wilaya = row?.findViewById<TextView>(R.id.willaya)
            this.taille = row?.findViewById<TextView>(R.id.taille)
            this.prix = row?.findViewById<TextView>(R.id.prix)
            this.description = row?.findViewById<TextView>(R.id.description)
            this.numero = row?.findViewById<TextView>(R.id.numero)
            this.photo = row?.findViewById<ImageView>(R.id.photo)
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val itemView = LayoutInflater.from(p0?.context)
            .inflate(R.layout.element, p0, false)

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        var annonce = items[p1]
        p0?.titre?.text = annonce.titre
        p0?.depot?.text = annonce.depot.toString()
        p0?.wilaya?.text = annonce.wilaya
        p0?.taille?.text = annonce.taille.toString()
        p0?.description?.text = annonce.description
        p0?.prix?.text = annonce.prix.toString()
        p0?.numero?.text = annonce.numero
        p0?.photo?.setImageResource(R.mipmap.ic_launcher)
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