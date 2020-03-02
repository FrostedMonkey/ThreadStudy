package threadobjectclasscommonmethods;

/**
 * @ClassName JoinInterrupt
 * @Author chenchen
 * @Date 2020/3/2 20:58
 * @Version 1.0
 * TODO 演示join期间被中断的效果
 **/
public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //主线程中断，这是当子线程join就会抛出中断异常
                    mainThread.interrupt();
                    Thread.sleep(5000);
                    System.out.println("Thread1 finished.");
                } catch (InterruptedException e) {
                    System.out.println("子线程中断");
                }
            }
        });
        thread1.start();
        System.out.println("等待子线程运行完毕");
        try {
            //子线程加入是会抛出异常，是因为子线程加入，我们等待子线程。等待过程中被中断，所以是主线程被中断，主线程抛出异常
            thread1.join();
        } catch (InterruptedException e) {
            //输出main主线程中断了
            System.out.println(Thread.currentThread().getName() + "主线程中断了");
            //将中断传递给子线程
            thread1.interrupt();
        }
        System.out.println("子线程已运行完毕");
    }

}
