package com.example.contatosapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ReplaceWith
import androidx.recyclerview.widget.RecyclerView
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ItemContatoBinding
import com.example.contatosapp.domain.Contatos
import com.squareup.picasso.Picasso

class ContatosAdapter(val onClick:(Contatos)->Unit): RecyclerView.Adapter<ContatosAdapter.ContatosViewHolder>() {

    private var listaContatos=listOf<Contatos>()
    fun adicionarLista(list: List<Contatos>){
        listaContatos= list
        notifyDataSetChanged()
    }

    inner class ContatosViewHolder(val binding: ItemContatoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Contatos){
            with(binding){
                textNomeContato.text = item.nome
                if (item.foto!=null && item.foto.isNotEmpty()){
                    Picasso.get()
                        .load(item.foto)
                        .placeholder(R.drawable.carregando)
                        .error(R.drawable.semimagem)
                        .into(imageFotoContato)
                }else{
                    imageFotoContato.setImageResource(R.drawable.semimagem)
                }
                constrainContato.setOnClickListener {
                    onClick(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContatosViewHolder {
      val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContatoBinding.inflate(layoutInflater,parent,false)
        return ContatosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContatosViewHolder, position: Int) {
        val lista = listaContatos[position]
        holder.bind(lista)
    }

    override fun getItemCount(): Int {
       return listaContatos.size
    }


}