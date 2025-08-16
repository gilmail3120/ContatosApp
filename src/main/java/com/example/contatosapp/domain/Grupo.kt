package com.example.contatosapp.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Grupo(
    val nome: String?="",
): Parcelable
