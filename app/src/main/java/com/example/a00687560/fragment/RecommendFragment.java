package com.example.a00687560.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a00687560.adapter.LibsCollectionAdapter;
import com.example.a00687560.adapter.MyAdapter;
import com.example.a00687560.adapter.OfferAdapter;
import com.example.a00687560.base.BaseFragment;
import com.example.a00687560.mdtug001.MyCollectionActivity;
import com.example.a00687560.mdtug001.R;
import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.model.LibsInfo;
import com.example.a00687560.model.LibsOffer;
import com.example.a00687560.util.SpUtil;
import com.example.a00687560.util.StringConvert;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Function:为我推荐页面的Fragment
 */

public class RecommendFragment extends BaseFragment {

    private ListView offerList;
    private OfferAdapter Adapter;
    private List<LibsOffer> LibsOfferList;
    private List<List<String>> mList = new ArrayList<List<String>>();
    private List<String> allUserType;
    private Set<Integer> set;

    private List<LibsInfo> all1 = new ArrayList<>();
    private List<LibsInfo> all2 = new ArrayList<>();
    private List<LibsInfo> LibsInFoList;
    private List<LibsCollection> libsCollectionList;

    @Override
    public View initView() {
        Log.e("TAG", "为我推荐页面的视图初始化了");
        View view = View.inflate(mContext, R.layout.fragment_recommend, null);
        offerList = (ListView) view.findViewById(R.id.offer_me_list);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TAG", "为我推荐页面的数据初始化了");
        allUserType = new ArrayList<>();
        LibsOfferList = new ArrayList<>();

        getAllBook();

    }

    private void getAllBook() {
        all1 = DataSupport.findAll(LibsInfo.class);
        //List<LibsCollection> all2 = DataSupport.findAll(LibsCollection.class);
        Log.e("TAG", "all===" + all1.size());
        if (all1 != null && all1.size() > 0) {

                    if (Adapter != null) {
                        Adapter.notifyDataSetChanged();
                    } else {
                        Adapter = new OfferAdapter(mContext, all1);
                        offerList.setAdapter(Adapter);
                    }


        } else {

        }
    }




    /**
     * 获取用户的订阅类型
     */
    private void getUserSubscriber() {
        String subscriber = SpUtil.getInstace().getString("subscriber", "");
        if (!"".equals(subscriber)) {
            Log.e("TAG", "subscriber===" + subscriber);
            // TODO 将用户爱好类型和爱好一样定义，对应一个数字
        }
    }

    /**
     * 获取用户的收藏类型
     */
    private void getUserCollect() {
        String collection = SpUtil.getInstace().getString("collection", "");
        if (!"".equals(collection)) {
            Log.e("TAG", "collection===" + collection);
            // TODO 将用户爱好类型和爱好一样定义，对应一个数字
        }
    }

    /**
     * 获取用户的爱好类型
     */
    private void getUserHobby() {
        String hobby = SpUtil.getInstace().getString("hobby", "");
        if (!"".equals(hobby)) {
            Log.e("TAG", "hobby===" + hobby);
            set = StringConvert.StringToSet(hobby);
        }
    }

    /**
     * 获取全部推荐图书方法(根据获取到的类型条件查询)

    private void getAllOffer() {
        List<LibsOffer> all = DataSupport.findAll(LibsOffer.class);
        if (all != null && all.size() > 0) {
            for (int i = 0; i < all.size(); i++) {
                int type_id = all.get(i).getType_id();
                Log.e("TAG", "type_id===" + type_id);
                if (set != null) {
                    if (set.contains(type_id)) {
                        // 如果获取到的类型和图书的类型相同就加入推荐列表
                        LibsOfferList.add(all.get(i));
                    }
                }
            }
//            LibsOfferList = all;
//            displayDataFromDB();
            if (Adapter != null) {
                Adapter.notifyDataSetChanged();
            } else {
                Adapter = new OfferAdapter(mContext, LibsOfferList);
                offerList.setAdapter(Adapter);
            }
        } else {
            Toast.makeText(mContext, "没找到推荐内容", Toast.LENGTH_SHORT).show();
        }
    }
     */

    /**@Override
    public void onResume() {
        super.onResume();
        getUserHobby();
        getAllOffer();// 获取全部推荐图书
    }

    private void displayDataFromDB() {
        mList.clear();
        List<String> columnList = new ArrayList<String>();
        columnList.add("book_name");
        columnList.add("book_author");
        columnList.add("book_id");
        mList.add(columnList);
        if (LibsOfferList != null && LibsOfferList.size() > 0) {
            for (int i = 0; i < LibsOfferList.size(); i++) {
                LibsOffer offer = LibsOfferList.get(i);
                List<String> stringList = new ArrayList<String>();
                stringList.add(String.valueOf(offer.getId()));
                stringList.add(offer.getBook_name());
                stringList.add(offer.getBook_author());
                stringList.add(offer.getBook_id());
                mList.add(stringList);
            }
            Adapter = new OfferAdapter(mContext, LibsOfferList);
            offerList.setAdapter(Adapter);
        }
    }
     */
}
