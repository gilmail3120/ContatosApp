package com.example.contatosapp.presentation.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contatosapp.R
import com.example.contatosapp.databinding.FragmentContatosBinding
import com.example.contatosapp.presentation.adapter.ContatosAdapter

class ContatosFragment : Fragment() {
    private val binding by lazy { FragmentContatosBinding.inflate(layoutInflater) }
    private lateinit var contatosAdapter: ContatosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       // return inflater.inflate(R.layout.fragment_contatos, container, false)
        adapter()
        return binding.root
    }

    private fun adapter() {
        contatosAdapter= ContatosAdapter()
        with(binding){
            rvContatos.adapter = contatosAdapter
            rvContatos.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
            contatosAdapter.adicionarLista(TODO())
        }
    }

}