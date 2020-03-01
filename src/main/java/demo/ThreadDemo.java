package demo;
/**
 * @ClassName ThreadDemo
 * @Author chenchen
 * @Date 2020/2/29 21:46
 * @Version 1.0
 **/
public class ThreadDemo extends Thread{
    public static void main(String[] args) {
        new ThreadDemo().start();
    }
    @Override
    public void run() {
        System.out.println("使用Thread类");
    }
}
