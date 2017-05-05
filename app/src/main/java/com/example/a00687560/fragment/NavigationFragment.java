package com.example.a00687560.fragment;

import android.util.Log;
import android.view.View;

import com.example.a00687560.base.BaseFragment;
import com.example.a00687560.mdtug001.R;

/**
 * Created by xpf on 2017/05/01 :)
 * Function:图书导航页面的Fragment
 */

public class NavigationFragment extends BaseFragment {

    @Override
    public View initView() {
        Log.e("TAG", "图书导航页面的视图初始化了");
        View view = View.inflate(mContext, R.layout.fragment_navigation, null);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "图书导航页面的数据初始化了");
    }

}
