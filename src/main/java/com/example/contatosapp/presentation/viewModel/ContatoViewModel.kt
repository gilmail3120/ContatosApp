package com.example.contatosapp.presentation.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contatosapp.data.repository.ContatoRepositoryImpl
import com.example.contatosapp.data.repository.ContatosRepository
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ContatoViewModel @Inject constructor(val contatosRepository: ContatosRepository): ViewModel() {

    val _contatos = MutableLiveData<List<Contatos>>()
    val contatos: LiveData<List<Contatos>>
        get() = _contatos

    val _mensagem = MutableLiveData<String>()
    val mensagem: LiveData<String>
        get() = _mensagem

    fun salvarContato(contatos:Contatos,uriFoto:Uri?){

        viewModelScope.launch {
            try {
                contatosRepository.salvarContato(contatos,uriFoto)
                _mensagem.value ="Contato salvo com sucesso!"
            }catch(e: Exception){
                _mensagem.value="Erro ao salvar contato."
            }

        }
    }
    fun salvarGrupo(grupo: Grupo){
        viewModelScope.launch {
            contatosRepository.salvarGrupo(grupo)
        }
    }
    fun obterContatos(){
        viewModelScope.launch {
         _contatos.value = contatosRepository.obterContatos()

        }
    }

}