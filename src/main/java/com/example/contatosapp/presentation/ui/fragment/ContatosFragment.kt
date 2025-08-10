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
import com.example.contatosapp.databinding.FragmentContatosBinding
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.presentation.adapter.ContatosAdapter
import com.example.contatosapp.presentation.ui.activity.PerfilContatoActivity
import com.example.contatosapp.presentation.viewModel.ContatoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContatosFragment : Fragment() {
    private  lateinit var binding: FragmentContatosBinding
    private lateinit var contatosAdapter: ContatosAdapter
    private val contatoViewModel: ContatoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContatosBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun observaveis() {
        contatoViewModel.contatos.observe(viewLifecycleOwner){contatos->
            if (contatos!=null){
                contatosAdapter.adicionarLista(contatos)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter()
        observaveis()
        contatoViewModel.obterContatos()
    }

    private fun adapter() {
        contatosAdapter= ContatosAdapter(){contatos->
            val intent = Intent(requireContext(), PerfilContatoActivity::class.java)
            intent.putExtra("contato", contatos)
            startActivity(intent)
        }
        with(binding){
            rvContatos.adapter = contatosAdapter
            rvContatos.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)

        }
    }
}