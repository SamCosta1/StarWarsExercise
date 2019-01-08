package com.example.samcosta.starwarsexercise.ui

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.os.Bundle
import com.example.samcosta.starwarsexercise.R

/**
 * Created by samcosta on 08/01/2019.
 *
 * Modified from the sample at https://developer.android.com/guide/topics/ui/dialogs
 */
class RequestFailedDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "RequestFailedDialogFragment"
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.network_request_failed_message)
                    .setNegativeButton(R.string.cancel, { dialog, id ->
                                dialog.dismiss()
                            })
            builder.create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }
}