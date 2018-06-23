package com.bytecoders.nezamulislamar.videortspplay.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.bytecoders.nezamulislamar.videortspplay.R;
import com.bytecoders.nezamulislamar.videortspplay.presenter.VideoPresenter;
import com.bytecoders.nezamulislamar.videortspplay.view.VideoFacing;

/**
 * Created by Nezamul Islam A R on 6/24/2018.
 */

public class VideoModdelImplement implements VideoPresenter {
    private VideoFacing vFacing;
    private int flag = 0,stopPosition;

    public VideoModdelImplement(VideoFacing vFacing) {
        this.vFacing = vFacing;
    }

    @Override
    public void videoPresentOnScreen(String rtpsUrl, final ProgressDialog pDialog, final VideoView videoView, Context context, final ImageButton passPl) {

//        videoView.setVideoPath(rtpsUrl);
//        videoView.requestFocus();
//        videoView.start();
        pDialog.setTitle("Video Streaming");
        pDialog.setMessage("Buffering...");
        pDialog.setCancelable(false);
        pDialog.show();

          Log.e("myTag",rtpsUrl);

        try {
            videoView.setVideoPath(rtpsUrl);

        } catch (Exception e) {
            vFacing.videoBufferingError();
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });

        passPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(flag == 0) {
                        stopPosition = videoView.getCurrentPosition(); //stopPosition is an int
                        videoView.pause();
                        flag = 1;
                        passPl.setBackgroundResource(R.drawable.playy);
                    }else if(flag == 1){
                        videoView.seekTo(stopPosition);
                        videoView.start();
                        flag = 0;
                        passPl.setBackgroundResource(R.drawable.pass);
                    }
                }catch (Exception e){
                    vFacing.pressError();
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }

            }
        });

    }

}
