package com.itmo.basiclayout.task1

import android.os.Parcel
import android.os.Parcelable
import com.itmo.basiclayout.task1.keysEnums.IconEnum

data class ListItemDetails(
    val title: String,
    val description: String,
    val icon: IconEnum
): Parcelable
{
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        IconEnum.valueOf(parcel.readString() ?: IconEnum.values()[0].name),
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(icon.name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListItemDetails> {
        override fun createFromParcel(parcel: Parcel): ListItemDetails {
            return ListItemDetails(parcel)
        }

        override fun newArray(size: Int): Array<ListItemDetails?> {
            return arrayOfNulls(size)
        }
    }
}
