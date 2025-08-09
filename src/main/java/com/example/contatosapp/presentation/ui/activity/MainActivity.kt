package com.example.contatosapp.presentation.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.contatosapp.R
import com.example.contatosapp.databinding.ActivityMainBinding
import com.example.contatosapp.presentation.ui.activity.CriarContatoActivity
import com.example.contatosapp.presentation.ui.fragment.ContatosFragment
import com.example.contatosapp.presentation.ui.fragment.FavoritosFragment
import com.example.contatosapp.presentation.ui.fragment.GrupoFragment

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            insets
        }

        eventoClique()
        navegacaobottom()
    }

    private fun navegacaobottom() {
        binding.bottomNavigationPrincipal.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
             R.id.rvContatos -> {replaceFragment(ContatosFragment()); true}
                R.id.menuBottomFavoritos->{replaceFragment(FavoritosFragment());true}
                R.id.menuBottomGrupo->{replaceFragment(GrupoFragment()); true}
                else->false
            }
        }

    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayoutPrincipal,fragment).commit()
    }


private fun eventoClique() {
    with(binding) {
        fbAddContato.setOnClickListener {
            val intent = Intent(this@MainActivity, CriarContatoActivity::class.java)
            startActivity(intent)
        }
    }
}
}