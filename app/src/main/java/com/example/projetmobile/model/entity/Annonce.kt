package com.example.projetmobile.model.entity

import java.util.*
import kotlin.collections.ArrayList

class Annonce(var titre:String, var depot:Date, var wilaya:String, var taille:Int, var prix:Float, var description:String, var numero:String?, var photo:ArrayList<String>) {
}