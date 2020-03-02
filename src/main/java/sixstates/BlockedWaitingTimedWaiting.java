package sixstates;

/**
 * @ClassName BlockedWaitingTimedWaiting
 * @Author chenchen
 * @Date 2020/3/2 14:43
 * @Version 1.0
 * 展示Blocked，Waiting，TimedWaiting
 **/
public class BlockedWaitingTimedWaiting implements Runnable{
    public static void main(String[] args) {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread thread1 = new Thread(runnable);
        thread1.start();
        Thread thread2 = new Thread(runnable);
        thread2.start();
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出Timed_Waiting状态，因为正在执行Thread.sleep(1000);
        System.out.println(thread1.getState());
        //打印出BLOCKED状态，因为thread2想拿得到sync()的锁却拿不到
        System.out.println(thread2.getState());
        try {
            Thread.sleep(1300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打印出WAITING状态，因为执行了wait()
        System.out.println(thread1.getState());

    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn() {
        try {
            Thread.sleep(1000);
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
