package com.example.a00687560.model;

import org.litepal.crud.DataSupport;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import static com.example.a00687560.mdtug001.R.id.user_id;



public class StudentInfo extends DataSupport {
    private int id;
    private int password;
    private int type_id;
    //private int collect_id;
    private int book_id;
    private int search_id;

    private List<LibsType> LibsTypeList = new ArrayList<LibsType>();
    private List<LibsInfo> LibsInfotList = new ArrayList<LibsInfo>();
    //private List<LibsCollection> LibsCollectionList = new ArrayList<LibsCollection>();
    private List<SearchHistory> SearchHistoryList = new ArrayList<SearchHistory>();

    public StudentInfo(){

    }
    public StudentInfo(int id,int password){
        this.id=id;
        this.password=password;
    }

    public StudentInfo(int id,int password,int type_id,int collect_id,int book_id,int search_id,List<LibsType> libsTypeList,
                       List<LibsInfo> libsInfoList, List<SearchHistory> searchHistoryList){

        this.id = id;
        this.password = password;
        this.type_id = type_id;
        //this.collect_id = collect_id;
        this.book_id = book_id;
        this.search_id = search_id;
        LibsTypeList = libsTypeList;
        LibsInfotList = libsInfoList;
        SearchHistoryList = searchHistoryList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }


    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

//    public int getCollect_id() {return collect_id;}

// public void setCollect_id(int collect_id) {this.collect_id = collect_id;}

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getSearch_id() {
        return search_id;
    }

    public void setSearch_id(int search_id) {
        this.search_id = search_id;
    }

    public List<LibsType> getLibsTypeList() {
        return LibsTypeList;
    }

    public void setLibsTypeList(List<LibsType> libsTypeList) {
        LibsTypeList = libsTypeList;
    }

    public List<LibsInfo> getLibsInfotList() {
        return LibsInfotList;
    }

    public void setLibsInfotList(List<LibsInfo> libsInfotList) {
        LibsInfotList = libsInfotList;
    }

    public List<SearchHistory> getSearchHistoryList() {
        return SearchHistoryList;
    }

    //public List<LibsCollection> getLibsCollectionList() {return LibsCollectionList;}

    //public void setLibsCollectionList(List<LibsCollection> libsCollectionList) {LibsCollectionList = libsCollectionList;}

    public void setSearchHistoryList(List<SearchHistory> searchHistoryList) {
        SearchHistoryList = searchHistoryList;
    }
    @Override
    public String toString() {
        return "StudentInfo{" +
                "id=" + id +
                ", password=" + password +
                ", type_id=" + type_id +
                //", collect_id=" + collect_id +
                ", book_id=" + book_id +
                ", search_id=" + search_id +
                ", LibsTypeList=" + LibsTypeList +
                ", LibsInfoList=" + LibsInfotList +
                //", LibsCollectionList"+LibsCollectionList+
                ", SearchHistoryList=" + SearchHistoryList +
                '}';
    }
}
