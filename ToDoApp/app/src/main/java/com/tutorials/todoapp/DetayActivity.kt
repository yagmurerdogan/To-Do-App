package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_detay.*

class DetayActivity : AppCompatActivity() {

    private lateinit var yapilacakIs:Yapilacaklar
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        toolbarDetay.title = "Yapılacak İş Detay"
        setSupportActionBar(toolbarDetay)
        vt = VeritabaniYardimcisi(this@DetayActivity)

        yapilacakIs=intent.getSerializableExtra("key") as Yapilacaklar
        editTextYapilacak.setText(yapilacakIs.yapilacak_is)

        buttonGuncelle.setOnClickListener {
            val yapilacak_is = editTextYapilacak.text.toString()

            guncelle(yapilacakIs.yapilacak_id,yapilacak_is)

        }
    }

    fun guncelle(yapilacak_id:Int, yapilacak_is:String) {
        Log.e("İş Güncelle","$yapilacak_id-$yapilacak_is")

        YapilacaklarDao().yapilacakGuncelle(vt,yapilacak_id,yapilacak_is)

        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
    }
}