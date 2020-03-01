package errordemo;

/**
 * @ClassName innerClass
 * @Author chenchen
 * @Date 2020/2/29 23:02
 * @Version 1.0
 * 使用匿名内部类的方式
 **/
public class innerClass {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();


    }
}
