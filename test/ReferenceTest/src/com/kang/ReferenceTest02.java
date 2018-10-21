package com.kang;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

// 弱引用WeakReference回收测试
public class ReferenceTest02 {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<User> rq = new ReferenceQueue<>();
        
        User u1 = new User("张三");
        User u2 = new User("李四");
        
        WeakReference<User> w1 = new WeakReference<>(u1, rq);
        WeakReference<User> w2 = new WeakReference<>(u2, rq);
        
        // 开始时，u1和u2对象均存在
        User.print(w1.get(), w2.get());
        
        // u1置为null后，u1对象只剩下w1这个弱引用。
        u1 = null;
        
        // u2对象没变化，还是有一个强引用和一个弱引用，所以它被认为是强引用
        
        System.out.println("------------执行GC------------");
        System.gc();
        Thread.sleep(1);    // 稍微暂停一下，等待GC完成
        
        // 执行GC后，只有弱引用的u1对象被回收了，u2没变化
        User.print(w1.get(), w2.get());
        
        System.out.println("------------------------------");
        
        // 去ReferenceQueue里验证u1的弱引用是否被回收
        while(true) {
            Reference r = rq.poll();    // 获取Reference（没有用remove，因为remove会导致陷入阻塞）
            if(r==w1){
                System.out.print("w1被回收了，w1中包裹的对象：");
                getUserFrom(r);
            } else if(r==w2){
                System.out.print("w2被回收了，w2中包裹的对象：");
                getUserFrom(r);
            } else {
                break;  // 既不是w1也不是w2，说明找完了
            }
        }
        // 验证结果是u1对象被回收了，w1被加入了ReferenceQueue
    }
    
    // 从Reference中获取其包裹的对象，并打印
    private static void getUserFrom(Reference r){
        try {
            Field rereferent = Reference.class.getDeclaredField("referent");
            rereferent.setAccessible(true);
            User u = (User)rereferent.get(r);
            User.print(u);
        } catch(NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
