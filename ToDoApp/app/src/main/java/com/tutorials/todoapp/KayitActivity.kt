package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_kayit.*

class KayitActivity : AppCompatActivity() {
    private lateinit var vt:VeritabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        toolbarKayit.title = "Yapılacak İş Kayıt"
        setSupportActionBar(toolbarKayit)

        vt = VeritabaniYardimcisi(this@KayitActivity)

        buttonKaydet.setOnClickListener {
            val yapilacak_is = editTextYapilacak.text.toString()

            kayit(yapilacak_is)

        }
    }

    fun kayit(yapilacak_is:String){
        Log.e("Yapılacak işi kayıt","$yapilacak_is")

        YapilacaklarDao().yapilacakEkle(vt,yapilacak_is)
        startActivity(Intent(this@KayitActivity,MainActivity::class.java))
    }
}