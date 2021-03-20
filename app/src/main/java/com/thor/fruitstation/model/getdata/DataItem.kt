package com.udacoding.anggotaapp.Model.getdata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataItem(

	@field:SerializedName("nama_menu")
	val namaMenu: String? = null,

	@field:SerializedName("harga")
	val harga: String? = null,

	@field:SerializedName("img_url")
	val imgUrl: String? = null,

	@field:SerializedName("kategori")
	val id_kategori: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

) : Parcelable

