package com.example.contatosapp.data.repository
import android.net.Uri
import android.util.Log
import com.example.contatosapp.domain.Contatos
import com.example.contatosapp.domain.Grupo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObjects
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ContatosRepository @Inject constructor(val db: FirebaseFirestore,val fbStorage: FirebaseStorage): ContatoRepositoryImpl {

    private val gruposCollection = db.collection("grupos")
    private val contatosCollection = db.collection("contatos")
    private val firebaseStorage = fbStorage.getReference("fotos")


    override suspend fun salvarContato(novoContato: Contatos,uriFoto: Uri?,grupoId:String) {

        try {
            val documentoReferencia =contatosCollection.add(novoContato).await()
            val contatoiD = documentoReferencia.id
            if (uriFoto!=null){
                val referenciaFoto = firebaseStorage.child("foto_perfil").child("$contatoiD.jpeg")
                referenciaFoto.putFile(uriFoto).await()

                val urlFotoPerfil = referenciaFoto.downloadUrl.await().toString()
                documentoReferencia.update("foto",urlFotoPerfil).await()
                Log.i("salvarContatoRepository", "Contato salvo com foto $urlFotoPerfil")
            }else{
                Log.i("salvarContatoRepository", "Contato salvo sem foto$uriFoto ")
            }

        }catch (e: Exception){
            Log.e("ContatosRepository", "salvarContato: erro na atualização do documento", e)
        }
    }

    override suspend fun salvarGrupo(grupos: Grupo?):String? {
    return try {
            if (grupos!=null){
                val documentoReference = gruposCollection.add(grupos).await()
                Log.i("contatosRepository", "Grupo salvo :$documentoReference ")
                 return documentoReference.id
            }else{
                null
            }
        }catch (e: Exception){
            Log.e("contatosRepository", " erro ao salvar grupo:$e ", )
        null
        }
    }

    override suspend fun obterContatos(): List<Contatos> {
       return try {
           val querySnaptShot = contatosCollection.get().await()
           Log.i("contatoRepository", "obterContatos: $querySnaptShot")
           val contatos = querySnaptShot.toObjects<Contatos>()
           Log.i("contatoRepository", "obterContatos: $contatos")
           val contatoComGrupo =contatos.map { contatos->
               if (contatos.grupoID !=null){
                   val grupoDoc = gruposCollection.document(contatos.grupoID).get().await()
                   val nomeGrupo=grupoDoc.getString("nome")
                   contatos.copy(nomeGrupo = nomeGrupo.toString())
               }else{
                   contatos
               }
           }
           return contatoComGrupo
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