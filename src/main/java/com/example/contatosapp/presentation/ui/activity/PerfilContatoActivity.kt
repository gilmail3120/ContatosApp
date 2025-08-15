package com.example.contatosapp.presentation.ui.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ActivityPerfilContatoBinding
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.presentation.viewModel.ContatoViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerfilContatoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPerfilContatoBinding.inflate(layoutInflater) }
    private var perfilContato: Contatos? = null
    private val contatoViewModel: ContatoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        bundle()
        enventoClique()
    }

    private fun bundle() {
        val contato = intent.extras
        if (contato != null) {
            perfilContato = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                contato.getParcelable("contato", Contatos::class.java)
            } else {
                contato.getParcelable("contato")
            }
        }
        with(binding) {
            textNomePerfilContato.text = perfilContato?.nome
            textEmailContato.text = perfilContato?.email
            textNomeGrupoPerfil.text = perfilContato?.nomeGrupo

            if (perfilContato?.foto!=null && perfilContato?.foto!=""){
                Picasso.get()
                    .load(perfilContato?.foto)
                    .error(R.drawable.semimagem)
                    .placeholder(R.drawable.carregando)
                    .into(imagePerfilContato)
            }else{
                imagePerfilContato.setImageResource(R.drawable.semimagem)
            }

            if (perfilContato?.favorito==true){
                imageFavoritar.setImageResource(R.drawable.ic_star_true)
            }else{
                imageFavoritar.setImageResource(R.drawable.ic_star_false)
            }
        }
    }

    private fun enventoClique() {
        with(binding) {
            imageBtnVolar.setOnClickListener {
                finish()
            }
            imageFavoritar.setOnClickListener {
                val estadoFavorito = !(perfilContato?.favorito?:false)

                perfilContato?.favorito = estadoFavorito

                if (perfilContato?.favorito == true){
                    imageFavoritar.setImageResource(R.drawable.ic_star_true)
                }else{
                    imageFavoritar.setImageResource(R.drawable.ic_star_false)
                }
                perfilContato?.id?.let {contatoId->
                    contatoViewModel.favoritarContato(contatoId,estadoFavorito)
                }
            }
        }
    }
}