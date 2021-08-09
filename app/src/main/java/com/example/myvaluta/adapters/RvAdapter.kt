package com.example.myvaluta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.databinding.ItemRvBinding
import kotlin.math.cos

class RvAdapter:ListAdapter<CourseEntity,RvAdapter.Vh>(MyDiffUtill()) {
    inner class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(courseEntity: CourseEntity,position: Int){
            itemRvBinding.date.text = courseEntity.Date
            itemRvBinding.name.text = courseEntity.CcyNm_UZ
            itemRvBinding.rate.text =  courseEntity.Rate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(getItem(position),position)
    }

    class MyDiffUtill:DiffUtil.ItemCallback<CourseEntity>(){
        override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem == newItem
        }

    }
}