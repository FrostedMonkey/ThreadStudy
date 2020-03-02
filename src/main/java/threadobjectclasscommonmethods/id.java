package threadobjectclasscommonmethods;

/**
 * @ClassName id
 * @Author chenchen
 * @Date 2020/3/2 22:41
 * @Version 1.0
 * TODO ID从1开始，JVM运行起来后，我们会自己创建线程的id早已经不是2
 **/
public class id {
    public static void main(String[] args) {
        Thread thread = new Thread();
        System.out.println("主线程ID："+Thread.currentThread().getId());
        System.out.println("子线程id："+thread.getId());
    }
}
