package com.tahir.omiseassignment.Helpers

import android.app.ProgressDialog
import android.content.Context


class ProgressDialogHelper {
    /**
     * Show ProgressDialog
     *
     * This method is used to show .
     *
     * @param Context .
     * @return ProgressDialog
     *
     *
     */

    fun showDialog(c: Context): ProgressDialog {
        val pd = ProgressDialog(c)
        pd.setMessage("loading....please wait")
        pd.show()

        return pd
    }

    companion object {
        /**
         * Cancels ProgressDialog
         *
         * This method is used to show .
         *
         * @param ProgressDialog .
         *
         * @return doesnot return anything
         *
         *
         */
        fun cancelDialog(pDialog: ProgressDialog) {
            pDialog.cancel()
        }
    }


}
