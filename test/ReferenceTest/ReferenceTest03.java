package test.ReferenceTest;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

// 虚引用PhantomReference回收测试
public class ReferenceTest03 {
    public static void main(String[] args) throws InterruptedException {
        ReferenceQueue<User> rq = new ReferenceQueue<>();
    
        User u1 = new User("张三");
        User u2 = new User("李四");
        
        PhantomReference<User> p1 = new PhantomReference<>(u1, rq);
        PhantomReference<User> p2 = new PhantomReference<>(u2, rq);
        
        // 开始时，u1和u2对象均存在，但是禁止通过此种方式直接获取
        User.print(p1.get(), p2.get());
    
        // u1置为null后，u1对象只剩下p1这个虚引用。
        u1 = null;
        
        // u2对象没变化，还是有一个强引用和一个虚引用，所以它被认为是强引用
    
        System.out.println("------------执行GC------------");
        System.gc();
        Thread.sleep(1);    // 稍微暂停一下，等待GC完成
        
        System.out.println("------------------------------");
    
        // 去ReferenceQueue里验证虚引用是否被回收
        while(true) {
            Reference r = rq.poll();    // 获取Reference（没有用remove，因为remove会导致陷入阻塞）
            if(r==p1){
                System.out.print("p1被回收了，p1中包裹的对象：");
                getUserFrom(r);   // JDK9之前，虚引用包裹的对象不会被自动释放，这里有值。JDK9之后，其行为与弱引用一致，这里输出null。
            } else if(r==p2){
                System.out.print("p2被回收了，p2中包裹的对象：");
                getUserFrom(r);
            } else {
                break;  // 既不是p1也不是p2，说明找完了
            }
        }
        // 验证结果是p1被加入了ReferenceQueue，但u1有没有被回收，取决于是JDK9之前（不会回收）还是之后（会回收）
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
