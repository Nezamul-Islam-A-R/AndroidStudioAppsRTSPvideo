package com.bytecoders.nezamulislamar.videortspplay;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

import com.bytecoders.nezamulislamar.videortspplay.model.VideoModdelImplement;
import com.bytecoders.nezamulislamar.videortspplay.presenter.VideoPresenter;
import com.bytecoders.nezamulislamar.videortspplay.view.VideoFacing;

public class MainActivity extends AppCompatActivity implements VideoFacing{

    private VideoPresenter vp;
    VideoView videoView;
    ProgressDialog pDialog;
    ImageButton passPl;
    MediaPlayer mp;

    String  videoRtspUrl="rtsp://184.72.239.149/vod/mp4:BigBuckBunny_175k.mov";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passPl = (ImageButton)findViewById(R.id.passPlay);
        videoView = (VideoView) this.findViewById(R.id.videoView);

        vp = new VideoModdelImplement(MainActivity.this);
        pDialog = new ProgressDialog(MainActivity.this);
        vp.videoPresentOnScreen(videoRtspUrl,pDialog, videoView, MainActivity.this, passPl);

    }


    @Override
    public void videoBufferingError() {
        Toast.makeText(this,"Video Facing Error",Toast.LENGTH_LONG).show();
    }

    @Override
    public void pressError() {
        Toast.makeText(this,"Video Pass & Play Error",Toast.LENGTH_LONG).show();
    }
}
