package com.example.haizihuijiaba.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.haizihuijiaba.MeActivity;
import com.example.haizihuijiaba.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 伟捷。 on 2015/12/27.
 */
public class SetActivity extends FragmentActivity {
    //初始化事件
    @Bind(R.id.imageBtn_hui)
    ImageButton imageBtnHui;
    @Bind(R.id.ll_release)
    LinearLayout llRelease;
    @Bind(R.id.ll_feedback)
    LinearLayout llFeedback;
    @Bind(R.id.ll_dcm)
    LinearLayout llDcm;
    @Bind(R.id.LL)
    LinearLayout LL;
    @Bind(R.id.btn_exit)
    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
    }



    //添加监听事件
    @OnClick({R.id.imageBtn_hui, R.id.ll_release, R.id.ll_feedback,
            R.id.ll_dcm, R.id.LL, R.id.btn_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageBtn_hui:
                Intent hui = new Intent();
                hui.setClass(SetActivity.this, MeActivity.class);
                startActivity(hui);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                finish();
                break;
            case R.id.ll_release:
                Intent release = new Intent();
                release.setClass(SetActivity.this, ReleaseActivity.class);
                startActivity(release);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                finish();
                break;
            case R.id.ll_feedback:
                Intent feed = new Intent();
                feed.setClass(SetActivity.this, FeedBackActivity.class);
                startActivity(feed);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                finish();
                break;
            case R.id.ll_dcm:

                break;
            case R.id.LL:
                Intent ll = new Intent();
                ll.setClass(SetActivity.this, AboutUsActivity.class);
                startActivity(ll);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                finish();
                break;
            case R.id.btn_exit:
                //退出
                System.exit(0);
                break;
        }

    }

}
