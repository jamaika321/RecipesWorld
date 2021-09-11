package ru.radiknasybullin.cameraphone.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val retrofitService: RetrofitServices
): BaseDataSource() {

    suspend fun loadIngredientList() = getResult { retrofitService.loadIngredientList("list") }
    suspend fun loadRecipeListByCategories(category: String) =
        getResult { retrofitService.loadRecipeListByCategories(category) }
    suspend fun loadDishById(id: Int) = getResult { retrofitService.loadDishById(id) }
    suspend fun loadAreaCategoriesList() = getResult { retrofitService.loadAreaCategoriesList("list") }
    suspend fun loadMealCategories() = getResult { retrofitService.loadMealCategories() }
    suspend fun loadRecipeListByArea(area: String) = getResult { retrofitService.loadRecipeListByArea(area) }
}