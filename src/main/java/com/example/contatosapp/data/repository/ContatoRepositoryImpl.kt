package com.example.contatosapp.data.repository

import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo

interface ContatoRepositoryImpl {
    suspend fun salvarContato(novoContato:Contatos)
    suspend fun salvarGrupo(grupos: Grupo)
    suspend fun obterContatos(): List<Contatos>
    suspend fun editar(contato: Contatos, grupos: Grupo)
    suspend fun deletar()

}