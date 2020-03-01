package stopthread.volatiledemo;

/**
 * @ClassName WrongWayVolatile
 * @Author chenchen
 * @Date 2020/3/1 23:19
 * @Version 1.0
 * 演示用volatile 看似可行
 **/
public class WrongWayVolatile implements Runnable {
    private volatile boolean cancled = false;

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !cancled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        thread.sleep(5000);
        wrongWayVolatile.cancled = true;

    }
}
