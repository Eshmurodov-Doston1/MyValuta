package com.example.myvaluta.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myvaluta.fragments.ConvertationFragment
import com.example.myvaluta.fragments.CoursesFragment
import com.example.myvaluta.fragments.SettingsFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                CoursesFragment()
            }
            1->{
                ConvertationFragment()
            }
            2->{
                SettingsFragment()
            }
            else->{
                CoursesFragment()
            }
        }
    }
}