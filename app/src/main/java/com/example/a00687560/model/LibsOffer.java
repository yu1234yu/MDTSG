package com.example.a00687560.model;

import org.litepal.crud.DataSupport;


public class LibsOffer extends DataSupport {
    private int id;
    private int user_id;
    private int type_id;
    private String  book_id;
    private String book_name;
    private String book_author;


    public LibsOffer(){

    }

    public LibsOffer(int id,int user_id,int type_id,String book_id,String book_name,String book_author){
        this.id=id;
        this.book_id=book_id;
        this.book_author=book_author;
        this.book_name=book_name;
        this.user_id=user_id;
        this.type_id=type_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

}
