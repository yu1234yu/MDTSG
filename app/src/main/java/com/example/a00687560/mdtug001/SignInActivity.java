package com.example.a00687560.mdtug001;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a00687560.model.StudentInfo;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;


public class SignInActivity extends Activity {

    //账号和密码
    private EditText user_id;
    private EditText user_password;

    private Button btnsignin;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // 删除navigationBar
        getWindow().
                getDecorView().
                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        //创建数据库
        SQLiteDatabase db= Connector.getDatabase();
        saveUsers();



        /**
         *点击登录按钮
         */
        user_id=(EditText)findViewById(R.id.user_id);
        user_password=(EditText)findViewById(R.id.user_password);
        btnsignin=(Button)findViewById(R.id.btn_sign_in);
        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIdAndPwd();
            }
        });


        /**
         * 点击 忘记密码 文本跳转
         */
        //借助SpannableString类实现超链接文字
        TextView tv_link = (TextView) findViewById(R.id.tv_link);
        tv_link.setText(getClickable());
        //设置超链接可点击
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        //给文本设置监听然后跳转，不传参数跳转:
           //Intent intent =new Intent(this,ForgotPassword1Activity.class);
           //startActivity(intent);
        tv_link.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(SignInActivity.this,ForgotPassword1Activity.class));
            }
        });


    }

    /**
     * 事先存储几个账密进数据库
     */
    private void saveUsers() {
        for (int i =1; i <5; i++) {
            StudentInfo studentInfo = new StudentInfo(i,123456);
            studentInfo.save();
        }
    }

    /**
     * 检查用户名和密码
     */

        public void checkIdAndPwd() {
            String id = user_id.getText().toString().trim();
            String pwd = user_password.getText().toString().trim();
            if (id.equals("") || pwd.equals("")) {
                //弹出消息框
                new AlertDialog.Builder(SignInActivity.this).setTitle("错误")
                        .setMessage("账号或密码不能为空").setPositiveButton("确定", null)
                        .show();
            } else {
                StudentInfo studentInfo = DataSupport.find(StudentInfo.class, Integer.parseInt(id));
                if (studentInfo != null) {
                    int password = studentInfo.getPassword();
                    if (pwd.equals(""+password)) {
                        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
                        finish();//登录成功后销毁页面
                    } else {
                        Toast.makeText(SignInActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(SignInActivity.this, "不存在该用户！", Toast.LENGTH_SHORT).show();
                }
            }
        }



    /**
     * 定义getClickable方法可点击
     * @return
     */
    public SpannableString getClickable() {
        SpannableString spannableString = new SpannableString("忘记密码");
        //设置下划线文字
        spannableString.setSpan(new UnderlineSpan(), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的单击事件
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                //本界面弹框提示“忘记密码”
                //Toast.makeText(SignInActivity.this, "忘记密码", Toast.LENGTH_SHORT).getView();


            }
        }, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置文字的前景色
        spannableString.setSpan(new ForegroundColorSpan(getColor(R.color.colorPrimary)), 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;

    }


    }



