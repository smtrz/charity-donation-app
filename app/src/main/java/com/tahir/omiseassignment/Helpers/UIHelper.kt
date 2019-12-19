package com.tahir.omiseassignment.Helpers


import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.provider.Settings
import android.text.TextUtils.TruncateAt
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.snackbar.Snackbar

object UIHelper {


    fun showLongToastInCenter(ctx: Context?, messageId: Int) {

        if (ctx == null) return

        val toast = Toast.makeText(ctx, messageId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showLongToastInCenter(ctx: Context?, message: String) {
        if (ctx == null) return
        //        message = Strings.nullToEmpty(message);
        val toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun showLongToastInBottom(ctx: Context?, message: String) {
        if (ctx == null) return
        //        message = Strings.nullToEmpty(message);
        val toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 0)
        toast.show()
    }

    fun showShortToastInCenter(ctx: Context?, message: String) {
        if (ctx == null) return
        //        message = Strings.nullToEmpty(message);


        val toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)


        toast.show()
    }

    fun showShortToastInCenter(ctx: Context?, message: Int) {
        if (ctx == null) return
        val toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }


    fun hideSoftKeyboard(context: Context?, editText: EditText) {
        if (context == null) return
        try {
            val imm = context
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(editText.windowToken, 0)
        } catch (t: Throwable) {
        }

    }

    fun hideSoftKeyboard(context: Context?, view: View) {
        if (context == null) return
        val imm = context
            .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }

    fun showAlertDialog(
        message: String, title: CharSequence,
        context: Context?
    ) {

        if (context == null) return
        val builder = AlertDialog.Builder(context)
        builder.setMessage(message)
            .setTitle(title)
            .setCancelable(true)
            .setNegativeButton(
                "OK"
            ) { dialog, id -> dialog.cancel() }

        val alert = builder.create()
        alert.show()
    }

    fun locateView(v: View?): Rect? {
        val loc_int = IntArray(2)
        if (v == null)
            return null
        try {
            v.getLocationOnScreen(loc_int)
        } catch (npe: NullPointerException) {
            // Happens when the view doesn't exist on screen anymore.
            return null
        }

        val location = Rect()
        location.left = loc_int[0]
        location.top = loc_int[1]
        location.right = location.left + v.width
        location.bottom = location.top + v.height
        return location
    }

    fun textMarquee(txtView: TextView) {
        txtView.ellipsize = TruncateAt.END
    }


    fun dimBehind(dialog: Dialog) {
        val lp = dialog.window!!.attributes
        lp.dimAmount = 0.9f
        dialog.window!!.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        dialog.setCancelable(false)
    }


    fun truncate(str: String, len: Int): String {
        return if (str.length > len) {
            str.substring(0, len) + "..."
        } else {
            str
        }
    }


    fun showSnackToast(view: View?, message: String) {
        if (view == null) {
            return
        }
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }


    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    fun showSettingsDialog(activity: Activity) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Need Permissions")
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.")
        builder.setPositiveButton("GOTO SETTINGS") { dialog, which ->
            dialog.cancel()
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivityForResult(intent, 101)
        }
        builder.setNegativeButton("Cancel") { dialog, which -> dialog.cancel() }
        builder.show()

    }


}