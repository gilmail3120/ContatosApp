package com.example.contatosapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.databinding.ActivitySelecionarGrupoBinding
import com.example.contatosapp.helper.Mensagem

class SelecionarGrupoActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySelecionarGrupoBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        eventoCLique()
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
        val dialogView = LayoutInflater.from(this).inflate(R.layout.janela_novo_radiobuttom,null)
        val editText = dialogView.findViewById<EditText>(R.id.editInputNomeGrupo)

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Gravar") {_,_->
                val radioName = editText.text.toString().trim()
                if (radioName.isNotEmpty()){
                    addRadioButtom(radioName)
                }else{
                    Mensagem.exibir(this,"Preencha o campo por favor")
                }
            }.setNegativeButton("Cancelar",null).show()


    }
    private fun addRadioButtom(name:String){
        val newRadioButtom = RadioButton(this).apply {
            text = name
            id = View.generateViewId()
        }
        binding.radioGroup.addView(newRadioButtom)
    }
}


