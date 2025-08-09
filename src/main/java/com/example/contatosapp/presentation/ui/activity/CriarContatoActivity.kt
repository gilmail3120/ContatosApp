package com.example.contatosapp.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ActivityCriarContatoBinding
import com.example.contatosapp.helper.Mensagem

class CriarContatoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCriarContatoBinding.inflate(layoutInflater) }
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
        with(binding){
            btnCriarPerfilSalvar.setOnClickListener {
                Mensagem.exibir(this@CriarContatoActivity,"Botao salvar clicado, ainda nao foi configurado")
            }
            btnCriarPerfilCancelar.setOnClickListener {
                finish()
            }
        }
    }
}