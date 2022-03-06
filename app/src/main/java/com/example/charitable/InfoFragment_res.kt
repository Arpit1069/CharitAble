package com.example.charitable

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*

class InfoFragment_res : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_info, container, false)
        rootView.Cancel.setOnClickListener {
            dismiss()
        }
        rootView.Submit.setOnClickListener {
            val selectedID = ratingRadioGroup.checkedRadioButtonId
            val radio = rootView.findViewById<RadioButton>(selectedID)
            var ratingResult = radio.text.toString()
            Toast.makeText(context, "Thank You for rating us:  $ratingResult", Toast.LENGTH_LONG).show()
            dismiss()

        }

        return rootView
        //return inflater.inflate(R.layout.fragment_info, container, false)

    }

    private fun dismiss() {
        TODO("Not yet implemented")
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            InfoFragment_res().apply {
                arguments = Bundle().apply {}
            }
    }
}