package com.bytecoders.nezamulislamar.videortspplay.presenter;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.VideoView;

/**
 * Created by Nezamul Islam A R on 6/24/2018.
 */

public interface VideoPresenter {
    void videoPresentOnScreen(String rtpsUrl, ProgressDialog pro, VideoView videoView, Context context, ImageButton passPl);
}
