package com.example.haizihuijiaba.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.haizihuijiaba.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 伟捷。 on 2015/11/21.
 */
public class BufferActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buffer);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(BufferActivity.this,LoginActivity.class);
                startActivity(intent);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        };
        timer.schedule(timerTask,1800);
    }
}
