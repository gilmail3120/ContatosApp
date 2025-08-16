package com.example.contatosapp.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contatosapp.data.repository.ContatosRepository
import com.example.contatosapp.domain.Grupo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelecionarGrupoViewModel @Inject constructor(val contatosRepository: ContatosRepository): ViewModel() {

    val _listaGrupos = MutableLiveData<List<Grupo>>()
    val listaGrupo: LiveData<List<Grupo>>
        get() = _listaGrupos

    val _mensagem = MutableLiveData<String>()
    val mensagem: LiveData<String>
        get() = _mensagem

    fun obterListaGrupos(){
        viewModelScope.launch {
            try {
                _listaGrupos.value = contatosRepository.obterGrupos()
            } catch (e: Exception){
                _mensagem.value = "Erro ao carregar lista de grupo"
            }
        }
    }
    fun salvarGrupo(novoGrupo: Grupo){
        viewModelScope.launch {
            contatosRepository.salvarGrupo(novoGrupo)
        }
    }
}