package com.example.mymobilesafe.adapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mymobilesafe.R;

public class MainAdapter extends BaseAdapter {
    // 布局填充器
    private LayoutInflater inflater;
    // 接收MainActivity传递过来的上下文对象
    private Context context = null;
    //用于替换”手机防盗“的新标题
    private String newname;
    private SharedPreferences sp;
    private static final int[] icons = { R.drawable.widget1,
            R.drawable.widget2, R.drawable.widget3, R.drawable.widget4,
            R.drawable.widget5, R.drawable.widget6, R.drawable.widget7,
            R.drawable.widget8, R.drawable.widget9 };
    // 将九个item的每一个标题都存入该数组中
    private static String[] names= {"手机防盗", "通讯卫士", "软件管理", "进程管理",
            "流量统计", "手机杀毒", "系统优化", "高级工具", "设置中心" };

    public MainAdapter(Context context) {
        this.context = context;
        // 获取系统中的布局填充器
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //获取用于替换”手机防盗“的新标题，默认值为空
        sp = context.getSharedPreferences("config",
                Context.MODE_PRIVATE);
        newname = sp.getString("newname", "")

        ;
    }

    /**
     * 返回gridview有多少个item
     */
    public int getCount() {
        return names.length;
    }

    /**
     * 获取每个item对象，如果我们不对这个返回的item对象做相应的操作的话， 我们可以返回一个null， 这里我们简单处理一下，返回position
     */
    public Object getItem(int position) {
        return position;
    }

    /**
     * 返回当前item的id
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * 返回每一个gridview中条目中的view对象
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.main_item, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_main_item_name);
        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_main_item_icon);
        tv_name.setText(names[position]);
        iv_icon.setImageResource(icons[position]);
        //第一个Item，也即“手机防盗”对应的Item
        if (position == 0) {
            //判断sp中取出的newname是否为空，如果不为空的话，将“手机防盗”对应的标题修改为sp中修改后的标题
            if (!TextUtils.isEmpty(newname)) {
                tv_name.setText(newname);
            }
        }

        return view;
    }
}