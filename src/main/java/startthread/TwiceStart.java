package startthread;

/**
 * @ClassName TwiceStart
 * @Author chenchen
 * @Date 2020/3/1 0:25
 * @Version 1.0
 **/
public class TwiceStart {
    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
