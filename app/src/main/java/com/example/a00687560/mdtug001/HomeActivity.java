package com.example.a00687560.mdtug001;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a00687560.base.BaseFragment;
import com.example.a00687560.fragment.MagazineFragment;
import com.example.a00687560.fragment.NavigationFragment;
import com.example.a00687560.fragment.RecommendFragment;
import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.model.LibsInfo;
import com.example.a00687560.model.LibsOffer;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends FragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridLayout gridLayout;
    private LinearLayout linear_tag;
    private ImageView up;
    private Button menu_6;
    private boolean isButton = true;
    private EditText search_edit_text;
    private RadioGroup rgMain;
    private RadioButton rbRecommend, rbBook, rbMagazine;
    private List<LibsInfo> libsInfoList;
    private List<LibsCollection> libsCollectionList;
    private List<LibsOffer> libsOfferList;


    private FragmentTransaction transaction;
    // 用于显示Fragment的容器
    private FrameLayout flContainer;
    // 用于存储底部3个页面的Fragment的集合
    private List<BaseFragment> fragments;
    private int currentPosition = 0; // 默认当前位置为0
    private Fragment tempFragment; // 用于存储临时的Fragment
    private static final int MESSAGE_BACK = 1;
    private boolean isFlag = true;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BACK:
                    isFlag = true; // 在2s时,恢复isFlag的变量值
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 创建数据库
        SQLiteDatabase db = Connector.getDatabase();
        //saveOfferBook();

        /**
         * 以下为滑动侧菜单效果
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);//去除默认navigationItem的图标

        /**
         * 以下为默认隐藏顶部网格图标，点击更多后 显示，并隐藏文字标签
         */
        gridLayout = (GridLayout) findViewById(R.id.gridlayout);
        menu_6 = (Button) findViewById(R.id.menu_6);
        up = (ImageView) findViewById(R.id.up);
        linear_tag = (LinearLayout) findViewById(R.id.linear_tag);
        menu_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
                    if (isButton) {
                        linear_tag.setVisibility(View.GONE);
                        isButton = false;
                    }
                    isButton = false;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    isButton = true;
                }
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
                    isButton = false;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    isButton = true;
                    if (isButton) {
                        linear_tag.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        search_edit_text = (EditText) findViewById(R.id.search_edit_text);
        search_edit_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });


        rgMain = (RadioGroup) findViewById(R.id.rgMain);
        rbRecommend = (RadioButton) findViewById(R.id.rbRecommend);
        rbBook = (RadioButton) findViewById(R.id.rbBook);
        rbMagazine = (RadioButton) findViewById(R.id.rbMagazine);
        flContainer = (FrameLayout) findViewById(R.id.flContainer);
        initFragment();
        initListener();
        rgMain.check(R.id.rbRecommend); // 默认选中为我推荐

/**
 * 为list添加手势监听

 mlistcontentview.setOnTouchListener(new View.OnTouchListener(){
 public boolean onTouch(View v, MotionEvent event){
 switch(event.getAction()){
 case MotionEvent.ACTION_DOWN:
 mFirstY = event.getY();
 break;
 case MotionEvent.ACTION_MOVE:
 mCurrentY=event.getY();
 if (mCurrentY-mFirstY>mTouchSlop){
 //下滑  显示导航和搜索
 mhinderview.setVisibility(View.VISIBLE);
 }else if (mFirstY-mCurrentY>mTouchSlop){
 //上滑 隐藏导航和搜索
 mhinderview.setVisibility(View.GONE);
 }
 break;
 case MotionEvent.ACTION_UP:
 break;
 }
 return false;
 }
 });
 */
    }

    /**
     * 初始化Fragment
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new NavigationFragment());
        fragments.add(new MagazineFragment());
    }

    /**
     * 初始化RadioGroup被选中时的监听
     */
    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbRecommend:
                        currentPosition = 0;
                        break;
                    case R.id.rbBook:
                        currentPosition = 1;
                        break;
                    case R.id.rbMagazine:
                        currentPosition = 2;
                        break;
                }
                BaseFragment fragment = getFragmentByPosition(currentPosition);
                switchFragment(tempFragment, fragment);
            }
        });
    }

    /**
     * 切换Fragment
     *
     * @param fromFragment
     * @param toFragment
     */
    private void switchFragment(Fragment fromFragment, Fragment toFragment) {
        if (tempFragment != toFragment) {
            tempFragment = toFragment;
            if (toFragment != null) {
                // 开启事务
                transaction = this.getSupportFragmentManager().beginTransaction();
                // 判断要显示的Fragment是否已经被添加
                if (!toFragment.isAdded()) {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment); // 隐藏当前的
                    }
                    transaction.add(R.id.flContainer, toFragment).commit(); // 添加新的
                } else {
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(toFragment).commit();
                }
            }
        }
    }

    /**
     * 得到position位置的Fragment
     *
     * @param position
     * @return
     */
    private BaseFragment getFragmentByPosition(int position) {
        if (fragments != null && fragments.size() > 0) {
            return fragments.get(position);
        }
        return null;
    }

    /**
     * 事先存储一些推荐信息进入数据库


    private void saveOfferBook() {
        for (int i = 0; i < 8; i++) {
            LibsOffer libsOffer = new LibsOffer(i, 1, 1, "11122432", "Effective Java", "Joshua Bloch");
            libsOffer.save();
        }
    }
     */
    /**
     * 把订阅和收藏的图书存进推荐表

    private void saveOfferBook(){
        LibsOffer libsOffer = new LibsOffer();
         libsInfoList= DataSupport.findAll(LibsInfo.class);
        DataSupport.saveAll(libsInfoList);
        libsCollectionList=DataSupport.findAll(LibsCollection.class);
        DataSupport.saveAll(libsCollectionList);
    }
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_book) {
            // Handle the camera action
            Intent intent = new Intent(this, MyBookActivity.class);
            startActivity(intent);
            //跳转到 我的订阅
        } else if (id == R.id.my_collection) {
            Intent intent = new Intent(this, MyCollectionActivity.class);
            startActivity(intent);
            //跳转到 我的收藏
        } else if (id == R.id.my_like) {
            Intent intent = new Intent(this, MyLikeActivity.class);
            startActivity(intent);
            //跳转到 我的爱好
        } else if (id == R.id.setting) {
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
            //跳转到 设置
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && isFlag) {
            isFlag = false;
            Toast.makeText(HomeActivity.this, "再点击一次返回键退出应用", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(MESSAGE_BACK, 2000);
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 保证在activity退出前,移除所有未被执行的消息和回调方法,避免出现内存泄漏!
        handler.removeCallbacksAndMessages(null);
    }

}
