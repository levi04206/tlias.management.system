package com.itheima;

public class LocalThreadTest {

    private static ThreadLocal<String> local = new ThreadLocal<>();//
    public static void main(String[] args) {
        local.set("玩偶姐姐");

        String s = local.get();
        System.out.println(Thread.currentThread() + ":" +s);

        local.remove();
        System.out.println( Thread.currentThread() + ":" +local.get());
    }
}
