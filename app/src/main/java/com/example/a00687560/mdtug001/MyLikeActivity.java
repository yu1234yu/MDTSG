package com.example.a00687560.mdtug001;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00687560.util.SpUtil;
import com.example.a00687560.util.StringConvert;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.Set;

public class MyLikeActivity extends AppCompatActivity {
    private TagFlowLayout tagFlowLayout;
    private LayoutInflater mInflater;
    private Context mContext;
    private String[] mVals = {"文学", "政治", "语言", "社会科学总论", "天文学", "艺术", "生物科学", "工业技术", "航空", "地球科学", "哲学", "医药"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_like);
        mContext = this;

        mInflater = LayoutInflater.from(mContext);

        //返回上级界面
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_like_toolbar);
        tagFlowLayout = (TagFlowLayout) findViewById(R.id.flow_layout);

        setSupportActionBar(toolbar);
        //后台设置ToolBar的图标，传递给对象toolbar
        toolbar.setNavigationIcon(R.drawable.btn_back);
        //让原始toolBar的title不显示。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startActivity(new Intent(MyLikeActivity.this, HomeActivity.class));
            }
        });

        TagAdapter<String> tagAdapter = new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        // 设置适配器
        tagFlowLayout.setAdapter(tagAdapter);

        // 将之前选中的标签选中(即保存选中的标签)
        String hobby = SpUtil.getInstace().getString("hobby", "");
        Log.e("TAG", "hobby===" + hobby);
        Set<Integer> set = StringConvert.StringToSet(hobby);
        tagAdapter.setSelectedList(set);

        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(MyLikeActivity.this, mVals[position], Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        tagFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                MyLikeActivity activity = (MyLikeActivity) mContext;
                activity.setTitle("choose:" + selectPosSet.toString());
                Log.e("TAG", "choose===" + selectPosSet.toString());
                // 将用户选中好的爱好的类型保存起来
                SpUtil.getInstace().save("hobby", selectPosSet.toString());
            }
        });
    }


}


