package com.thor.fruitstation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.thor.fruitstation.databinding.ItemAllBinding
import com.udacoding.anggotaapp.Model.getdata.DataItem

class AllItemAdapter(var data: List<DataItem?>?, var c: Context, val itemClick: OnClickListener): RecyclerView.Adapter<AllItemAdapter.MenuHolder>(){

    class MenuHolder(var binding: ItemAllBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data: DataItem?, c: Context, itemClick: OnClickListener) {
            binding.txtNamaItemBuah.text = data?.namaMenu
            Glide.with(c).load(data?.imgUrl).into(binding.imgItemBuah)
            binding.btnHapus.setOnClickListener {
                itemClick.hapus(data)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        val binding =  ItemAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MenuHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        holder.bind(data?.get(position), c, itemClick)
    }

    override fun getItemCount() = data?.size ?: 0

    interface OnClickListener {
        fun hapus(item: DataItem?)
    }
}