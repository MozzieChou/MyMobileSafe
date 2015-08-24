package com.example.mymobilesafe.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.mymobilesafe.R;
import com.example.mymobilesafe.adapter.MainAdapter;
import com.example.mymobilesafe.utils.AssetCopyUtil;

import java.io.File;

/**
 * Created by Mozzie on 2015/8/21.
 * 程序主界面，显示九大模块
 */
public class MainActivity extends Activity {
    private GridView gv_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Thread() {
            public void run() {
                File file = new File(getFilesDir(), "antivirus.db");
                if (file.exists() && file.length() > 0) {//数据库文件已经拷贝成功

                } else {
                    AssetCopyUtil.copy1(getApplicationContext(),
                            "antivirus.db", file.getAbsolutePath(), null);
                }
            };

        }.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        gv_main = (GridView) findViewById(R.id.gv_main);
        //为gv_main对象设置一个适配器，该适配器的作用是用于为每个item填充对应的数据
        gv_main.setAdapter(new MainAdapter(this));
        //为GridView对象中的item设置点击时的监听事件
        gv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //参数一：item的父控件，也就是GridView 参数二：当前点击的item 参数三：当前点击的item在GridView中的位置
            //参数四：id的值为点击了GridView的哪一项对应的数值，点击了GridView第9项，那id就等于8
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0: //手机防盗
                        //跳转到”手机防盗“对应的Activity界面
                        Intent lostprotectedIntent = new Intent(MainActivity.this, LostProtectedActivity.class);
                        startActivity(lostprotectedIntent);
                        break;
                    case 1: //通讯卫士
                        Intent callSmsIntent = new Intent(MainActivity.this, CallSmsSafeActivity.class);
                        startActivity(callSmsIntent);
                        break;
                    case 2: //程序管理
                        Intent appManagerIntent = new Intent(MainActivity.this, AppManagerActivity.class);
                        startActivity(appManagerIntent);
                    case 3: //进程管理
                        Intent taskManagerIntent = new Intent(MainActivity.this, TaskManagerActivity.class);
                        startActivity(taskManagerIntent);
                        break;
                    case 4: //流量管理
                        Intent trafficInfoIntent = new Intent(MainActivity.this, TrafficInfoActivity.class);
                        startActivity(trafficInfoIntent);
                        break;
                    case 5: //手机杀毒
                        Intent antiVirusIntent = new Intent(MainActivity.this, AntiVirusActivity.class);
                        startActivity(antiVirusIntent);
                        break;
                    case 6: //系统优化
                        Intent cleanCacheIntent = new Intent(MainActivity.this, CleanCacheActivity.class);
                        startActivity(cleanCacheIntent);
                        break;
                    case 7://高级工具
                        Intent atoolsIntent = new Intent(MainActivity.this, AtoolsActivity.class);
                        startActivity(atoolsIntent);
                        break;
                    case 8://设置中心
                        //跳转到”设置中心“对应的Activity界面
                        Intent settingIntent = new Intent(MainActivity.this, SettingCenterActivity.class);
                        startActivity(settingIntent);
                        break;
                }
            }
        });
    }
}
