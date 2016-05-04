package com.example.haizihuijiaba.Mes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.haizihuijiaba.MeActivity;
import com.example.haizihuijiaba.R;

/**
 * Created by 伟捷。 on 2016/4/26.
 */
public class CompileActivity extends Activity {
    private ImageButton imageBtn_fanhui1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile);
        imageBtn_fanhui1=(ImageButton)this.findViewById(R.id.imageBtn_fanhui1);
        imageBtn_fanhui1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompileActivity.this, MeActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
    }
}
