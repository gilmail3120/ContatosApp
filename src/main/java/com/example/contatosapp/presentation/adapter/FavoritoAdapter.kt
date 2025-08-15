package com.example.contatosapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ItemContatoBinding
import com.example.contatosapp.domain.Contatos
import com.squareup.picasso.Picasso

class FavoritoAdapter(val onClick:(Contatos)->Unit) : RecyclerView.Adapter<FavoritoAdapter.FavoritoViewHolder>() {
    private var listaFavoritos = listOf<Contatos>()
    fun adicionar(list: List<Contatos>) {
        listaFavoritos = list
        notifyDataSetChanged()
    }

    inner class FavoritoViewHolder(val binding: ItemContatoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Contatos) {
            with(binding) {
                textNomeContato.text = item.nome
                Picasso.get()
                    .load(item.foto)
                    .error(R.drawable.semimagem)
                    .placeholder(R.drawable.carregando)
                    .into(imageFotoContato)
                constrainContato.setOnClickListener {
                    onClick(item)
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContatoBinding.inflate(layoutInflater, parent, false)
        return FavoritoViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: FavoritoViewHolder,
        position: Int
    ) {
        val lista = listaFavoritos[position]
        holder.bind(lista)

    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }


}