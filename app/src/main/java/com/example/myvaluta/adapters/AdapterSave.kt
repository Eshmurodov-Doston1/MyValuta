package com.example.myvaluta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myvaluta.database.entity.SaveSumm
import com.example.myvaluta.databinding.ItemSaveBinding

class AdapterSave(var onItemClickListener: OnItemClickListener):ListAdapter<SaveSumm,AdapterSave.Vh>(MyDiffUtill()) {
    inner class Vh(var itemSaveBinding: ItemSaveBinding):RecyclerView.ViewHolder(itemSaveBinding.root){
        fun onBind(saveSumm: SaveSumm,position: Int){
            itemSaveBinding.date.text = saveSumm.date.substring(0,10)
            itemSaveBinding.summ.text = "${saveSumm.sumDen} ${saveSumm.sale}"
            itemSaveBinding.summ2.text = saveSumm.buy
            itemSaveBinding.summ1.text = saveSumm.sale
            itemSaveBinding.summMon.text = saveSumm.summ
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(saveSumm,position)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemSaveBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }

    class MyDiffUtill:DiffUtil.ItemCallback<SaveSumm>(){
        override fun areItemsTheSame(oldItem: SaveSumm, newItem: SaveSumm): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SaveSumm, newItem: SaveSumm): Boolean {
            return oldItem == newItem
        }
    }
    interface OnItemClickListener{
        fun onItemClick(saveSumm: SaveSumm,position: Int)
    }
}