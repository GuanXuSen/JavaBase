package com.gx.sbd.demo;

/**
 * @ClassName : ThreadLocalContext
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/29 14:38
 * @Version : 1.0
 */
public class ThreadLocalContext {

    private static ThreadLocal<LoginInfo> local = new ThreadLocal<>();

    public static LoginInfo getLocal(){
        return local.get();
    }

    public static  void setLocal(LoginInfo localInfo){
        local.set(localInfo);
    }

    public static void removeLocal(){
        local.remove();
    }
}
