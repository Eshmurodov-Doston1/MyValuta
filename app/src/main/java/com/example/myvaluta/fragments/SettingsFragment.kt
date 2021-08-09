package com.example.myvaluta.fragments

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentContainer
import com.example.myvaluta.App
import com.example.myvaluta.R
import com.example.myvaluta.databinding.FragmentSettingsBinding
import com.example.myvaluta.databinding.InfoDilaogBinding
import com.example.myvaluta.databinding.LanguageDialogBinding
import com.example.myvaluta.utils.LocaleHelper
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
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
    lateinit var fragmentSettingsBinding: FragmentSettingsBinding
    lateinit var root:View
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       fragmentSettingsBinding = FragmentSettingsBinding.inflate(inflater,container,false)
        root = fragmentSettingsBinding.root

        fragmentSettingsBinding.language.setOnClickListener {
        val alert = AlertDialog.Builder(root.context, R.style.BottomSheetDialogThem)
            val create = alert.create()
            val dialogBinding = LanguageDialogBinding.inflate(LayoutInflater.from(root.context), null, false)
            create.setView(dialogBinding.root)
            val language = LocaleHelper.getLanguage(root.context)
            when(language.lowercase(Locale.getDefault())){
                "uz".lowercase(Locale.getDefault())->{
                    dialogBinding.uzb.isChecked=true
                }
                "en".lowercase(Locale.getDefault())->{
                    dialogBinding.ru.isChecked=true
                }
                "ru".lowercase(Locale.getDefault())->{
                    dialogBinding.en.isChecked=true
                }
            }
            dialogBinding.okBtn.setOnClickListener {
                val checkedRadioButtonId = dialogBinding.radioGroup.checkedRadioButtonId
                when(checkedRadioButtonId){
                    R.id.uzb->{
                        LocaleHelper.setLocale(root.context,"uz")
                        dialogBinding.uzb.isChecked=true
                        activity?.recreate()
                    }
                    R.id.en->{
                        LocaleHelper.setLocale(root.context,"en")
                        dialogBinding.en.isChecked=true
                        activity?.recreate()
                    }
                    R.id.ru->{
                        LocaleHelper.setLocale(root.context,"ru")
                        dialogBinding.ru.isChecked=true
                        activity?.recreate()
                    }
                }
                create.dismiss()
            }
            dialogBinding.noBtn.setOnClickListener {
                create.dismiss()
            }
            create.show()
        }


        sharedPreferences = (activity as AppCompatActivity).getSharedPreferences("night",0)
        val boolean = sharedPreferences.getBoolean("my_night", false)

        fragmentSettingsBinding.myswitch.isChecked = boolean

        fragmentSettingsBinding.myswitch.addSwitchObserver { switchView, isChecked ->
            if(isChecked){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                //(activity as AppCompatActivity).recreate()
                var editor =  sharedPreferences.edit()
                editor.putBoolean("my_night",true)
                editor.apply()
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                //(activity as AppCompatActivity).recreate()
                var editor =  sharedPreferences.edit()
                editor.putBoolean("my_night",false)
                editor.apply()
            }
        }

        fragmentSettingsBinding.info.setOnClickListener {
            val alertDialog = AlertDialog.Builder(root.context, R.style.BottomSheetDialogThem)
            val create = alertDialog.create()
            val dialogMy = InfoDilaogBinding.inflate(LayoutInflater.from(root.context), null, false)
            create.setView(dialogMy.root)
            create.show()
        }

        fragmentSettingsBinding.shareApp.setOnClickListener {
            var shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT,"Hello")
                this.type="text/plain"
            }
            startActivity(shareIntent)
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
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}