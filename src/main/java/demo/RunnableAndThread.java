package demo;

/**
 * @ClassName RunnableAndThread
 * @Author chenchen
 * @Date 2020/2/29 22:33
 * @Version 1.0
 * 同时使用Runnable和Thread两种实现线程的方式
 **/
public class RunnableAndThread {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runnable");
            }
        }){
            @Override
            public void run() {
                System.out.println("thread");
            }
        }.start();
    }
}
