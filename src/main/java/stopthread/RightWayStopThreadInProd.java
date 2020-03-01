package stopthread;

/**
 * @ClassName RightWayStopThreadInProd
 * @Author chenchen
 * @Date 2020/3/1 22:16
 * @Version 1.0
 * 最佳实践：catch了InterruptedException之后优先选择：在方法签名中抛出异常
 * 那么在run方法中就会强制try/catch
 *
 * 理解：因为sleep过程中如果有线程中断了当前线程.抛出此异常时，当前线程的中断状态将被清除。
 * 所以当下层方法如果把异常处理了，那么我们在run方法中就无法得到这个异常中断请求。所以底层方法应该讲异常抛出。这时候我们在run方法中调用
 * 的时候，我们就必须使用try/catch捕获，就可以收到这个异常中断请求。这时候我们可以根据实际情况作出相应的处理（打印日志或是停止等等）。
 **/
public class RightWayStopThreadInProd implements Runnable{
    @Override
    public void run() {
        while(true&&!Thread.currentThread().isInterrupted()){
            System.out.println("aaaaa");
            try {
                ThrowInMethod();
            } catch (InterruptedException e) {
                //保存日志，停止程序。
                e.printStackTrace();
            }
        }
    }

    private void ThrowInMethod() throws InterruptedException {
        //直接try/catch是把异常处理在很低级的层次。而不是run方法。正确方法应该是throws InterruptedException
       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
       Thread.sleep(2000);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( new RightWayStopThreadInProd());
        thread.start();
        thread.sleep(500);
        thread.interrupt();
    }
}
