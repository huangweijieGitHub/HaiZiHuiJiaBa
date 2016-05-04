package com.example.haizihuijiaba.News;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.NewsActivity;
import com.example.haizihuijiaba.R;

/**
 * Created by 伟捷。 on 2016/1/18.
 */
public class newsactivity extends Activity implements View.OnClickListener {
    private ImageButton imageBtn_fanhui1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nw);
        initView();
    }

    private void initView() {
        imageBtn_fanhui1 =(ImageButton)this.findViewById(R.id.imageBtn_fanhui1);
        imageBtn_fanhui1.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageBtn_fanhui1:
                Intent intent =new Intent(newsactivity.this, NewsActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                break;
        }
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
