package com.example.a00687560.mdtug001;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.a00687560.adapter.MyAdapter;
import com.example.a00687560.model.LibsInfo;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class MyBookActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private MyAdapter Adapter;
    private Button btnAdd;
    private Context mContext;
    private List<LibsInfo> LibsInFoList;
    private List<List<String>> mList = new ArrayList<List<String>>();
    private List<LibsInfo> all = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_my_book);

        // 返回上级界面
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_book_toolbar);
        setSupportActionBar(toolbar);

        // 后台设置ToolBar的图标，传递给对象toolbar
        toolbar.setNavigationIcon(R.drawable.btn_back);
        // 让原始toolBar的title不显示。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MyBookActivity.this, HomeActivity.class));
                finish(); // 结束当前页面，避免创建多个Activity实例
            }
        });

        btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);

        // 设置RcycleView属性
        mRecyclerView = (RecyclerView) findViewById(R.id.my_book_recycler_view);
        // 以下，如果可以确定每个Item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //以下，创建默认的线性LayoutMan5ager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        Adapter = new MyAdapter(mContext, all);
//        mRecyclerView.setAdapter(Adapter);

        getAllBook();//获取全部预定图书；
    }

    /**
     * 获取全部预定图书 方法
     */
    private void getAllBook() {
        all = DataSupport.findAll(LibsInfo.class);
        Log.e("TAG", "all===" + all.size());
        if (all != null && all.size() > 0) {
            LibsInFoList = all;
//            populateDataFromDB();
            // Adapter此处可以能为null所以先判空
            if (Adapter != null) {
                Adapter.notifyDataSetChanged();
            } else {
                Adapter = new MyAdapter(mContext, all);
                mRecyclerView.setAdapter(Adapter);
            }

        } else {
            showToast();
        }
    }

    private void showToast() {
        Toast toast = new Toast(this);
        ImageView img = new ImageView(this);
        img.setImageResource(R.drawable.img_book_hint);
        toast.setView(img);
        toast.show();
    }

    private void populateDataFromDB() {
        mList.clear();
        List<String> columnList = new ArrayList<String>();
        columnList.add("name");
        mList.add(columnList);
        if (LibsInFoList != null && LibsInFoList.size() > 0) {
            Log.e("TAG", "LibsInFoList.size()===" + LibsInFoList.size());
            for (int i = 0; i < LibsInFoList.size(); i++) {
                LibsInfo user = LibsInFoList.get(i);
                List<String> stringList = new ArrayList<String>();
                stringList.add(String.valueOf(user.getId()));
                stringList.add(user.getLib_name());
                mList.add(stringList);
            }
            Adapter = new MyAdapter(mContext, LibsInFoList);
            mRecyclerView.setAdapter(Adapter);
        }
    }

    //点击 添加 按钮监听
    public void onClick(View v) {
        // 普通存储
        Intent i = new Intent(MyBookActivity.this, AddBookActivity.class);
        startActivityForResult(i, 1);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        //根据返回的resultCode判断是通过哪种操作返回的，并提示相关信息；
        switch (requestCode) {
            case 0:
                if (resultCode == 1)
                    Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                if (resultCode == 2)
                    Toast.makeText(this, "已删除", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                if (resultCode == RESULT_OK)
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                break;
        }

        /**
         * 先判空，再设置适配器，避免出现空指针异常
         */
        // 此处为重复的操作！！！因为getAllBook()里有设置适配器
//        if (LibsInFoList != null && LibsInFoList.size() > 0) {
//            Adapter = new MyAdapter(mContext, LibsInFoList);
//            mRecyclerView.setAdapter(Adapter);
//        }
        getAllBook();
    }


}
