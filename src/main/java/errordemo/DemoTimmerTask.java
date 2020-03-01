package errordemo;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName DemoTimmerTask
 * @Author chenchen
 * @Date 2020/2/29 22:56
 * @Version 1.0
 * 定时器创建线程
 **/
public class DemoTimmerTask {
    public static void main(String[] args) {
        Timer timer =new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,1000);
    }
}
