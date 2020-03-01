package errordemo;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ErrorWays
 * @Author chenchen
 * @Date 2020/2/29 22:43
 * @Version 1.0
 * 线程池创建线程
 **/
public class ErrorWays {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task() {
            });
        }
    }
}
class Task implements Runnable{

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
