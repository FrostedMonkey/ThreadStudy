package threadobjectclasscommonmethods;

/**
 * @ClassName WaitNotifyPrintOddEvenSyn
 * @Author chenchen
 * @Date 2020/3/2 18:03
 * @Version 1.0
 * 两个线程交替打印0~100的奇偶数，用synchronized关键字实现
 **/
public class WaitNotifyPrintOddEvenSyn {
    private static final Object printResource = new Object();
    /**
     * 1、新建两个线程1只处理偶数 2只处理奇数（用为运算）
     * 2、用synchronized作为通信机制
     */
    private static int count;

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (printResource) {
                        if ((count & 1) == 0){
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }
                    }
                }

            }
        },"偶数").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < 100) {
                    synchronized (printResource) {
                        if ((count & 1) == 1){
                            System.out.println(Thread.currentThread().getName()+":"+count++);
                        }

                    }
                }

            }
        },"奇数").start();


    }
}
