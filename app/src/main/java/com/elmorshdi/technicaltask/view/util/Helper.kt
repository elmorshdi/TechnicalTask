package com.elmorshdi.technicaltask.view.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.elmorshdi.technicaltask.R
import com.elmorshdi.technicaltask.data.model.Product
import com.elmorshdi.technicaltask.databinding.DialogBinding

fun showDialog(
    product: Product,
    context: Context,
    layoutInflater: LayoutInflater
): Dialog {
    val dialog = Dialog(context)

    val dialogBinding = DataBindingUtil.inflate<DialogBinding>(
        layoutInflater,
        R.layout.dialog,
        null,
        false
    )
    dialog.setContentView(dialogBinding.root)
    dialog.setCancelable(true)
    dialogBinding.product=product

    dialog.show()
    return dialog
}