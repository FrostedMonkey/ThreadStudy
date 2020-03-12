package threaderr;

/**
 * @ClassName MultiThreadEoor4
 * @Author chenchen
 * @Date 2020/3/10 21:14
 * @Version 1.0
 * 初始化未完毕，就this赋值
 **/
public class MultiThreadEoor4 {
    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        Thread.sleep(100);
        if(point!=null){
            System.out.println(point);
        }
    }
}
class Point{
    private final int x,y;
    public Point(int x,int y) throws InterruptedException {
        this.x=x;
        MultiThreadEoor4.point=this;
        Thread.sleep(1000);
        this.y=y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
class PointMaker extends Thread{
    @Override
    public void run() {
        try {
            new Point(1,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
