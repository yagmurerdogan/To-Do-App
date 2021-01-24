package com.tutorials.todoapp

import android.content.ContentValues

class YapilacaklarDao {

    fun tumYapilacaklar(vt:VeritabaniYardimcisi): ArrayList<Yapilacaklar>{
        val db = vt.writableDatabase
        val yapilacaklarListe = ArrayList<Yapilacaklar>()
        val c = db.rawQuery("SELECT * FROM ToDoList",null)

        while(c.moveToNext()){
            val yapilacakIs = Yapilacaklar(c.getInt(c.getColumnIndex("yapilacak_id"))
                ,c.getString(c.getColumnIndex("yapilacak_is")))
            yapilacaklarListe.add(yapilacakIs)
        }
        return yapilacaklarListe
    }

    fun yapilacakAra(vt:VeritabaniYardimcisi,aramaKelimesi:String): ArrayList<Yapilacaklar>{
        val db = vt.writableDatabase
        val yapilacaklarListe = ArrayList<Yapilacaklar>()
        val c = db.rawQuery("SELECT * FROM ToDoList WHERE yapilacak_is like '%$aramaKelimesi%'",null)

        while(c.moveToNext()){
            val yapilacakIs = Yapilacaklar(c.getInt(c.getColumnIndex("yapilacak_id"))
                ,c.getString(c.getColumnIndex("yapilacak_is")))
            yapilacaklarListe.add(yapilacakIs)
        }
        return yapilacaklarListe
    }

    fun yapilacakSil(vt:VeritabaniYardimcisi,yapilacak_id:Int) {
        val db = vt.writableDatabase
        db.delete("ToDoList","yapilacak_id=?",arrayOf(yapilacak_id.toString()))
        db.close()
    }

    fun yapilacakEkle(vt:VeritabaniYardimcisi,yapilacak_is:String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("yapilacak_is",yapilacak_is)

        db.insertOrThrow("ToDoList",null,values)

        db.close()
    }

    fun yapilacakGuncelle(vt:VeritabaniYardimcisi,yapilacak_id:Int,yapilacak_is:String) {
        val db = vt.writableDatabase
        val values = ContentValues()
        values.put("yapilacak_is",yapilacak_is)


        db.update("ToDoList",values,"yapilacak_id=?", arrayOf(yapilacak_id.toString()))

        db.close()
    }
}

