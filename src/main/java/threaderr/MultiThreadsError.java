package threaderr;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName MultiThreadsError
 * @Author chenchen
 * @Date 2020/3/9 22:42
 * @Version 1.0
 * 运行结果出错
 * 演示计数不准确（减少），找出具体出错的位置
 **/
public class MultiThreadsError implements Runnable{
    static volatile CyclicBarrier cyclicBarrier1 =new CyclicBarrier(2);
    static volatile CyclicBarrier cyclicBarrier2 =new CyclicBarrier(2);
    static MultiThreadsError multiThreadsError=new MultiThreadsError();
    int index = 0;
    final boolean[] marked =new boolean[100000];
    static AtomicInteger realIndex = new AtomicInteger();
    static AtomicInteger wrongCount = new AtomicInteger();
    @Override
    public void run() {
        marked[0]=true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            index++;
            try {
                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (multiThreadsError){
                if(marked[index]&&marked[index-1]){
                    System.out.println("发生错误"+index);
                    wrongCount.incrementAndGet();
                }
                marked[index] =true;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Thread thread =new Thread(multiThreadsError);
        Thread thread2 =new Thread(multiThreadsError);
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        System.out.println(multiThreadsError.index);
        System.out.println("真正运行的次数"+realIndex.get());
        System.out.println("错误的次数"+wrongCount.get());
    }
}
