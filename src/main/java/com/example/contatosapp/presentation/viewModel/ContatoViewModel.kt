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

    fun salvarContatoComGrupo(contato:Contatos,grupo: Grupo,uriFoto: Uri?){
        viewModelScope.launch {
            val grupoId = contatosRepository.salvarGrupo(grupo)
            if (grupoId!=null){
                val contatoComGrupo =contato.copy(grupoID = grupoId)
                contatosRepository.salvarContato(contatoComGrupo,uriFoto,grupoId)
                _mensagem.value = "Contato salvo com sucesso!"
            }else{
                _mensagem.value = "Erro ao salvar contato com grupo"
            }
        }

    }

    fun obterContatos(){
        viewModelScope.launch {
         _contatos.value = contatosRepository.obterContatos()

        }
    }

}