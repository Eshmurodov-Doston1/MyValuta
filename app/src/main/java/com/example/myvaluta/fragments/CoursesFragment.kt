package com.example.myvaluta.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.myvaluta.R
import com.example.myvaluta.adapters.AdapterViewPagerCourse
import com.example.myvaluta.adapters.RvAdapter
import com.example.myvaluta.databinding.FragmentCoursesBinding
import com.example.myvaluta.utils.Status
import com.example.myvaluta.viewmodels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CoursesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class CoursesFragment : Fragment() {
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
    val myViewModel: MyViewModel by viewModels()

    val rvAdapter by lazy {
        RvAdapter()
    }
    var handler = Handler(Looper.getMainLooper())
    lateinit var fragmentCoursesBinding: FragmentCoursesBinding
    lateinit var root:View
    val adapterViewPagerCourse by lazy {
        AdapterViewPagerCourse(root.context)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       fragmentCoursesBinding = FragmentCoursesBinding.inflate(inflater,container,false)
        root =fragmentCoursesBinding.root

        myViewModel.getCourse().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    fragmentCoursesBinding.spinKit.visibility = View.INVISIBLE
                    adapterViewPagerCourse.submitList(it.data)
                    fragmentCoursesBinding.viewpager.adapter = adapterViewPagerCourse
                    fragmentCoursesBinding.viewpager.clipToPadding  = false
                    fragmentCoursesBinding.viewpager.clipChildren  = false
                    fragmentCoursesBinding.viewpager.offscreenPageLimit  = 3
                    fragmentCoursesBinding.viewpager.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
                    fragmentCoursesBinding.indicator.attachToPager(fragmentCoursesBinding.viewpager)
                    val compositePageTransformer = CompositePageTransformer()
                    compositePageTransformer.addTransformer(MarginPageTransformer(40))
                    compositePageTransformer.addTransformer { page, position ->
                        var r = 1 - abs(position)
                        page.scaleY = 0.85f + r*0.1f
                    }
                    fragmentCoursesBinding.viewpager.setPageTransformer(compositePageTransformer)
                    fragmentCoursesBinding.viewpager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
                        override fun onPageSelected(position: Int) {
                            super.onPageSelected(position)
                            handler.removeCallbacks(slideRunnable)
                            handler.postDelayed(slideRunnable,3000)
                            if (position== it.data!!.size-1){
                                fragmentCoursesBinding.viewpager.setCurrentItem(0,false)
                            }
                        }
                    })

                    rvAdapter.submitList(it.data)
                    fragmentCoursesBinding.courseRv.adapter=rvAdapter
                    fragmentCoursesBinding.courseRv.isNestedScrollingEnabled = false

                }


                Status.ERROR->{

                }

                Status.LOADING->{
                    fragmentCoursesBinding.spinKit.visibility = View.VISIBLE
                }
            }
        })

        return root
    }

    private val slideRunnable:Runnable = Runnable {
        fragmentCoursesBinding.viewpager.setCurrentItem(fragmentCoursesBinding.viewpager.currentItem+1)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(slideRunnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(slideRunnable,3000)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CoursesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CoursesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}