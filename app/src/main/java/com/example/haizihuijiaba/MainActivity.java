package com.example.haizihuijiaba;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.haizihuijiaba.Add.AddActivity;
import com.example.haizihuijiaba.Class.Lost;
import com.example.haizihuijiaba.Class.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.listener.FindListener;


public class MainActivity extends FragmentActivity{

    private RelativeLayout rl_home,rl_news,rl_me;
    private TextView tv_add,mTv=null;
    private ListView listView;
    private ImageView iv_home,iv_news,iv_me,image1;
    private TextView tv_home,tv_news,tv_me;
    //简单适配器 SimpleAdapter
    private SimpleAdapter adapter;
    private int status = 0;
    public LocationClient mLocationClient = null;//初始化LocationClient类
    public MyLocationListener myListener = new MyLocationListener();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化 Bmob SDK，第一个参数为上下文，第二个参数为Application ID
        Bmob.initialize(MainActivity.this,"c5135f228edfbc0881cd6eede22fc172");
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        mLocationClient = new LocationClient(getApplicationContext());  //声明LocationClient类
        mLocationClient.registerLocationListener(myListener); //注册监听函数
        //初始化事件
        rl_home =(RelativeLayout)this.findViewById(R.id.rl_home);
        rl_news =(RelativeLayout)this.findViewById(R.id.rl_news);
        rl_me   =(RelativeLayout)this.findViewById(R.id.rl_me);
        tv_add =(TextView)this.findViewById(R.id.tv_add);
        listView = (ListView)this.findViewById(R.id.listView);
        iv_home = (ImageView)this.findViewById(R.id.iv_home);
        iv_news =(ImageView)this.findViewById(R.id.iv_news);
        iv_me =(ImageView)this.findViewById(R.id.iv_me);
        image1=(ImageView)this.findViewById(R.id.image1);
        tv_home =(TextView)this.findViewById(R.id.tv_home);
        tv_news =(TextView)this.findViewById(R.id.tv_news);
        tv_me =(TextView)this.findViewById(R.id.tv_me);
        mTv = (TextView) findViewById(R.id.mTv);//初始化文本
        setLocationOption();//定义setLocationOption()方法
        mLocationClient.start();//执行定位

        //添加监听事件
        rl_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    //0代表点击时候，1代表没有点击时候
                    case 0:
                      // rl_home.setBackgroundResource(R.drawable.activity_yanse);
                        tv_home.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent home = new Intent();
                home.setClass(MainActivity.this,MainActivity.class);
                startActivity(home);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                      //  rl_news.setBackgroundResource(R.drawable.activity_yanse);
                        tv_news.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent news = new Intent();
                news.setClass(MainActivity.this,NewsActivity.class);
                startActivity(news);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });
        rl_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (status){
                    case 0:
                       // rl_me.setBackgroundResource(R.drawable.activity_yanse);
                        tv_me.setTextColor(Color.YELLOW);
                        status=1;
                        break;
                    case 1:
                        status=0;
                        break;
                }
                Intent me = new Intent();
                me.setClass(MainActivity.this, MeActivity.class);
                startActivity(me);
                // 由左向右滑入的效果
               // overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent add = new Intent();
                add.setClass(MainActivity.this, AddActivity.class);
                startActivity(add);
                //实现淡入淡出的效果
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                finish();
            }
        });

        final BmobQuery<Lost> lost = new BmobQuery<Lost>();
        final List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        lost.order("-createdAt");
        lost.findObjects(MainActivity.this, new FindListener<Lost>() {
            @Override
            public void onSuccess(List<Lost> list) {
                for (Lost lost:list)
                {
                    HashMap<String, Object> item = new HashMap<String, Object>();
                    item.put("text_ti",lost.getCity());
                    BmobFile icon =lost.getImage();
                    icon.loadImage(MainActivity.this,image1);
                    item.put("image_tu",icon);
                    item.put("text_ben",lost.getMessage());
                    item.put("text_time",lost.getCreatedAt());
                    item.put("btn_lei",lost.getClue());
                    item.put("text_lu",lost.getJourney());
                    data.add(item);
                   //实现列表的显示
                    listView.setAdapter(adapter);

                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });

        //获取到集合数据
        //注意SimpleAdapter适配器用法
       // data = MyDataSource.getData();
         adapter = new SimpleAdapter(getApplicationContext(),data,R.layout.activity_item,
                new String[]{"image_tu","text_ti","text_ben","text_time","btn_lei","text_lu","text_xian"},
                new int[]{R.id.image_tu,R.id.text_ti,R.id.text_ben,R.id.text_time,R.id.btn_lei,R.id.text_lu,R.id.text_xian});



    }
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //将获取的City赋值给txt
            /**
             *1.国家:location.getCountry()
             * 2.城市:location.getCity()
             * 3.区域(例：天河区)：location.getDistrict()
             * 4.地点(例：风信路)：location.getStreet()
             * 5.详细地址：location.getAddrStr()
             */
            mTv.setText(location.getCity()+location.getDistrict());
            Toast.makeText(MainActivity.this,"网络定位成功"+location.getDirection(),Toast.LENGTH_LONG).show();
        }

        public void onReceivePoi(BDLocation arg0) {
        }
    }
    //执行onDestroy()方法，停止定位
    @Override
    public void onDestroy() {
        mLocationClient.stop();
        super.onDestroy();
    }

    //设置相关参数
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); //打开gps
        option.setAddrType("all");//返回定位结果包含地址信息
        option.setPriority(LocationClientOption.NetWorkFirst); // 设置网络优先
        option.setPriority(LocationClientOption.GpsFirst);       //gps
        option.disableCache(true);//禁止启用缓存定位
        mLocationClient.setLocOption(option);
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

