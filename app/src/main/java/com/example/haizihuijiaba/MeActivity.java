package com.example.haizihuijiaba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.Mes.CompileActivity;
import com.example.haizihuijiaba.Set.SetActivity;

import cn.bmob.v3.Bmob;


/**
 * Created by 伟捷。 on 2015/11/28.
 */
public class MeActivity extends FragmentActivity {

    private RelativeLayout rl_home3,rl_news3,rl_me3;
    private TextView set,tv_home3,tv_news3,tv_me3,tv_compile;
    private int status=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_me);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(MeActivity.this,"c5135f228edfbc0881cd6eede22fc172");

        //初始化事件
        set = (TextView)this.findViewById(R.id.set);
        tv_home3=(TextView)this.findViewById(R.id.tv_home3);
        tv_news3=(TextView)this.findViewById(R.id.tv_news3);
        tv_me3=(TextView)this.findViewById(R.id.tv_me3);
        tv_compile=(TextView)this.findViewById(R.id.tv_compile);
        rl_home3 =(RelativeLayout)this.findViewById(R.id.rl_home3);
        rl_news3 =(RelativeLayout)this.findViewById(R.id.rl_news3);
        rl_me3   =(RelativeLayout)this.findViewById(R.id.rl_me3);


        //添加监听事件
        rl_home3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                        //rl_home3.setBackgroundResource(R.drawable.activity_yanse);
                        tv_home3.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent home3 = new Intent();
                home3.setClass(MeActivity.this,MainActivity.class);
                startActivity(home3);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_news3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                      //  rl_news3.setBackgroundResource(R.drawable.activity_yanse);
                        tv_news3.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent news3 = new Intent();
                news3.setClass(MeActivity.this,NewsActivity.class);
                startActivity(news3);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_me3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                      //  rl_me3.setBackgroundResource(R.drawable.activity_yanse);
                       tv_me3.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent me3 = new Intent();
                me3.setClass(MeActivity.this,MeActivity.class);
                startActivity(me3);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent set1 = new Intent();
                set1.setClass(MeActivity.this, SetActivity.class);
                startActivity(set1);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        tv_compile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeActivity.this, CompileActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }

    /**
     * 连续按两次返回键就退出
     */
    private static long fristTime;
    @Override
    public void onBackPressed() {
        if (fristTime+2000>System.currentTimeMillis()) {
            System.exit(0);
            super.onBackPressed();
        }
        else {
            ToastUtils.toast(this,"再按一次退出程序");
        }
        fristTime=System.currentTimeMillis();
    }
}
