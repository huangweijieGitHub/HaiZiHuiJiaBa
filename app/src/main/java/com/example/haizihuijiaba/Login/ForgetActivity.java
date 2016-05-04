package com.example.haizihuijiaba.Login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.Class.User;
import com.example.haizihuijiaba.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.RequestSMSCodeListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;


/**
 * Created by 伟捷。 on 2015/11/23.
 */
public class ForgetActivity extends FragmentActivity implements View.OnClickListener {
    private ImageButton imageBtn_rr;
    private Button btn_ForgetPwd,btn_AuthCode1;
    private EditText edt_ID,edt_AlterPwd,edt_AuthCode1,edt_Number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(ForgetActivity.this, "c5135f228edfbc0881cd6eede22fc172");
        initView();
    }

    private void initView() {
        edt_ID=(EditText)this.findViewById(R.id.edt_ID);
        edt_AlterPwd=(EditText)this.findViewById(R.id.edt_AlterPwd);
        edt_Number1=(EditText)this.findViewById(R.id.edt_Number1);
        edt_AuthCode1=(EditText)this.findViewById(R.id.edt_AuthCode1);
        imageBtn_rr = (ImageButton) this.findViewById(R.id.imageBtn_rr);
        btn_AuthCode1 = (Button) this.findViewById(R.id.btn_AuthCode1);
        btn_ForgetPwd=(Button)this.findViewById(R.id.btn_ForgetPwd);
        imageBtn_rr.setOnClickListener(this);
        btn_AuthCode1.setOnClickListener(this);
        btn_ForgetPwd.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBtn_rr:
                Intent intent = new Intent();
                intent.setClass(ForgetActivity.this, LoginActivity.class);
                startActivity(intent);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.btn_AuthCode1:
                //1.获取手机号码
                String Number1=edt_Number1.getText().toString().trim();

                //2.与服务器信息匹配
                BmobSMS.requestSMSCode(ForgetActivity.this, Number1, "Jack", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {
                        if (e==null)//验证码发送成功
                        {
                            Toast.makeText(ForgetActivity.this,"请查收验证码",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //3.倒计时
                new TimeCount(60 * 1000, 1000).start();
                break;
            case R.id.btn_ForgetPwd:
                //1.获取文本框内容
                String UserName =edt_ID.getText().toString().trim();
                String UserPwd =edt_AlterPwd.getText().toString().trim();
                String number1=edt_Number1.getText().toString().trim();
                String AuthCode1=edt_AuthCode1.getText().toString().trim();
                //2.判断文本空是否为空
                if (UserName.isEmpty()||UserPwd.isEmpty()||number1.isEmpty()||AuthCode1.isEmpty()){
                    Toast.makeText(ForgetActivity.this,"信息不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                //3.验证手机号
                BmobSMS.verifySmsCode(ForgetActivity.this, number1, AuthCode1, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e==null)//短信验证码已验证成功
                        {
                            Toast.makeText(ForgetActivity.this,"验证成功"+e.getErrorCode()+",msg="+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(ForgetActivity.this,"验证失败：Code="+e.getErrorCode()+",msg="+e.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //4.初始化Bmob
                final User user = new User();
                //4.修改内容
                user.setValue("UserPwd",UserPwd);
                //5.执行
                user.update(ForgetActivity.this,UserName, new UpdateListener() {
                    @Override
                    public void onSuccess() {
                        //6.修改成功
                        Toast.makeText(ForgetActivity.this,"修改密码成功",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        //修改失败
                     Toast.makeText(ForgetActivity.this,"修改密码失败:"+s,Toast.LENGTH_LONG).show();
                    }
                });
                 break;
            default:
                break;
        }
    }

    private class TimeCount extends CountDownTimer {
        public TimeCount(long open, long down) {
            super(open, down);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_AuthCode1.setClickable(false);
            btn_AuthCode1.setText(millisUntilFinished / 1000 + "秒后可重发");
        }

        @Override
        public void onFinish() {
            btn_AuthCode1.setText("重新获取");
            btn_AuthCode1.setClickable(true);
        }
    }

    /**
     * 连续按两次返回键就退出
     */
    private static long firstTime;
    @Override
    public void onBackPressed() {
        if (firstTime+2000>System.currentTimeMillis()){
            System.exit(0);
            super.onBackPressed();
        }
        else
        {
            ToastUtils.toast(this,"再按一次退出程序");
        }
        firstTime=System.currentTimeMillis();

    }
}
