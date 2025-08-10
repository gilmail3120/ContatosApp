package com.example.contatosapp.di

import com.example.contatosapp.data.repository.ContatoRepositoryImpl
import com.example.contatosapp.data.repository.ContatosRepository
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore{
        return FirebaseFirestore.getInstance()
    }

    @Provides
    fun provideContatoRepository(firebaseFirestore: FirebaseFirestore): ContatoRepositoryImpl{
        return ContatosRepository(firebaseFirestore)
    }

}