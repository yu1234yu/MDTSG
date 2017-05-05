package com.example.a00687560.mdtug001;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a00687560.adapter.LibsCollectionAdapter;
import com.example.a00687560.model.LibsCollection;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;


public class MyCollectionActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView libsColections;
    private LibsCollectionAdapter adapter;
    private Button btnAdd;
    private List<LibsCollection> libsCollectionList;
    private SQLiteDatabase db;
    private List<List<String>> mList = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_collection_toolbar);
        setSupportActionBar(toolbar);
        //后台设置ToolBar的图标，传递给对象toolbar
        toolbar.setNavigationIcon(R.drawable.btn_back);
        //让原始toolBar的title不显示。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MyCollectionActivity.this, HomeActivity.class));
            }
        });


        libsColections = (ListView) findViewById(R.id.collection_list);
        btnAdd = (Button) findViewById(R.id.btn_add_collection);
        btnAdd.setOnClickListener(this);

        // 获取全部收藏信息
        getAllLibsCollection();
    }

    public void onClick(View view) {
        Intent intent = new Intent(MyCollectionActivity.this, AddCollectionActivity.class);
        intent.putExtra("request", "Add");
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
    }

    private void getAllLibsCollection() {
        List<LibsCollection> all = DataSupport.findAll(LibsCollection.class);
        if (all != null && all.size() > 0) {
            libsCollectionList = all;
//            displayDataFromDB();
            // 养成每次都要判null的好习惯，避免空指针异常！！！
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            } else {
                adapter = new LibsCollectionAdapter(MyCollectionActivity.this, libsCollectionList);
                libsColections.setAdapter(adapter);
            }
        } else {
            Toast.makeText(this, "没找到收藏内容", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDataFromDB() {
        mList.clear();
        List<String> columnList = new ArrayList<String>();
        columnList.add("book_name");
        mList.add(columnList);
        if (libsCollectionList != null && libsCollectionList.size() > 0) {
            for (int i = 0; i < libsCollectionList.size(); i++) {
                LibsCollection offer = libsCollectionList.get(i);
                List<String> stringList = new ArrayList<String>();
                stringList.add(String.valueOf(offer.getId()));
                stringList.add(offer.getLib_name());
                mList.add(stringList);
            }
            adapter = new LibsCollectionAdapter(MyCollectionActivity.this, libsCollectionList);
            libsColections.setAdapter(adapter);
        }
    }

}

