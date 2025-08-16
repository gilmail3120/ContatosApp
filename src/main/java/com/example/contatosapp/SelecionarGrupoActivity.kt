package com.example.contatosapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.databinding.ActivitySelecionarGrupoBinding
import com.example.contatosapp.domain.Grupo
import com.example.contatosapp.helper.Mensagem
import com.example.contatosapp.presentation.viewModel.SelecionarGrupoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelecionarGrupoActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelecionarGrupoBinding.inflate(layoutInflater) }
    private val selecionarGrupoViewModel: SelecionarGrupoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        observaveis()
        eventoCLique()
    }

    private fun observaveis() {
        selecionarGrupoViewModel.mensagem.observe(this){erro->
            Mensagem.exibir(this,"Erro $erro")
        }

        selecionarGrupoViewModel.listaGrupo.observe(this){grupos->
            binding.radioGroup.removeAllViews()
            addRadioButtom("Sem Grupo")
            if (grupos!=null){
                grupos.forEach { grupo->
                    addRadioButtom(grupo.nome.toString())
                    Log.i("selecionaGrupoActivity", "observaveis: ${grupo.nome}")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        selecionarGrupoViewModel.obterListaGrupos()
    }

    private fun eventoCLique() {
        with(binding) {
            imageVoltar.setOnClickListener {
                finish()
            }
            imageViewAdd.setOnClickListener {
                showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.janela_novo_radiobuttom, null)
        val editText = dialogView.findViewById<EditText>(R.id.editInputNomeGrupo)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Gravar") { _, _ ->
                val radioName = editText.text.toString().trim()
                if (radioName.isNotEmpty()) {
                    selecionarGrupoViewModel.salvarGrupo(Grupo(nome = radioName))
                } else {
                    Mensagem.exibir(this, "Preencha o campo por favor")
                }
            }.setNegativeButton("Cancelar", null).show()

    }

    private fun addRadioButtom(name: String) {
        val newRadioButtom = RadioButton(this).apply {
            text = name
            id = View.generateViewId()
        }
        binding.radioGroup.addView(newRadioButtom)
    }
}


