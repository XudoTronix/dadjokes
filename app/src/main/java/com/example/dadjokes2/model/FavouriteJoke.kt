package com.example.dadjokes2.model

import android.os.Parcel
import android.os.Parcelable

data class FavouriteJoke(
    var iddoc: String? = "",
    var id: String? = "",
    var joke: String? = "",
    var email: String? = ""
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(iddoc)
        parcel.writeString(id)
        parcel.writeString(joke)
        parcel.writeString(email)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FavouriteJoke> {
        override fun createFromParcel(parcel: Parcel): FavouriteJoke {
            return FavouriteJoke(parcel)
        }

        override fun newArray(size: Int): Array<FavouriteJoke?> {
            return arrayOfNulls(size)
        }
    }
}
