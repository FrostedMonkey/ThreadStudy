package stopthread;

/**
 * @ClassName RightWayStopThreadInProd
 * @Author chenchen
 * @Date 2020/3/1 22:16
 * @Version 1.0
 * 最佳实践：在catch语句中调用Thread.currentThread().interrupt();来恢复设置中断状态，
 * 以便于在后续的执行中，依然能够检查到刚才发生的中断让他跳出
 **/
public class RightWayStopThreadInProd2 implements Runnable {
    @Override
    public void run() {
        while (true) {
            if(Thread.currentThread().isInterrupted()){
                System.out.println("Interrputed,程序运行结束");
                break;
            }
            reInMethod();

        }
    }

    private void reInMethod() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }
}
