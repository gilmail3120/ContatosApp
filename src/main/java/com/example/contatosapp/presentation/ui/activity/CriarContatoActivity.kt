package com.example.contatosapp.presentation.ui.activity

import android.content.Intent
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
import com.example.contatosapp.SelecionarGrupoActivity
import com.example.contatosapp.databinding.ActivityCriarContatoBinding
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo
import com.example.contatosapp.helper.Mensagem
import com.example.contatosapp.presentation.viewModel.ContatoViewModel
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
    private var grupo:String?=null
    private val selecionarGrupoLaucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
        if (result.resultCode == RESULT_OK){
            val data:Intent? = result.data
            grupo = data?.getStringExtra("radiogrupo")
            grupo?.let {valorGrupo->
                binding.editInputGrupo.setText(valorGrupo)
            }
        }
    }


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
                if (mensagem == "Contato salvo com sucesso!"){
                    finish()
                }
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
                    editInputGrupo.setText(grupo)
                    var camposValidados = true
                    textinputNome.error = null
                    textInputTelefone.error=null
                    editInputEmail.error=null

                    if (nome.isEmpty()){
                        textinputNome.error ="Preencha o nome"
                        camposValidados = false
                    }
                    if (tel.isEmpty()){
                        textInputTelefone.error="Preencha o telefone"
                        camposValidados= false
                    }
                    if (email.isEmpty()){
                        textInputEmail.error = "Preencha o e-mail"
                        camposValidados =false
                    }
                    if(camposValidados){
                        val novoContato = Contatos(nome = nome, telefone = tel, email = email, nomeGrupo = grupo.toString())
                        val novoGrupo = Grupo(grupo)

                        contatoViewModel.salvarContatoComGrupo(novoContato,novoGrupo,uriImagemSelecionada)

                    }
                }
            }
            btnCriarPerfilCancelar.setOnClickListener {
                finish()
            }
            imageBtnEditarFoto.setOnClickListener {
                abrirGaleria.launch("image/*")//Mime type
            }
            editInputGrupo.setOnClickListener {
                val intent = Intent(this@CriarContatoActivity, SelecionarGrupoActivity::class.java)
                selecionarGrupoLaucher.launch(intent)
            }
        }
    }
}