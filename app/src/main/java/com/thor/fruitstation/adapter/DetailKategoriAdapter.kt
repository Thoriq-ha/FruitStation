package com.thor.fruitstation.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thor.fruitstation.activity.ItemDetail
import com.thor.fruitstation.databinding.ItemDetailKategoriBinding
import com.udacoding.anggotaapp.Model.getdata.DataItem

class DetailKategoriAdapter(var data: List<DataItem?>?, var c: Context): RecyclerView.Adapter<DetailKategoriAdapter.MenuHolder>(){

    class MenuHolder(var binding: ItemDetailKategoriBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataItem?, c: Context) {
            binding.txtNamaItemBuah.text = data?.namaMenu
            Glide.with(c).load(data?.imgUrl).into(binding.imgItemBuah)

            binding.root.setOnClickListener() {
                val intent = Intent(c, ItemDetail::class.java)
                intent.putExtra("dataitem", data)
                binding.root.context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val binding = ItemDetailKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(data?.get(position), c)
    }

    override fun getItemCount() = data?.size ?: 0
}