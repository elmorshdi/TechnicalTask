package com.elmorshdi.technicaltask.view.util

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
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

fun alertDialog(
    title: String,
    message: String,
    context: Context,
    myFunction: (View) -> Unit,
    view: View
) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setIcon(android.R.drawable.ic_dialog_alert)
    //performing positive action
    builder.setPositiveButton("Yes") { _, _ ->
        myFunction(view)
    }
    builder.setNeutralButton("Cancel") { _, _ ->

    }
    val alertDialog: AlertDialog = builder.create()
    alertDialog.setCancelable(false)
    alertDialog.show()
}