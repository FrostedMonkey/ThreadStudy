package threadobjectclasscommonmethods;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepInterrupted
 * @Author chenchen
 * @Date 2020/3/2 20:38
 * @Version 1.0
 * TODO 每隔1秒钟输出当前事件，被中断
 * Thread.sleep()
 * TimeYnit.SECONDS.sleep()
 **/
public class SleepInterrupted implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(new Date());
            try {
                TimeUnit.HOURS.sleep(3);
                TimeUnit.MINUTES.sleep(20);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("被中断了");
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SleepInterrupted());
        thread.start();

        thread.sleep(6500);

        thread.interrupt();
    }
}
