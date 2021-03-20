package com.thor.fruitstation.model.getdata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataKategori(

	@field:SerializedName("id_kategori")
	val idKategori: Int? = null,

	@field:SerializedName("nama_kategori")
	val namaKategori: String? = null
) : Parcelable
