package com.example.haizihuijiaba.Class;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by 伟捷。 on 2016/4/6.
 */
public class Lost extends BmobObject {
    private String City;
    private String Message;
    private String Clue;
    private String Journey;
    private BmobFile Image;

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getClue() {
        return Clue;
    }

    public void setClue(String clue) {
        Clue = clue;
    }

    public String getJourney() {
        return Journey;
    }

    public void setJourney(String journey) {
        Journey = journey;
    }

    public BmobFile getImage() {
        return Image;
    }

    public void setImage(BmobFile image) {
        Image = image;
    }
}
