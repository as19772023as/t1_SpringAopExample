package ru.strebkov.t1_SpringAopExample.utils;

public class UserContext {
    //хранит локал перем для потоков изолированно от др потоков
    private static final ThreadLocal<String> USERNAME = new ThreadLocal<>();

    private UserContext(){

    }

    public static String getUsername(){
        return USERNAME.get();
    }

    public static void setUsername(String username){
        USERNAME.set(username);
    }
}
