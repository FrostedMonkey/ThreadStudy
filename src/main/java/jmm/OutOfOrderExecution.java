package jmm;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName OutOfOrderExecution
 * @Author chenchen
 * @Date 2020/3/10 23:02
 * @Version 1.0
 * 演示重排序
 * 直到达到某个条件才停止  测试小概率事件
 *
 **/

public class OutOfOrderExecution {
    private static int x = 0, y = 0;
    private static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            CountDownLatch latch = new CountDownLatch(3);
            /**
             * 三种情况
             * 1、a=1;x=b;b=1;y=a(1)最终结果 x=0 y=1
             * 2、b=1;y=a(0);a=1;x=b(1)最终结果 x=1 y=0
             * 3、b=1;a=1;x=b(1);y=a(1),最终结果 x=1 y=1
             * 会出现x=0 y=0是因为发生了重排序 4行代码的执行顺序为 y=a a=1 x=b  b=1
             */
            Thread one = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.countDown();
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.countDown();
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    b = 1;
                    y = a;
                }
            });
            two.start();
            one.start();
            latch.countDown();
            one.join();
            two.join();

            String result = "第" + i + "次（" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
