package test.ReferenceTest;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;

// 软引用SoftReference回收测试
public class ReferenceTest01 {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<User> rq = new ReferenceQueue<>();
    
        User u1 = new User("张三");
        User u2 = new User("李四");
    
        SoftReference<User> s1 = new SoftReference<>(u1, rq);
        SoftReference<User> s2 = new SoftReference<>(u2, rq);
    
        // 开始时，u1和u2对象均存在
        User.print(s1.get(), s2.get());
    
        // u1置为null后，u1对象只剩下s1这个软引用。
        u1 = null;
    
        // u2对象没变化，还是有一个强引用和一个软引用，所以它被认为是强引用
    
        System.out.println("------------执行GC------------");
        System.gc();
        Thread.sleep(1);    // 稍微暂停一下，等待GC完成
        
        // 执行GC后，由于JVM堆内存充足，所以u1和u2这两个对象依然存在，s1和s2也没有被回收
        User.print(s1.get(), s2.get());
    
        System.out.println("------------------------------");
    
        // 去ReferenceQueue里验证u1的软引用是否被回收
        while(true) {
            Reference r = rq.poll();    // 获取Reference（没有用remove，因为remove会导致陷入阻塞）
            if(r==s1){
                System.out.print("s1被回收了，s1中包裹的对象：");
                getUserFrom(r);
            } else if(r==s2){
                System.out.print("s2被回收了，s2中包裹的对象：");
                getUserFrom(r);
            } else {
                break;  // 既不是s1也不是s2，说明找完了
            }
        }
        // 验证结果是堆内存足够时，两个对象依然存在
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
