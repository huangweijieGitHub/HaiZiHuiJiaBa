package com.example.haizihuijiaba.Class;

import android.widget.TextView;

import cn.bmob.v3.BmobObject;

/**
 * Created by 伟捷。 on 2016/3/29.
 */
public class User extends BmobObject {
    private String UserName;
    private String UserPwd;
    private String UserPwd2;
    private String Number;

    public String getUserName(String username) {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPwd() {
        return UserPwd;
    }

    public void setUserPwd(String userPwd) {
        UserPwd = userPwd;
    }

    public String getUserPwd2() {
        return UserPwd2;
    }

    public void setUserPwd2(String userPwd2) {
        UserPwd2 = userPwd2;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    @Override
    public String toString() {
        return "User[ UserName"+UserName+",UserPwd"+UserPwd+"]";
    }

}
