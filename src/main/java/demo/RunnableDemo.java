package demo;
/**
 * @ClassName RunnableDemo
 * @Author chenchen
 * @Date 2020/2/29 21:48
 * @Version 1.0
 **/
public class RunnableDemo implements Runnable{
    public static void main(String[] args) {
        new Thread(new RunnableDemo()).start();
    }
    @Override
    public void run() {
        System.out.println("使用runnable");
    }
}
