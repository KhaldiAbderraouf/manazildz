package com.example.projetmobile.model.entity

import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class Annonce(var titre:String, var depot:Date, var wilaya:String, var taille:Int, var prix:Float, var description:String, var numero:String?, var photo:ArrayList<String>) {
    fun to_Json():JSONObject{
        var j :JSONObject = JSONObject()

        j.put("titre",titre)
        j.put("depot",depot)
        j.put("willaya",wilaya)
        j.put("taille",taille)
        j.put("prix",prix)
        j.put("description",description)
        j.put("numero",numero)
        j.put("photo",photo)

        return j
    }
}