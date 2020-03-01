package stopthread;

/**
 * @ClassName RightWayStopThreadWithSleepEveryLoop
 * @Author chenchen
 * @Date 2020/3/1 21:32
 * @Version 1.0
 **/

/**
 * 描述：     如果在执行过程中，每次循环都会调用sleep或wait等方法，那么不需要每次迭代都检查是否已中断
 */
public class RightWayStopThreadWithSleepEveryLoop {
    //打印依次休眠10毫秒  5秒的时候主线程休眠
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + "是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
