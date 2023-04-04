package shawn.martin.circuit.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shawn.martin.circuit.data.AuthRepository
import shawn.martin.circuit.data.AuthRepositoryImpl

//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.ktx.firestore

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
//    @Provides
//    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // DI where auth is used
    @Provides
    fun auth(): FirebaseAuth = Firebase.auth
    // DI where firestore is used
//    @Provides fun firestore(): FirebaseFirestore = Firebase.firestore

    @Provides
   fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

}