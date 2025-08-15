package com.example.contatosapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contatosapp.R
import com.example.contatosapp.databinding.FragmentFavoritosBinding
import com.example.contatosapp.presentation.adapter.FavoritoAdapter
import com.example.contatosapp.presentation.ui.activity.PerfilContatoActivity
import com.example.contatosapp.presentation.viewModel.FavoritoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritosFragment : Fragment() {
    private lateinit var binding: FragmentFavoritosBinding
    private val favoritoViewModel: FavoritoViewModel by viewModels()
    private lateinit var favoritoAdapter: FavoritoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        iniciarAdapter()
        observaveis()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoritoViewModel.obterFavoritos()
    }

    private fun observaveis() {
        favoritoViewModel.listaFavoritos.observe(viewLifecycleOwner){contatos->
            favoritoAdapter.adicionar(contatos)
        }
    }

    private fun iniciarAdapter() {
        favoritoAdapter= FavoritoAdapter(){contatos->
            val intent = Intent(requireContext(), PerfilContatoActivity::class.java)
            intent.putExtra("contato",contatos)
            startActivity(intent)
        }

        with(binding){
            rvContatosFavotiros.adapter = favoritoAdapter
            rvContatosFavotiros.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        }
    }
}