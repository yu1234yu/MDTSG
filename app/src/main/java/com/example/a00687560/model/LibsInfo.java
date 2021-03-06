package com.example.a00687560.model;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class LibsInfo extends DataSupport {
    private int id;
    private String lib_name;
    private int type_id;
    private LibsInfo libsInfo;
    private List<StudentInfo> StudentInfoList = new ArrayList<>();

    public LibsInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib_name() {
        return lib_name;
    }

    public void setLib_name(String lib_name) {
        this.lib_name = lib_name;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public LibsInfo getLibsInfo() {
        return libsInfo;
    }

    public void setLibsInfo(LibsInfo libsInfo) {
        this.libsInfo = libsInfo;
    }

    public List<StudentInfo> getStudentInfoList() {
        return StudentInfoList;
    }

    public void setStudentInfoList(List<StudentInfo> studentInfoList) {
        StudentInfoList = studentInfoList;
    }


}
