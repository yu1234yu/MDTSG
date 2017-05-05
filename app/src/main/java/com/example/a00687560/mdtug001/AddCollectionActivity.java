package com.example.a00687560.mdtug001;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.util.SpUtil;

public class AddCollectionActivity extends Activity implements View.OnClickListener {

    private EditText etLibName, etTypeId, etLibId;
    private Button btnAdd;
    private Intent intent;
    private int str_lib_id, str_type_id;
    private String str_lib_name;
    private String collectionString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_collection);

        etLibName = (EditText) findViewById(R.id.lib_name);
        etLibId = (EditText) findViewById(R.id.lib_id);
        etTypeId = (EditText) findViewById(R.id.type_id);
        btnAdd = (Button) findViewById(R.id.btn_add_collection);
        btnAdd.setOnClickListener(this);

        //获取传递过来的intent
        intent = getIntent();
    }


    public void onClick(View view) {
        // 获取输入框的值
        str_lib_id = etLibId.getId();
        str_lib_name = etLibName.getText().toString();
        str_type_id = etTypeId.getId();

        LibsCollection libsInfo = new LibsCollection();
        libsInfo.setId(str_lib_id);
        libsInfo.setLib_name(str_lib_name);
        libsInfo.setType_id(str_type_id);
        boolean ifSaveSuccess = libsInfo.save();
        if (ifSaveSuccess) {
            Toast.makeText(AddCollectionActivity.this, "存储成功！", Toast.LENGTH_SHORT).show();
            // 同时将用户每次收藏的图书的类型保存起来
            collectionString = SpUtil.getInstace().getString("collection", "");
            collectionString += str_type_id;
            SpUtil.getInstace().save("collection", collectionString);
        } else {
            Toast.makeText(AddCollectionActivity.this, "存储失败！", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK, intent);
        finish();
//        startActivity(new Intent(AddCollectionActivity.this, MyCollectionActivity.class));
    }
}


