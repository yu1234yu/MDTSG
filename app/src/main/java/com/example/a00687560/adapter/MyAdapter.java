package com.example.a00687560.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsInfo;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<LibsInfo> libsInfoList;
    private Context context;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<LibsInfo> libsInfoList) {
        this.libsInfoList = libsInfoList;
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new VH(View.inflate(context, R.layout.item_recyclerview, null));
        // 此处加载的布局文件要和下面ViewHolder中控件对应起来，不然，下面的TextView中会报空
        // 此方法中低层还是调用的LayoutInflater方法,二第三个参数为null时，即没有指定相应的rootView，所以其显示
        // 不会充满整个手机屏幕
        return new VH(mInflater.inflate(R.layout.list_view_offer_me_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String lib_name = libsInfoList.get(position).getLib_name();
        int id = libsInfoList.get(position).getId();
        int type_id = libsInfoList.get(position).getType_id();
        Log.e("TAG", "lib_name===" + lib_name);
        VH vHolder = (VH) holder;
        if (!TextUtils.isEmpty(lib_name)) {
            vHolder.mTextView.setText(lib_name);
        }
        vHolder.lib_author.setText("" + id);
        vHolder.lib_ISBN.setText("" + type_id);
    }

    @Override
    public int getItemCount() {
        return libsInfoList.size();
    }

    class VH extends RecyclerView.ViewHolder {
        TextView mTextView;
        TextView lib_author;
        TextView lib_ISBN;

        VH(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.lib_name);
            lib_author = (TextView) itemView.findViewById(R.id.lib_author);
            lib_ISBN = (TextView) itemView.findViewById(R.id.lib_ISBN);
        }
    }
}