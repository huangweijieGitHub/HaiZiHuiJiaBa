package com.example.haizihuijiaba.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.Class.User;
import com.example.haizihuijiaba.MainActivity;
import com.example.haizihuijiaba.R;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;


/**
 * Created by 伟捷。 on 2015/11/21.
 */
public class LoginActivity extends FragmentActivity implements View.OnClickListener {
    private EditText edtTxt_Id, edtTxt_Psd;
    private Button btn_Login;
    private TextView txt_Forget, txt_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(LoginActivity.this, "c5135f228edfbc0881cd6eede22fc172");
        initVew();
    }

    private void initVew() {
        edtTxt_Id = (EditText) this.findViewById(R.id.edtTxt_Id);
        edtTxt_Psd = (EditText) this.findViewById(R.id.edtTxt_Psd);
        btn_Login = (Button) this.findViewById(R.id.btn_Login);
        txt_Forget = (TextView) this.findViewById(R.id.txt_Forget);
        txt_Register = (TextView) this.findViewById(R.id.txt_Register);
        btn_Login.setOnClickListener(this);
        txt_Forget.setOnClickListener(this);
        txt_Register.setOnClickListener(this);
    }

    //为按钮添加监听事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Login:
                login();
                break;
            case R.id.txt_Forget:
                Intent intent2 = new Intent();
                intent2.setClass(LoginActivity.this, ForgetActivity.class);
                startActivity(intent2);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            case R.id.txt_Register:
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent1);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            default:
                break;

        }

    }

    private void login() {
        //1.获取文本框的内容
        String UserName = edtTxt_Id.getText().toString().trim();
        String UserPwd = edtTxt_Psd.getText().toString().trim();
        //2.判断Id和Pwd是否为空
        if (UserName.isEmpty() || UserPwd.isEmpty()) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空", Toast.LENGTH_LONG).show();
            return;

        }
        //3.new查询方法
        final BmobQuery<User> userQuery = new BmobQuery<User>();
        //4.查询条件
        userQuery.addWhereEqualTo("UserName", UserName);
        userQuery.addWhereEqualTo("UserPwd", UserPwd);
        //5.执行查询内容
        userQuery.findObjects(LoginActivity.this, new FindListener<User>() {
            @Override
            public void onSuccess(List<User> list) {
                //6.成功执行
                if (list != null && list.size() > 0) {
                    Intent intent3 = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent3);
                    //实现淡入淡出的效果
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登陆失败", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(int i, String s) {
                //7.失败执行
                Toast.makeText(LoginActivity.this, "登录失败" + i + "," + s, Toast.LENGTH_LONG).show();

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
