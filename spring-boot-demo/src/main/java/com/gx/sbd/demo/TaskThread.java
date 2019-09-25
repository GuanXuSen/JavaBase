package com.gx.sbd.demo;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName : TaskThread
 * @Description :TOO
 * @Author : gx
 * @Date : 2019/9/24 14:34
 * @Version : 1.0
 */
public class TaskThread extends Thread {

    public  String taskName;

    public TaskThread(String taskName){
        this.taskName = taskName;
    }

    private static int taskNum = 50;

    private static Deque<Integer>  deque =  new ConcurrentLinkedDeque();

    static {
        for (int i = 0; i < 50; i++) {
            deque.add(i);
        }
    }

    private static final Object obj = new Object() ;
    //创建所对象
    private static final Lock lock = new ReentrantLock(true) ;
    @Override
    public void run(){
        test4();
    }

    /**
     * 同步 代码块
     */
    private  void test(){
        synchronized (obj){
            int useTime = (int) (Math.random() * 10 * 1000);
            useTime = useTime / 1000 * 1000;
            System.out.println(taskName + " 等待时间" + useTime);
            try {
                Thread.sleep(useTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            taskNum--;
            System.out.println(taskName + " 获取序号。。。" + taskNum);
        }

    }

    /**
     * 同步方法 （多线程问题，数据不正确）
     */
    private synchronized void test2(){
        int useTime = (int) (Math.random() * 10 * 1000);
        useTime = useTime / 1000 * 1000;
        System.out.println(taskName + " 等待时间" + useTime);
        try {
            Thread.sleep(useTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        taskNum--;
        System.out.println(taskName + " 获取序号。。。" + taskNum);


    }


    /**
     * lock
     */
    private  void test3(){
        lock.lock();
        try{
            int useTime = (int) (Math.random() * 10 * 1000);
            useTime = useTime / 1000 * 1000;
            System.out.println(taskName + " 等待时间" + useTime);
            try {
                Thread.sleep(useTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            taskNum--;
            System.out.println(taskName + " 获取序号。。。" + taskNum);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }

    /**
     * 队列
     */
    private  void test4(){

        try{
            int useTime = (int) (Math.random() * 10 * 1000);
            useTime = useTime / 1000 * 1000;
            // System.out.println(taskName + " 等待时间" + useTime);
            try {
                Thread.sleep(useTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(taskName + " 获取序号。。。" + deque.pop());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
