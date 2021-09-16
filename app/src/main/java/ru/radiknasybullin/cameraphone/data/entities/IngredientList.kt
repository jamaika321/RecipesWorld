package ru.radiknasybullin.cameraphone.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ingredients")
class IngredientList(
        @PrimaryKey
        @SerializedName("strIngredient")
        val strName: String,
        @SerializedName("strDescription")
        val strDescription: String? = "",
        var isHave: Boolean = false
        ) :Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readString()!!,
                parcel.readString(),
                parcel.readByte() != 0.toByte()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(strName)
                parcel.writeString(strDescription)
                parcel.writeByte(if (isHave) 1 else 0)
        }

        override fun describeContents(): Int {
                return 0
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