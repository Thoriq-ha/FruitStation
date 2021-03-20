package com.thor.beritahuapp.network

import com.thor.fruitstation.model.getdata.ResponseGetKategori
import com.udacoding.anggotaapp.Model.action.ResponseAction
import com.udacoding.anggotaapp.Model.getdata.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface MenuService {
    //read kategori
    @GET("getKategori.php")
    fun getKategori(): Call<ResponseGetKategori>

    //insert kategori
    @FormUrlEncoded
    @POST("insertKategori.php")
    fun insertKategori(
        @Field("nama_kategori") nama_kategori: String

    ): Call<ResponseAction>

    //update kategori
    @FormUrlEncoded
    @POST("updateKategori.php")
    fun updateKategori(
        @Field("id_kategori") id_kategori: Int,
        @Field("nama_kategori") nama_kategori: String

    ): Call<ResponseAction>

    //delete Kategori
    @FormUrlEncoded
    @POST("deleteKategori.php")
    fun deleteKategori(@Field("id_kategori") id: Int): Call<ResponseAction>



    @GET("getMenu.php?id=2")
    fun getStroberi(): Call<ResponseGetData>

    @GET("getMenu.php?id=1")
    fun getSatu(): Call<ResponseGetData>



    //insert item
    @FormUrlEncoded
    @POST("insertItem.php")
    fun insertItem(
        @Field("nama_menu") nama_menu: String,
        @Field("harga") harga: Int,
        @Field("deskripsi") deskripsi: String,
        @Field("img_url") img_url: String,
        @Field("id_kategori") kategori: Int
    ): Call<ResponseAction>

    //read item
    @GET("getMenu.php")
    fun getMenu(): Call<ResponseGetData>

    //read item by id
//    @FormUrlEncoded
    @GET("getMenu.php")
    fun getDataByKategori(@Query("id_kategori") id: Int): Call<ResponseGetData>

    //delete item
    @FormUrlEncoded
    @POST("deleteItem.php")
    fun deleteData(@Field("id") id: String): Call<ResponseAction>

    //update item
    @FormUrlEncoded
    @POST("updateMenu.php")
    fun updateData(
        @Field("id") id: Int,
        @Field("nama_menu") nama_menu: String,
        @Field("harga") harga: Int,
        @Field("deskripsi") deskripsi: String,
        @Field("img_url") img_url: String,
        @Field("kategori") kategori: Int
    ): Call<ResponseAction>

}