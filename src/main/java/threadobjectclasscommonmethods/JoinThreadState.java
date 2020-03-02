package threadobjectclasscommonmethods;

/**
 * @ClassName JoinThreadState
 * @Author chenchen
 * @Date 2020/3/2 21:11
 * @Version 1.0
 * TODO 先join在mainThread.getState();通过debugger看线程join前后状态对比
 **/
public class JoinThreadState {
    public static void main(String[] args) throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println(mainThread.getState());
                    System.out.println("Thread0 运行结束");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        System.out.println("等待子线程执行完毕");
        thread.join();
        System.out.println("子线程运行完毕");
    }
}
