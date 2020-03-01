package stopthread;

/**
 * @ClassName CantInterrupt
 * @Author chenchen
 * @Date 2020/3/1 21:55
 * @Version 1.0
 * 如果while里面放try/catch会导致中断失效
 **/
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            //当5秒的时候告知要终止，此时异常被捕获了，但是循环没有到达结束条件，还会往下运行。
            //即使加上了Thread.currentThread().isInterrupted()判断也不行。因为sleep过程中如果有线程中断了当前线程.抛出此异常时，当前线程的中断状态将被清除。
            // if any thread has interrupted the current thread. The<i>interrupted status</i> of the current thread is cleared when this exception is thrown.
            //如果有线程中断了当前线程。抛出此异常时，当前线程的中断状态将被清除。
            while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0){
                    System.out.println(num + "是100的倍数");
                }
                num++;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }

}
