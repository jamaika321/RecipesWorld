package ru.radiknasybullin.cameraphone.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import ru.radiknasybullin.cameraphone.data.entities.RecipeList
import ru.radiknasybullin.cameraphone.data.entities.RecipeListObject
import ru.radiknasybullin.cameraphone.data.utils.Resource

fun <T, A> performGetOperation(databaseQuery: () -> LiveData<T>,
                               networkCall: suspend () -> Resource<A>,
                               saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == Resource.Status.SUCCESS){
            saveCallResult(responseStatus.data!!)
        } else if(responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
        emitSource(source)
    }

fun getFavoriteList(databaseQuery: () -> LiveData<Array<RecipeList>>): LiveData<Resource<Array<RecipeList>>> =
    liveData {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map {Resource.success(it)}
        emitSource(source)
}

fun saveRecipeList(databaseQuery: () -> LiveData<RecipeListObject>,
                       networkCall: suspend () -> Resource<RecipeListObject>,
                       saveCallResult: suspend (RecipeListObject) -> Unit,
                   category: String): LiveData<Resource<RecipeListObject>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = databaseQuery.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if(responseStatus.status == Resource.Status.SUCCESS){
            responseStatus.data!!.name = category
            saveCallResult(responseStatus.data!!)
        } else if(responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
        emitSource(source)
    }

