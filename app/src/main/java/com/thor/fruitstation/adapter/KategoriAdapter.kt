package com.thor.fruitstation.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thor.fruitstation.activity.AddUpdateKategori
import com.thor.fruitstation.activity.KategoriDetail
import com.thor.fruitstation.databinding.ItemKategoriBinding
import com.thor.fruitstation.model.getdata.DataKategori
import kotlin.random.Random

class KategoriAdapter(
    var data: List<DataKategori?>?,
    var c: Context,
    val onClick: KategoriAdapter.OnClickListener
) : RecyclerView.Adapter<KategoriAdapter.kategoriHolder>() {
    class kategoriHolder(var binding: ItemKategoriBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataKategori?, c: Context, itemClick: KategoriAdapter.OnClickListener) {
            binding.txtNamaKategori.text = data?.namaKategori

            binding.txtHapusKategori.setOnClickListener {
                itemClick.hapus(data)
            }


            binding.txtUpdate.setOnClickListener {
                val intent = Intent(c, AddUpdateKategori::class.java)
                intent.putExtra("isupdate", "true")
                intent.putExtra("kategori", data)

                binding.root.context.startActivity(intent)
            }
            binding.layoutKategori.setOnClickListener(){
                val intent = Intent(c, KategoriDetail::class.java)
                intent.putExtra("kategori", data)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kategoriHolder {
        val binding = ItemKategoriBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return kategoriHolder(binding)
    }

    override fun onBindViewHolder(holder: kategoriHolder, position: Int) {
        holder.bind(data?.get(position), c, onClick)
    }

    override fun getItemCount() = data?.size ?: 0

    interface OnClickListener {
        fun hapus(item: DataKategori?)
    }
}



