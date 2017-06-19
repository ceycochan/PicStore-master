package com.nshane.picstore.interfaces;

/**
 * Created by lbl on 2017-6-19.
 */

public interface DownloadListener {
    void onProgress(int progress);

   void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
