package com.example.myvaluta.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myvaluta.R
import com.example.myvaluta.adapters.AdapterDialog
import com.example.myvaluta.adapters.AdapterSave
import com.example.myvaluta.database.AppDatabase
import com.example.myvaluta.database.entity.CourseEntity
import com.example.myvaluta.database.entity.SaveSumm
import com.example.myvaluta.databinding.DialogsaveBinding
import com.example.myvaluta.databinding.FragmentConvertationBinding
import com.example.myvaluta.databinding.MydialogBinding
import com.example.myvaluta.utils.Status
import com.example.myvaluta.viewmodels.MyViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConvertationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class ConvertationFragment : Fragment() {
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
    val myViewModel:MyViewModel by viewModels()
    lateinit var fragmentConvertationBinding: FragmentConvertationBinding
    lateinit var root:View
    lateinit var adapterDialog:AdapterDialog
    lateinit var appDatabase: AppDatabase
    lateinit var  adapterSave:AdapterSave
    var position1:Int=0
    var position2:Int=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     fragmentConvertationBinding= FragmentConvertationBinding.inflate(inflater,container,false)
        root = fragmentConvertationBinding.root
        myViewModel.getCourse().observe(viewLifecycleOwner, Observer {
            when(it.status){
                Status.SUCCESS->{
                    position1 = 0
                    position2 = it.data!!.size-1
                    fragmentConvertationBinding.course.text = it.data[position1].Ccy
                    fragmentConvertationBinding.course1.text = it.data[position2].Ccy
                    fragmentConvertationBinding.line1.setOnClickListener { l1->
                        var alertDialog =  AlertDialog.Builder(root.context,R.style.BottomSheetDialogThem)
                        val create = alertDialog.create()
                        val mydialog = MydialogBinding.inflate(LayoutInflater.from(root.context), null, false)
                        create.setView(mydialog.root)
                        adapterDialog = AdapterDialog(object:AdapterDialog.OnItemClickListener{
                            override fun onItemClick(courseEntity: CourseEntity, position: Int) {
                                if (it.data[position2].Ccy.lowercase(Locale.getDefault())!=courseEntity.Ccy.lowercase(Locale.getDefault())){
                                    position1 = position
                                    fragmentConvertationBinding.course.text = courseEntity.Ccy
                                    val summ = fragmentConvertationBinding.summ.text.toString().trim()
                                    if (summ.isNotEmpty()){
                                        val rate1 = it.data[position1].Rate.toDouble()
                                        val rate2 = it.data[position2].Rate.toDouble()
                                        var summ1 =(summ.toDouble()*rate1)/rate2
                                        var summ2 =(summ.toDouble()*rate1)%rate2
                                        fragmentConvertationBinding.summVal.text = "${summ1.toLong()}.${summ2.toString().subSequence(0,2)} ${it.data[position2].Ccy}"
                                    }
                                    create.dismiss()
                                }
                            }
                        }, it.data)
                        mydialog.rv.adapter = adapterDialog
                        create.show()
                    }

                    fragmentConvertationBinding.line2.setOnClickListener { l2->
                        var alertDialog =  AlertDialog.Builder(root.context,R.style.BottomSheetDialogThem)
                        val create = alertDialog.create()
                        val mydialog = MydialogBinding.inflate(LayoutInflater.from(root.context), null, false)
                        create.setView(mydialog.root)
                        adapterDialog = AdapterDialog(object:AdapterDialog.OnItemClickListener{
                            override fun onItemClick(courseEntity: CourseEntity, position: Int) {
                                if (it.data[position1].Ccy.lowercase(Locale.getDefault())!=courseEntity.Ccy.lowercase(Locale.getDefault())) {
                                    position2 = position
                                    fragmentConvertationBinding.course1.text = courseEntity.Ccy
                                    val summ = fragmentConvertationBinding.summ.text.toString().trim()
                                    if (summ.isNotEmpty()){
                                        val rate1 = it.data[position1].Rate.toDouble()
                                        val rate2 = it.data[position2].Rate.toDouble()
                                        var summ1 =(summ.toDouble()*rate1)/rate2
                                        var summ2 =(summ.toDouble()*rate1)%rate2
                                        fragmentConvertationBinding.summVal.text = "${summ1.toLong()}.${summ2.toString().subSequence(0,2)} ${it.data[position2].Ccy}"
                                    }
                                    create.dismiss()
                                }
                            }
                        }, it.data)
                        mydialog.rv.adapter = adapterDialog
                        create.show()
                    }

                    fragmentConvertationBinding.conve1.setOnClickListener {v->
                        var position:Int
                        position = position1
                        position1 = position2
                        position2 = position
                        fragmentConvertationBinding.course.text = it.data[position1].Ccy
                        fragmentConvertationBinding.course1.text = it.data[position2].Ccy
                        val summ = fragmentConvertationBinding.summ.text.toString().trim()
                        if (summ.isNotEmpty()){
                            val rate1 = it.data[position1].Rate.toDouble()
                            val rate2 = it.data[position2].Rate.toDouble()
                            var summ1 =(summ.toDouble()*rate1)/rate2
                            var summ2 =(summ.toDouble()*rate1)%rate2
                            fragmentConvertationBinding.summVal.text = "${summ1.toLong()}.${summ2.toString().subSequence(0,2)} ${it.data[position2].Ccy}"
                        }
                    }
                    fragmentConvertationBinding.summ.addTextChangedListener {er->
                        val summ = er.toString().trim()
                        if (summ.isNotEmpty()){
                            fragmentConvertationBinding.summVal.visibility = View.VISIBLE
                            val rate1 = it.data[position1].Rate.toDouble()
                            val rate2 = it.data[position2].Rate.toDouble()
                            var summ1 =(summ.toDouble()*rate1)/rate2
                            var summ2 =(summ.toDouble()*rate1)%rate2
                            fragmentConvertationBinding.summVal.text = "${summ1.toLong()}.${summ2.toString().subSequence(0,2)} ${it.data[position2].Ccy}"
                        }else{
                            fragmentConvertationBinding.summVal.text=""
                            fragmentConvertationBinding.summVal.visibility = View.INVISIBLE
                        }
                        /*val l = (toInt)/(listSpinner[position2].cb_price.toDouble())
                           val l1 = (it.toString().toDouble())%(listSpinner[position2].cb_price.toDouble())
                           t="${l.toLong()}.${l1.toString().substring(0,2)}"*/
                        // myViewModel.getSaveDao()
                    }

                    adapterSave = AdapterSave(object:AdapterSave.OnItemClickListener{
                        override fun onItemClick(saveSumm: SaveSumm, position: Int) {
                            var alertDialog = AlertDialog.Builder(root.context,R.style.BottomSheetDialogThem)
                            val create = alertDialog.create()
                            val dialogsaveBinding = DialogsaveBinding.inflate(
                                LayoutInflater.from(root.context),
                                null,
                                false
                            )
                            create.setView(dialogsaveBinding.root)
                            dialogsaveBinding.delete.setOnClickListener {
                                myViewModel.getSaveDao().deleteSave(saveSumm)
                                create.dismiss()
                            }
                            dialogsaveBinding.share.setOnClickListener {
                                var shareIntent = Intent().apply {
                                    this.action = Intent.ACTION_SEND
                                    this.putExtra(Intent.EXTRA_TEXT,"${saveSumm.sumDen} ${saveSumm.sale}\n${saveSumm.summ}\n${saveSumm.date}")
                                    this.type="text/plain"
                                }
                                startActivity(shareIntent)
                            }
                            create.show()
                        }
                    })
                    fragmentConvertationBinding.save.setOnClickListener {l->
                        val summ = fragmentConvertationBinding.summVal.text.toString().trim()
                        if (summ.isNotEmpty() && fragmentConvertationBinding.summ.text.toString().trim().isNotEmpty()){
                            val date = SimpleDateFormat("dd.MM.yyyy mm:HH").format(Date())
                            val summ1 = fragmentConvertationBinding.summ.text.toString().trim()
                            var saveSumm = SaveSumm(summ = summ,sale = it.data[position1].Ccy,buy = it.data[position2].Ccy,date = date,sumDen = summ1)
                            myViewModel.getSaveDao().insertSave(saveSumm)
                        }
                    }
                    myViewModel.getSaveDao().getSave().observe(viewLifecycleOwner, Observer {
                        adapterSave.submitList(it)
                        fragmentConvertationBinding.rvMy.adapter = adapterSave
                    })

                }
                Status.ERROR->{

                }
                Status.LOADING->{

                }
            }
        })

        fragmentConvertationBinding.conve1.setOnClickListener {
            //fragmentConvertationBinding.conve1.animation =AnimationUtils.loadAnimation(root.context,R.anim.button_anim)
        }

        return root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ConvertationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConvertationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}