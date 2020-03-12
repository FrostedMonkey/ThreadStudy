package jmm;

/**
 * 演示可见性带来的问题
 * @ClassName FieldVisibility
 * @Author chenchen
 * @Date 2020/3/11 21:25
 * @Version 1.0
 **/
public class FieldVisibility {

    /*
    //volatile解决可见性的问题
    volatile int a = 1;
    volatile int b = 2;*/
    int a =1;
    int b=2;
    private void change() {
        a = 3;
        b = a;
    }


    /**
     * a=3 b=2第一个线程执行完a=3 线程2开始打印
     * a=1 b=2 没有经过任何修改直接打印
     * a=3 b=3 修改完成在打印
     * b=3 a=1 线程1已经做了change操作但是对于打印线程而言看到了b真实的值3  ，但是a还有没同步 所以找到a的原始值1
     */
    private void print() {
        System.out.println("b=" + b + ";a=" + a);
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.change();
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    test.print();
                }
            }).start();
        }

    }


}
