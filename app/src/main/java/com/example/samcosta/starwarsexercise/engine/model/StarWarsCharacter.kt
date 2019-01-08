package com.example.samcosta.starwarsexercise.engine.model

import android.os.Parcel
import android.os.Parcelable

data class StarWarsCharacter (
        val name: String,
        val height: String,
        val mass: String,
        val hairColor: String,
        val skinColor: String,
        val eyeColor: String,
        val birthYear: String,
        val gender: String,
        val homeworld: String,
        val films: List<String>,
        val species: List<String>,
        val vehicles: List<String>,
        val starships: List<String>,
        val created: String,
        val edited: String,
        val url: String
) {
    fun toSummary() = StarWarsCharacterSummary(name, height, mass, created)
}

data class StarWarsCharacterSummary (
        val name: String,
        val height: String,
        val mass: String,
        val created: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(height)
        parcel.writeString(mass)
        parcel.writeString(created)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<StarWarsCharacterSummary> {
        override fun createFromParcel(parcel: Parcel): StarWarsCharacterSummary {
            return StarWarsCharacterSummary(parcel)
        }

        override fun newArray(size: Int): Array<StarWarsCharacterSummary?> {
            return arrayOfNulls(size)
        }
    }
}

