package com.example.contatosapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contatos(
    val nome:String="",
    val telefone:String="",
    val email:String="",
    val foto:String="",
    val grupoID:String="",
    val nomeGrupo:String="",
    val favorito: Boolean =false
): Parcelable
