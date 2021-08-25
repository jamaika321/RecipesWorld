package ru.radiknasybullin.cameraphone.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "recipes")
class RecipeList(
    @SerializedName("idMeal")
    val idMeal: Int,
    @PrimaryKey
    @SerializedName("strMeal")
    val strMeal: String,
    @SerializedName("strMealThumb")
    val strMealThumb : String? = ""
        ) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
        parcel.readString().toString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(idMeal)
        parcel.writeString(strMeal)
        parcel.writeString(strMealThumb)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RecipeList> {
        override fun createFromParcel(parcel: Parcel): RecipeList {
            return RecipeList(parcel)
        }

        override fun newArray(size: Int): Array<RecipeList?> {
            return arrayOfNulls(size)
        }
    }
}