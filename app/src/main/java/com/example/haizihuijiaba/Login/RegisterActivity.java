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
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.VerifySMSCodeListener;


/**
 * Created by 伟捷。 on 2015/11/23.
 */
public class RegisterActivity extends FragmentActivity implements View.OnClickListener {
    private ImageButton imageBtn_fanhui;
    private Button btn_register, btn_AuthCode;
    private EditText edt_Name, edt_Pwd, edt_Pwd2, edt_Number, edt_AuthCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(this, "c5135f228edfbc0881cd6eede22fc172");
        initView();
    }

    private void initView() {
        edt_Name = (EditText) this.findViewById(R.id.edt_Name);
        edt_Pwd = (EditText) this.findViewById(R.id.edt_Pwd);
        edt_Pwd2 = (EditText) this.findViewById(R.id.edt_Pwd2);
        edt_Number = (EditText) this.findViewById(R.id.edt_Number);
        edt_AuthCode = (EditText) this.findViewById(R.id.edt_AuthCode);
        imageBtn_fanhui = (ImageButton) this.findViewById(R.id.imageBtn_fanhui);
        btn_register = (Button) this.findViewById(R.id.btn_register);
        btn_AuthCode = (Button) this.findViewById(R.id.btn_AuthCode);
        imageBtn_fanhui.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        btn_AuthCode.setOnClickListener(this);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageBtn_fanhui:
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.btn_AuthCode:
                //1.获取手机号码
                String Number = edt_Number.getText().toString().trim();
                //2.与服务器对接
                BmobSMS.requestSMSCode(RegisterActivity.this, Number, "Jack", new RequestSMSCodeListener() {
                    @Override
                    public void done(Integer integer, BmobException e) {//integer用于查询本次短信发送详情
                        if (e == null)//验证码发送成功
                        {
                            Toast.makeText(RegisterActivity.this, "请查收验证码", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                new TimeCount(60 * 1000, 1000).start();
               break;
            case R.id.btn_register:
                //1.获取文本框内容
                String Name = edt_Name.getText().toString().trim();
                String Pwd = edt_Pwd.getText().toString().trim();
                String Pwd2 = edt_Pwd2.getText().toString().trim();
                String number = edt_Number.getText().toString().trim();
                String AuthCode = edt_AuthCode.getText().toString().trim();
                //2.判断文本框是否为空
                if (Name.isEmpty() || Pwd.isEmpty() || Pwd2.isEmpty() || number.isEmpty() || AuthCode.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "重要信息不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                //3.验证手机号码
                BmobSMS.verifySmsCode(RegisterActivity.this, number, AuthCode, new VerifySMSCodeListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) //短信验证码已验证成功
                        {
                            Toast.makeText(RegisterActivity.this, "验证成功", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "验证失败：code=" + e.getErrorCode() + ",msg=" + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

                final User user = new User();
                user.setUserName(Name);
                user.setUserPwd(Pwd);
                user.setUserPwd2(Pwd2);
                user.setNumber(number);
                /**
                 * 保存数据到Bmob服务器
                 */
                user.save(RegisterActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterActivity.this, "添加数据成功，返回objectId为：" + user.getObjectId(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RegisterActivity.this, "创建数据失败：" + s, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }

    /**
     * 倒计时
     */
    private class TimeCount extends CountDownTimer {
        public TimeCount(long i, long l) {
            super(i, l);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_AuthCode.setClickable(false);
            btn_AuthCode.setText(millisUntilFinished / 1000 + "秒后可重发");
        }

        @Override
        public void onFinish() {
            btn_AuthCode.setText("重新获取");
            btn_AuthCode.setClickable(true);

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
