package com.arifrgilang.presentation.main.fragment.profile

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.arifrgilang.presentation.R
import com.arifrgilang.presentation.main.fragment.dashboard.model.StudentUiModel
import com.arifrgilang.presentation.util.loadImage
import com.arifrgilang.presentation.util.loadPath
import com.arifrgilang.presentation.util.logFirebase


/**
 * Created by arifrgilang on 4/22/2021
 */
class ProfileDialogFragment(
    private val student: StudentUiModel,
    private val callback: OnClickLogout
    ): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        logFirebase()
        val view = LayoutInflater
            .from(requireContext())
            .inflate(R.layout.fragment_profile_dialog, null)
        val builder = AlertDialog
            .Builder(requireContext())
            .setView(view)
        setupView(view)
        return builder.create()
    }

    private fun setupView(view: View?) {
        student.data?.let{ data ->
            view?.let{
                view.findViewById<ImageView>(R.id.iv_profile_dialog)
                    .loadImage(data.imageUrl, CircularProgressDrawable(requireContext()))
                view.findViewById<TextView>(R.id.tv_name).text = data.name
                view.findViewById<TextView>(R.id.tv_npm).text = data.npm
                view.findViewById<TextView>(R.id.tv_email).text = data.email
                view.findViewById<TextView>(R.id.tv_paus_name).text = data.pausName
                view.findViewById<TextView>(R.id.tv_gender).text = data.gender
                view.findViewById<TextView>(R.id.tv_entry_year).text = data.entryYear
                view.findViewById<TextView>(R.id.tv_phone_number).text = data.phoneNumber
                view.findViewById<TextView>(R.id.tv_faculty).text = data.faculty
                view.findViewById<TextView>(R.id.btn_logout).setOnClickListener {
                    callback.performLogout()
                    dismiss()
                }
            }
        }
    }

    interface OnClickLogout {
        fun performLogout()
    }
}