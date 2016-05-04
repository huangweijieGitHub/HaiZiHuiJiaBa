package com.example.haizihuijiaba.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.R;

/**
 * Created by 伟捷。 on 2015/12/31.
 */
public class ReleaseActivity extends FragmentActivity {
    private ImageButton fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        //初始化控件
        fanhui =(ImageButton)this.findViewById(R.id.fanhui);

        //添加监听事件
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fanhui1 = new Intent();
                fanhui1.setClass(ReleaseActivity.this, SetActivity.class);
                startActivity(fanhui1);
                //实现淡入淡出的效果
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
