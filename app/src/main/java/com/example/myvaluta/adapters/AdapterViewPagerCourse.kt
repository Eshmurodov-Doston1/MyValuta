package com.example.myvaluta.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myvaluta.R
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.databinding.ItemViewpagerBinding
import com.example.myvaluta.utils.LocaleHelper

class AdapterViewPagerCourse(var context: Context):ListAdapter<CourseEntity,AdapterViewPagerCourse.Vh>(MyDiffUtill()){
    inner class Vh(var itemViewpagerBinding: ItemViewpagerBinding):RecyclerView.ViewHolder(itemViewpagerBinding.root){
       fun onBind(courseEntity: CourseEntity,position: Int){
           val onAttach = LocaleHelper.onAttach(context)
           itemViewpagerBinding.code.text = courseEntity.Ccy
           itemViewpagerBinding.date.text = courseEntity.Date
           itemViewpagerBinding.nameCourse.text = courseEntity.CcyNm_UZ
           itemViewpagerBinding.saleCourse.text = courseEntity.Rate
           itemViewpagerBinding.name.text = onAttach.getText(R.string.name)
           itemViewpagerBinding.sale.text = onAttach.getText(R.string.sale)

       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):Vh {
        return Vh(ItemViewpagerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder:Vh, position: Int) {
        holder.onBind(getItem(position),position)

    }
    class MyDiffUtill():DiffUtil.ItemCallback<CourseEntity>(){
        override fun areItemsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem.id== newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseEntity, newItem: CourseEntity): Boolean {
            return oldItem == newItem
        }

    }
}