package com.example.haizihuijiaba.Set;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haizihuijiaba.Class.Content;
import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

/**
 * Created by 伟捷。 on 2015/12/30.
 */
public class FeedBackActivity extends FragmentActivity implements View.OnClickListener {
    private ImageButton imageBtn_fan;
    private EditText edt_Content;
    private Button btn_tijiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(FeedBackActivity.this,"c5135f228edfbc0881cd6eede22fc172");
        initVew();
    }

    private void initVew() {
        imageBtn_fan =(ImageButton)this.findViewById(R.id.imageBtn_fan);
        edt_Content =(EditText)this.findViewById(R.id.edt_Content);
        btn_tijiao=(Button)this.findViewById(R.id.btn_tijiao);
        imageBtn_fan.setOnClickListener(this);
        btn_tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imageBtn_fan:
                Intent ibfan = new Intent();
                ibfan.setClass(FeedBackActivity.this,SetActivity.class);
                startActivity(ibfan);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
                break;
            case R.id.btn_tijiao:
                /*
                * 提交意见
                * */
                //1.获取文本框内容
                String content =edt_Content.getText().toString().trim();
                //2.判断文本框是否为空
                if (content.isEmpty())
                {
                    Toast.makeText(FeedBackActivity.this,"反馈意见不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                //3.初始化
                final Content ct =new Content();
                //4.与数据库字符串匹配
                ct.setContent(content);
                /**
                 * 保存数据到Bmob服务器
                 */
                ct.save(FeedBackActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        //5.执行成功
                        Toast.makeText(FeedBackActivity.this,"添加数据成功，返回objectId为:"+ct.getObjectId(),Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        //6.执行失败
                        Toast.makeText(FeedBackActivity.this,"创建数据失败："+s,Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
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
