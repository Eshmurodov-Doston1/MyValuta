package com.example.myvaluta.fragments

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myvaluta.R
import com.example.myvaluta.adapters.ViewPagerAdapter
import com.example.myvaluta.databinding.FragmentHomeBinding
import com.example.myvaluta.utils.LocaleHelper
import me.ibrahimsn.lib.SmoothBottomBar
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    lateinit var fragmentHomeBinding: FragmentHomeBinding
    lateinit var root:View
    lateinit var viewPagerAdapter: ViewPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        root = fragmentHomeBinding.root
        loadUI()
        val onAttach = LocaleHelper.onAttach(root.context)
        val language = LocaleHelper.getLanguage(root.context)
        fragmentHomeBinding.toolbar.title = onAttach.getText(R.string.app_name)
        fragmentHomeBinding.viewpager.isUserInputEnabled = false

        fragmentHomeBinding.bottomNavigatio.setOnItemSelectedListener {
           when(it){
               0->{
                   fragmentHomeBinding.viewpager.currentItem=0
               }
               1->{
                   fragmentHomeBinding.viewpager.currentItem=1
               }
               2->{
                   fragmentHomeBinding.viewpager.currentItem=2
               }
           }
       }
        return root
    }

    private fun loadUI() {
        viewPagerAdapter = ViewPagerAdapter(requireActivity())
        fragmentHomeBinding.viewpager.adapter = viewPagerAdapter
        fragmentHomeBinding.viewpager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0->{
                        fragmentHomeBinding.bottomNavigatio.itemActiveIndex = 0
                    }
                    1->{
                        fragmentHomeBinding.bottomNavigatio.itemActiveIndex = 1
                    }
                    2->{
                        fragmentHomeBinding.bottomNavigatio.itemActiveIndex = 2
                    }
                }
            }
        })
    }
        companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}