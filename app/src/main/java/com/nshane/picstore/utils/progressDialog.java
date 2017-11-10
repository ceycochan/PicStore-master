package com.nshane.picstore.utils;

import android.app.Activity;
import android.content.Context;


public class ProgressDialog {
    private static ProgressDialog ourInstance = new ProgressDialog();

    public static ProgressDialog getInstance() {
        return ourInstance;
    }

    private ProgressDialog() {
    }

    public android.app.ProgressDialog mProgressDialog;

    public void showProgress(Activity m, String msg) {
        try {
            mProgressDialog = android.app.ProgressDialog.show(m
                    , null
                    , msg
                    , true);
            mProgressDialog.setCancelable(false);
        } catch (Exception e) {

        }

    }

    public void showProgress(Context m, String msg) {
        try {
            mProgressDialog = android.app.ProgressDialog.show(m
                    , null
                    , msg
                    , true);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setCanceledOnTouchOutside(false);
        } catch (Exception e) {

        }

    }

    public void showProgress(Activity m, String msg, boolean flag) {
        mProgressDialog = android.app.ProgressDialog.show(m
                , null
                , msg
                , true);
        mProgressDialog.setCancelable(flag);
    }

    public void cancelProgress() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.cancel();
            }
        } catch (Exception e) {
            LogUtil.d(" cancel dialog !");
        }

    }
}
