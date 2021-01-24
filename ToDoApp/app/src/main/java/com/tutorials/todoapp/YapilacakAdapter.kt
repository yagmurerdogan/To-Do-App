package com.tutorials.todoapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class YapilacakAdapter(var mContext:Context, var yapilacaklarListe:ArrayList<Yapilacaklar>,var vt:VeritabaniYardimcisi)
    : RecyclerView.Adapter<YapilacakAdapter.CardTasarimTutucu>(){

        inner class CardTasarimTutucu(tasarim:View):RecyclerView.ViewHolder(tasarim){
            var satir_card: CardView
                var satir_yazi: TextView
                    var satir_resim: ImageView

                    init {
                        satir_card = tasarim.findViewById(R.id.satir_card)
                        satir_yazi = tasarim.findViewById(R.id.satir_yazi)
                        satir_resim = tasarim.findViewById(R.id.satir_resim)
                    }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var yapilacakIs = yapilacaklarListe.get(position)

        holder.satir_yazi.text = "${yapilacakIs.yapilacak_is}"
        holder.satir_resim.setOnClickListener {
            Toast.makeText(mContext,"Bir işten daha kurtuldun :)",Toast.LENGTH_LONG).show()
            YapilacaklarDao().yapilacakSil(vt,yapilacakIs.yapilacak_id)
            yapilacaklarListe = YapilacaklarDao().tumYapilacaklar(vt)
            notifyDataSetChanged()
        }
        holder.satir_card.setOnClickListener {
            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("key",yapilacakIs)
            mContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return yapilacaklarListe.size
    }
}