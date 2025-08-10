package com.example.contatosapp.data.repository
import com.google.firebase.firestore.ktx.toObjects
import android.util.Log
import androidx.navigation.serialization.generateRouteWithArgs
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ContatosRepository @Inject constructor(val db: FirebaseFirestore): ContatoRepositoryImpl {

    private val gruposCollection = db.collection("grupos")
    private val contatosCollection = db.collection("contatos")


    override suspend fun salvarContato(novoContato: Contatos) {
        try {
            val contatoRef =contatosCollection.add(novoContato).await()
        }catch (e: Exception){
            Log.i("contatosRepository", "salvarContato: erro ao salvar contato $e")
        }

    }

    override suspend fun salvarGrupo(grupos: Grupo) {

        try {
            val documentoReference = gruposCollection.add(grupos).await()
            Log.i("contatosRepository", "Grupo salvo :$documentoReference ")

        }catch (e: Exception){
            Log.e("contatosRepository", " erro ao salvar grupo:$e ", )
        }

    }

    override suspend fun obterContatos(): List<Contatos> {
       return try {
           val querySnaptShot = contatosCollection.get().await()
           Log.i("contatoRepository", "obterContatos: $querySnaptShot")
           val contatos = querySnaptShot.toObjects<Contatos>()
           Log.i("contatoRepository", "obterContatos: $contatos")
           contatos
       }catch(e: Exception){
           Log.i("contatosRepository", "obterContatos:$e ")
           emptyList<Contatos>()
       }
    }


    override suspend fun editar(
        contato: Contatos,
        grupos: Grupo
    ) {

    }

    override suspend fun deletar() {

    }
}