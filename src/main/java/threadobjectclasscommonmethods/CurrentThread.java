package threadobjectclasscommonmethods;

/**
 * @ClassName CurrentThread
 * @Author chenchen
 * @Date 2020/3/2 21:45
 * @Version 1.0
 * TODO 演示打印main thread0 thread1
 **/
public class CurrentThread implements Runnable{
    public static void main(String[] args) {
        new CurrentThread().run();
        new Thread(new CurrentThread()).start();
        new Thread(new CurrentThread()).start();
    }
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
