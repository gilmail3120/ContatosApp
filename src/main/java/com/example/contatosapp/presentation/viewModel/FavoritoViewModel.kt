package com.example.contatosapp.presentation.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contatosapp.data.repository.ContatosRepository
import com.example.contatosapp.domain.Contatos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritoViewModel @Inject constructor(val contatosRepository: ContatosRepository): ViewModel() {

    val _listaFavoritos = MutableLiveData<List<Contatos>>()
    val listaFavoritos: LiveData<List<Contatos>>
        get() = _listaFavoritos
    val _mensagem = MutableLiveData<String>()
    val mensagem: LiveData<String>
        get() = _mensagem

    fun obterFavoritos(){
        viewModelScope.launch {
            try {
            _listaFavoritos.value = contatosRepository.obterFavoritos()
            }catch (e: Exception){
                _mensagem.value = "Erro ao carregar favoritos"
                Log.e("favoritoViewModel", "Erro ao obter favoritos ${e.message}", )
            }
        }
    }

}