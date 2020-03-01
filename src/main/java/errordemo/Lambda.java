package errordemo;

/**
 * @ClassName Lambda
 * @Author chenchen
 * @Date 2020/2/29 23:04
 * @Version 1.0
 * 使用lambda表达式的方法
 **/
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
