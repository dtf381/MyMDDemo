package com.sunjiajia.alldemo.databinding;

/**
 * Created by mk on 2017/2/6.
 */

public class User {
    public String name;
    public String myBlog;
    public int age;

    public User(String name,int age,String myBlog){
        this.name=name;
        this.age=age;
        this.myBlog=myBlog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyBlog() {
        return myBlog;
    }

    public void setMyBlog(String myBlog) {
        this.myBlog = myBlog;
    }
}
