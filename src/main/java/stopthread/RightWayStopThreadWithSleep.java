package stopthread;

/**
 * @ClassName RightWayStopThreadWithSleep
 * @Author chenchen
 * @Date 2020/3/1 21:16
 * @Version 1.0
 * 带有sleep的中断线程的写法
 **/
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //主线程休眠0.5秒钟后，提出终止线程的信号，此时上面的线程还在休眠中；
        thread.sleep(500);
        thread.interrupt();
    }
}
