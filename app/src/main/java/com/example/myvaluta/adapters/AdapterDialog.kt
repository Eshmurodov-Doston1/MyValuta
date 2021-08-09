package com.example.myvaluta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.databinding.ItemDialogBinding

class AdapterDialog(var onItemClickListener: OnItemClickListener,var list: List<CourseEntity>):RecyclerView.Adapter<AdapterDialog.Vh>() {
    inner class Vh(var itemDiaologBinding:ItemDialogBinding):RecyclerView.ViewHolder(itemDiaologBinding.root){
        fun onBind(courseEntity: CourseEntity,position: Int){
            itemDiaologBinding.text.text = courseEntity.CcyNm_UZ
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(courseEntity,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
     return Vh(ItemDialogBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnItemClickListener{
        fun onItemClick(courseEntity: CourseEntity,position: Int)
    }
}