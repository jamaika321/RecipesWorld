package ru.radiknasybullin.cameraphone.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.radiknasybullin.cameraphone.BuildConfig
import ru.radiknasybullin.cameraphone.data.api.RemoteDataSource
import ru.radiknasybullin.cameraphone.data.api.RetrofitServices
import ru.radiknasybullin.cameraphone.data.db.dao.Dao
import ru.radiknasybullin.cameraphone.data.db.database.LocalRoomDB
import ru.radiknasybullin.cameraphone.data.repository.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .client(provideOkHttpClient())
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }else{
        OkHttpClient
            .Builder()
            .build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCharacterService(retrofit: Retrofit): RetrofitServices = retrofit.create(RetrofitServices::class.java)

    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: RetrofitServices) = RemoteDataSource(characterService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = LocalRoomDB.getAppDataBase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: LocalRoomDB) = db.dao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: RemoteDataSource,
                          localDataSource: Dao
    ) =
        Repository(remoteDataSource, localDataSource)
}