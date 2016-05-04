package com.example.haizihuijiaba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.News.newsactivity;

/**
 * Created by 伟捷。 on 2015/11/28.
 */
public class NewsActivity extends FragmentActivity {
    private  RelativeLayout rl_home2,rl_news2,rl_me2;
    private LinearLayout ll_news;
    private TextView tv_home2,tv_news2,tv_me2;
    private int status = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化事件
        setContentView(R.layout.activity_news);
        ll_news = (LinearLayout)this.findViewById(R.id.ll_news);
         tv_home2=(TextView)this.findViewById(R.id.tv_home2);
        tv_news2=(TextView)this.findViewById(R.id.tv_news2);
        tv_me2=(TextView)this.findViewById(R.id.tv_me2);
        rl_home2 = (RelativeLayout)this.findViewById(R.id.rl_home2);
        rl_news2 =(RelativeLayout)this.findViewById(R.id.rl_news2);
        rl_me2   =(RelativeLayout)this.findViewById(R.id.rl_me2);
        //添加监听事件
        ll_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(NewsActivity.this, newsactivity.class);
                startActivity(intent);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        rl_home2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                       // rl_home2.setBackgroundResource(R.drawable.activity_yanse);
                         tv_home2.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent home2 = new Intent();
                home2.setClass(NewsActivity.this,MainActivity.class);
                startActivity(home2);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_news2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                        //rl_news2.setBackgroundResource(R.drawable.activity_yanse);
                        tv_news2.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent news2 = new Intent();
                news2.setClass(NewsActivity.this,NewsActivity.class);
                startActivity(news2);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_me2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                       // rl_me2.setBackgroundResource(R.drawable.activity_yanse);
                        tv_me2.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent me2 = new Intent();
                me2.setClass(NewsActivity.this,MeActivity.class);
                startActivity(me2);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

    }


    /**
     * 连续按两次返回键就退出
     */
    private static  long firstTime;
    public void onBackPressed() {
        //第一次点击
        if (firstTime+2000>System.currentTimeMillis()) {
            System.exit(0);
            super.onBackPressed();
        }
        else {
            ToastUtils.toast(this,"再按一次退出程序");
        }
        //第二次点击
        firstTime = System.currentTimeMillis();
    }
}
