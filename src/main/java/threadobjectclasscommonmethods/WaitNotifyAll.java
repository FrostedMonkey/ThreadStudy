package threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyAll
 * @Author chenchen
 * @Date 2020/3/2 17:17
 * @Version 1.0
 * 三个线程 线程1和线程2首先被阻塞，线程3唤醒 notify notifyAll
 * start先执行不代表线程先启动
 **/
public class WaitNotifyAll implements Runnable{

    private static final Object resourceA = new Object();

    @Override
    public void run() {
        synchronized (resourceA){
            System.out.println(Thread.currentThread().getName()+"获取到锁");
            try {
                System.out.println(Thread.currentThread().getName()+"wait to start");
                resourceA.wait();
                System.out.println(Thread.currentThread().getName()+"is wating to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    resourceA.notifyAll();
                    //resourceA.notify();
                    System.out.println("ThreaC notify");
                }
            }
        });
        threadA.start();
        threadB.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadC.start();
    }
}
