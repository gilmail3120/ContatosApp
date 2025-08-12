package com.example.contatosapp.data.repository

import android.net.Uri
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo

interface ContatoRepositoryImpl {
    suspend fun salvarContato(novoContato:Contatos,uriFoto: Uri?)
    suspend fun salvarGrupo(grupos: Grupo)
    suspend fun obterContatos(): List<Contatos>
    suspend fun editar(contato: Contatos, grupos: Grupo)
    suspend fun deletar()

}