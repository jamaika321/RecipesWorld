package ru.radiknasybullin.cameraphone.data.entities

import com.google.gson.annotations.SerializedName

class AreaCategoriesObject (
        @SerializedName("meals")
        val areaCategoriesList: Array<AreaCategoriesList>
        )