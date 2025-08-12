package com.example.contatosapp.presentation.ui.activity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ActivityCriarContatoBinding
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo
import com.example.contatosapp.helper.Mensagem
import com.example.contatosapp.presentation.viewModel.ContatoViewModel
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CriarContatoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCriarContatoBinding.inflate(layoutInflater) }
    private val contatoViewModel: ContatoViewModel by viewModels()
    private var uriImagemSelecionada: Uri? = null
    val abrirGaleria = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            binding.imageCriarPerfil.setImageURI(uri)
            uriImagemSelecionada = uri
            Mensagem.exibir(this@CriarContatoActivity, "Imagem selecionada.")
        } else {
            Mensagem.exibir(this@CriarContatoActivity, "Nenhuma imagem selecionada")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        observavel()
        eventoCLique()
    }

    private fun observavel() {
        contatoViewModel.mensagem.observe(this) { mensagem ->
            mensagem?.let {
                Mensagem.exibir(this, mensagem)
            }
        }
    }

    private fun eventoCLique() {
        with(binding) {
            btnCriarPerfilSalvar.setOnClickListener {

                with(binding) {
                    val nome = editInputNome.text.toString()
                    val tel = editInputTel.text.toString()
                    val email = editInputEmail.text.toString()
                    val grupo = editInputGrupo.text.toString()
                    val novoContato = Contatos(nome, tel, email)
                    val novoGrupo = Grupo(grupo)

                    contatoViewModel.salvarContato(novoContato, uriImagemSelecionada)
                    contatoViewModel.salvarGrupo(novoGrupo)
                    Log.i("criarcontato", "eventoCLique:$novoContato")

                }
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3000)
                    finish()
                }
            }
            btnCriarPerfilCancelar.setOnClickListener {
                finish()
            }

            imageBtnEditarFoto.setOnClickListener {
                abrirGaleria.launch("image/*")//Mime type
            }
        }
    }
}