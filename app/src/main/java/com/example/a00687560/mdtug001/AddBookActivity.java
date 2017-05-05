package com.example.a00687560.mdtug001;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a00687560.model.LibsInfo;
import com.example.a00687560.util.SpUtil;

public class AddBookActivity extends Activity implements View.OnClickListener {

    private EditText etLibId, etLibName, etTypeId;
    private Button btnAdd;
    private Intent intent;
    private int str_lib_id, str_type_id;
    private String str_lib_name;
    private String subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etLibId = (EditText) findViewById(R.id.lib_id);
        etLibName = (EditText) findViewById(R.id.lib_name);
        etTypeId = (EditText) findViewById(R.id.type_id);
        btnAdd = (Button) findViewById(R.id.btn_add_book);
        btnAdd.setOnClickListener(this);
        // 获取传递过来的intent
        intent = getIntent();
    }

    @Override
    public void onClick(View view) {
        // 获取输入框的值要放在添加的时候，不然获取不到！！！
        str_lib_id = etLibId.getId();
        str_lib_name = etLibName.getText().toString().trim();
        str_type_id = etTypeId.getId();
        Log.e("TAG", "str_lib_id===" + str_lib_id + ",str_lib_name===" +
                str_lib_name + ",str_type_id==" + str_type_id);

        LibsInfo libsInfo = new LibsInfo();
        libsInfo.setId(str_lib_id);
        libsInfo.setLib_name(str_lib_name);
        libsInfo.setType_id(str_type_id);
        boolean ifSaveSuccess = libsInfo.save();
        if (ifSaveSuccess) {
            Toast.makeText(AddBookActivity.this, "存储成功！", Toast.LENGTH_SHORT).show();
            // 同时将用户每次收藏的图书的类型保存起来
            subscriber = SpUtil.getInstace().getString("subscriber", "");
            subscriber += str_type_id;
            SpUtil.getInstace().save("subscriber", subscriber);
        } else {
            Toast.makeText(AddBookActivity.this, "存储失败！", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK, intent);
        finish();
        // 此处不需要跳转到之前页面，结束当前页面即可将之前页面暴露出来，并回调onActivityResult()方法
//        startActivity(new Intent(AddBookActivity.this, MyBookActivity.class));
    }
}


