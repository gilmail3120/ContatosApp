package com.example.contatosapp.data.repository

import android.net.Uri
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo

interface ContatoRepositoryImpl {
    suspend fun salvarContato(novoContato:Contatos,uriFoto: Uri?,grupoId:String)
    suspend fun salvarGrupo(grupos: Grupo?):String?
    suspend fun favoritar(contatoId:String,favoritar: Boolean)
    suspend fun obterFavoritos():List<Contatos>
    suspend fun obterContatos(): List<Contatos>
    suspend fun obterGrupos(): List<Grupo>
    suspend fun editar(contato: Contatos, grupos: Grupo)
    suspend fun deletar()

}