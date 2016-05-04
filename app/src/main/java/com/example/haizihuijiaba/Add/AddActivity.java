package com.example.haizihuijiaba.Add;


import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.haizihuijiaba.Class.Lost;
import com.example.haizihuijiaba.Class.ToastUtils;
import com.example.haizihuijiaba.MainActivity;
import com.example.haizihuijiaba.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.SaveListener;


/**
 * Created by 伟捷。 on 2015/12/28.
 */
public class AddActivity extends FragmentActivity {
    private ImageButton fanhui;
    private Spinner spinner1, spinner2, spinner3;
    private List<String> data_list1, data_list2;
    private ArrayAdapter<String> arr_adapter1, arr_adapter2;
    private Button btn_tupian, btn_refer;
    private EditText edt_journey, edt_message;
    private ImageView image_tupian;
    //定义城市数据，用于二级菜单
    private String[][] arrCity = new String[][]{
            {"天河区", "越秀区", "番禺区", "荔湾区"},
            {"江城区", "阳西区", "阳东区", "阳春区"},
            {"台江区", "仓山区", "晋安区", "马尾区"},
            {"逢江区", "江海区", "新会区", "鹤山区"}
    };
    private ArrayAdapter<CharSequence> adapterCity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(AddActivity.this, "c5135f228edfbc0881cd6eede22fc172");
        //初始化控件
        fanhui = (ImageButton) this.findViewById(R.id.fanhui);
        spinner1 = (Spinner) this.findViewById(R.id.spinner1);
        spinner2 = (Spinner) this.findViewById(R.id.spinner2);
        spinner3 = (Spinner) this.findViewById(R.id.spinner3);
        edt_message = (EditText) this.findViewById(R.id.edt_message);
        edt_journey = (EditText) this.findViewById(R.id.edt_journey);
        btn_tupian = (Button) this.findViewById(R.id.btn_tupian);
        btn_refer = (Button) this.findViewById(R.id.btn_refer);
        image_tupian =(ImageView)this.findViewById(R.id.image_tupian);
        //添加监听事件
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fanhui1 = new Intent();
                fanhui1.setClass(AddActivity.this, MainActivity.class);
                startActivity(fanhui1);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });

        //数据
        data_list1 = new ArrayList<String>();
        data_list1.add("失踪");
        data_list1.add("线索");
        //适配器
        arr_adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list1);
        //设置样式
        arr_adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner1.setAdapter(arr_adapter1);
        //数据
        data_list2 = new ArrayList<String>();
        data_list2.add("广州市");
        data_list2.add("阳江市");
        data_list2.add("福州市");
        data_list2.add("江门市");
        //适配器
        arr_adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list2);
        //设置样式
        arr_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner2.setAdapter(arr_adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //使用ArrayAdapter转换数据
                AddActivity.this.adapterCity = new ArrayAdapter<CharSequence>(
                        AddActivity.this,
                        android.R.layout.simple_spinner_item,
                        AddActivity.this.arrCity[position]);
                //使用adapterCity数据适配器填充spinCity
                AddActivity.this.spinner3.setAdapter(AddActivity.this.adapterCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //提交
        btn_refer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1.获取参数
                String message = edt_message.getText().toString().trim();
                String journey = edt_journey.getText().toString().trim() + "Km";
                String city = spinner2.getSelectedItem().toString() + "," + spinner3.getSelectedItem().toString().trim();
                String clue = spinner1.getSelectedItem().toString().trim();

                BmobFile file =new BmobFile(new File(String.valueOf(image_tupian)));
                //2.调出方法
                final Lost lost = new Lost();
                lost.setCity(city);
                lost.setClue(clue);
                lost.setJourney(journey);
                lost.setMessage(message);
                //lost.setImage(file);

             /*
             * 保存数据到Bmob中
             * */
                lost.save(AddActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        ToastUtils.toast(AddActivity.this, "添加数据成功");

                    }

                    @Override
                    public void onFailure(int i, String s) {
                        ToastUtils.toast(AddActivity.this, "添加数据失败:" + s);
                    }
                });
            }
        });
         /*打开系统相册*/
        btn_tupian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                /* 开启Pictures画面Type设定为image */
                intent.setType("image/*");
                 /* 使用Intent.ACTION_GET_CONTENT这个Action */
                intent.setAction(Intent.ACTION_GET_CONTENT);
                 /* 取得相片后返回本画面 */
                startActivityForResult(intent, 1);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri uri = data.getData();
            Log.e("uri", uri.toString());
            ContentResolver cr = this.getContentResolver();
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                /* 将Bitmap设定到ImageView */
                image_tupian.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                Log.e("Exception", e.getMessage(),e);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
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
