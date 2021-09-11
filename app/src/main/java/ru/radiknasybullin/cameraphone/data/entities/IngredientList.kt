package ru.radiknasybullin.cameraphone.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ingredients")
class IngredientList(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        @SerializedName("strIngredient")
        val strName: String? = "",
        @SerializedName("strDescription")
        val strDescription: String? = "",
        var isHave: Boolean = false
        ) :Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readInt(),
                parcel.readString(),
                parcel.readString(),
                parcel.readByte() != 0.toByte()
        ) {
        }

        override fun describeContents(): Int {
                TODO("Not yet implemented")
        }

        override fun writeToParcel(dest: Parcel?, flags: Int) {
                TODO("Not yet implemented")
        }

        companion object CREATOR : Parcelable.Creator<IngredientList> {
                override fun createFromParcel(parcel: Parcel): IngredientList {
                        return IngredientList(parcel)
                }

                override fun newArray(size: Int): Array<IngredientList?> {
                        return arrayOfNulls(size)
                }
        }
}