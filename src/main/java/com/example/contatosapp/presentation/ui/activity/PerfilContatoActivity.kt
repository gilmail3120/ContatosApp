package com.example.contatosapp.presentation.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ActivityPerfilContatoBinding

class PerfilContatoActivity : AppCompatActivity() {
    private val binding by lazy { ActivityPerfilContatoBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }
        enventoClique()
    }

    private fun enventoClique() {
        with(binding){
            imageBtnVolar.setOnClickListener {
                finish()
            }
        }
    }
}