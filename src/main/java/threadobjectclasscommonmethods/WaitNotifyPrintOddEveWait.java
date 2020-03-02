package threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyPrintOddEveWait
 * @Author chenchen
 * @Date 2020/3/2 18:18
 * @Version 1.0
 * 两个线程交替打印0~100的奇偶数，用wait和notify
 **/
public class WaitNotifyPrintOddEveWait {
    private static int count = 0;
    private static final Object resource = new Object();
    public static void main(String[] args) {
        /**
         * 1、拿到锁，就打印
         * 2、打印完，唤醒其他线程，自己就休眠
         */
        new Thread(new TurningRunner(),"偶数").start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new TurningRunner(),"奇数").start();

    }
    static class TurningRunner implements Runnable{

        @Override
        public void run() {
            while(count<=100){
                synchronized (resource){
                    System.out.println(Thread.currentThread().getName()+";"+count++);
                    resource.notify();
                    if(count<=100){
                        try {
                            //如果任务还没结束就让出当前线程，自己休眠
                            resource.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
