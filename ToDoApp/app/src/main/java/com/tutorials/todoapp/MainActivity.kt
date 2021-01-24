package com.tutorials.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener {
    private lateinit var yapilacaklarListe:ArrayList<Yapilacaklar>
    private lateinit var adapter:YapilacakAdapter
    private lateinit var vt:VeritabaniYardimcisi



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        veritabaniKopyala()

        toolbar.title = "Yapılacaklar"
        //toolbarMainActivity.subtitle = "Alt Başlık"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        vt = VeritabaniYardimcisi(this@MainActivity)

        tumYapilacaklariAl()

//        yapilacaklarListe = ArrayList()
//
//        val is1 = Yapilacaklar(1,"To Do App Yap")
//        val is2 = Yapilacaklar(2,"Kaynak Sınavına Çalış")
//
//        yapilacaklarListe.add(is1)
//        yapilacaklarListe.add(is2)
//
//        adapter = YapilacakAdapter(this@MainActivity,yapilacaklarListe)
//        rv.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this@MainActivity,KayitActivity::class.java))
        }

        fabHelp.setOnClickListener {
            alertGoster()
        }
    }

    fun alertGoster(){
        val tasarim = LayoutInflater.from(this).inflate(R.layout.alert_tasarim,null)
        val ad = AlertDialog.Builder(this)
        ad.setTitle("Neler Yapılabilir?")
        ad.setView(tasarim)

        ad.setNegativeButton("Anladım") { dialogInterface, i->

        }
        ad.create().show()
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_arama_menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        Log.e("Gönderilen Arama Sonucu",query)
        aramaYap(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        Log.e("Harf Girdikçe Sonuç",newText)
        aramaYap(newText)
        return true
    }

    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun tumYapilacaklariAl(){
        yapilacaklarListe = YapilacaklarDao().tumYapilacaklar(vt)
        adapter = YapilacakAdapter(this@MainActivity,yapilacaklarListe,vt)
        rv.adapter = adapter

    }

    fun aramaYap(aramaKelimesi:String){
        yapilacaklarListe = YapilacaklarDao().yapilacakAra(vt,aramaKelimesi)
        adapter = YapilacakAdapter(this@MainActivity,yapilacaklarListe,vt)
        rv.adapter = adapter
    }

}