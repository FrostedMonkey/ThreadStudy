package threadobjectclasscommonmethods;

/**
 * @ClassName SleepDontReleaseMonitor
 * @Author chenchen
 * @Date 2020/3/2 19:01
 * @Version 1.0
 * 展示下承诺恒sleep的时候不是放synchronized的monitor，等sleep时间到了以后，正常结束后才会释放锁
 **/
public class SleepDontReleaseMonitor implements Runnable{
    public static void main(String[] args) {
        SleepDontReleaseMonitor sleepDontReleaseMonitor = new SleepDontReleaseMonitor();
        new Thread(sleepDontReleaseMonitor).start();
        new Thread(sleepDontReleaseMonitor).start();
    }
    @Override
    public void run() {
        syn();
    }
    private synchronized void syn(){
        System.out.println("线程："+Thread.currentThread().getName()+"获取到monitor");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程："+Thread.currentThread().getName()+"退出同步代码块");
    }
}
