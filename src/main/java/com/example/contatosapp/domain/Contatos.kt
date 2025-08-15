package com.example.contatosapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contatos(
    var id:String? =null,
    val nome:String="",
    val telefone:String="",
    val email:String="",
    val foto:String?=null,
    val grupoID:String="",
    val nomeGrupo:String="",
    var favorito: Boolean =false
): Parcelable
