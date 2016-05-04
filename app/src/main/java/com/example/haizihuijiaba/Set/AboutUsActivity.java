package com.example.haizihuijiaba.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageButton;
import com.example.haizihuijiaba.R;

/**
 * Created by 伟捷。 on 2015/12/29.
 */
public class AboutUsActivity extends FragmentActivity {
    private ImageButton img_fanhui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        //初始化事件
        img_fanhui = (ImageButton)this.findViewById(R.id.img_fanhui);
        //添加监听事件
        img_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fan = new Intent();
                fan.setClass(AboutUsActivity.this,SetActivity.class);
                startActivity(fan);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
}
