package com.example.contatosapp.presentation.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contatosapp.R
import com.example.contatosapp.databinding.FragmentContatosBinding
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.presentation.adapter.ContatosAdapter
import com.example.contatosapp.presentation.ui.activity.PerfilContatoActivity

class ContatosFragment : Fragment() {
    private  lateinit var binding: FragmentContatosBinding
    private lateinit var contatosAdapter: ContatosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentContatosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter()
    }

    private fun adapter() {
        contatosAdapter= ContatosAdapter(){contatos->
            val intent = Intent(requireContext(), PerfilContatoActivity::class.java)
            startActivity(intent)
        }
        with(binding){
            rvContatos.adapter = contatosAdapter
            rvContatos.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
            val lista = listOf(
                Contatos("Vegeta","https://criticalhits.com.br/wp-content/uploads/2021/11/1f31df5449a9adab243544dee3ede00a.png"),
                Contatos("Eda","https://cdn.prod.website-files.com/624ac40503a527cf47af4192/654b536c8c46e155141a426d_ai-face-generator-how.png"),
                Contatos("James","https://idsb.tmgrup.com.tr/ly/uploads/images/2023/11/14/301015.jpg"),
                Contatos("Rick","https://mymodernmet.com/wp/wp-content/uploads/2019/09/100k-ai-faces-6.jpg"),
                Contatos("Eda","https://cdn.prod.website-files.com/624ac40503a527cf47af4192/654b536c8c46e155141a426d_ai-face-generator-how.png"),
                Contatos("Vegeta","https://criticalhits.com.br/wp-content/uploads/2021/11/1f31df5449a9adab243544dee3ede00a.png"),
                Contatos("James","https://idsb.tmgrup.com.tr/ly/uploads/images/2023/11/14/301015.jpg")
            )
            contatosAdapter.adicionarLista(lista)
        }
    }
}