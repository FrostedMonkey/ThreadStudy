package threadobjectclasscommonmethods;

import java.util.Date;
import java.util.LinkedList;

/**
 * @ClassName ProducerConsumerModel
 * @Author chenchen
 * @Date 2020/3/2 17:46
 * @Version 1.0
 * 用wait/notify来实现
 **/
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EvenStorage evenStorage = new EvenStorage();
        Producer producer = new Producer(evenStorage);
        Consumer consumer = new Consumer(evenStorage);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
    class Producer implements Runnable{
        private EvenStorage storage;

        public Producer(EvenStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                storage.put();
            }
        }
    }
    //消费者
    class Consumer implements Runnable{
        private EvenStorage storage;

        public Consumer(EvenStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for(int i=0;i<100;i++){
                storage.take();
            }
        }
    }


    class EvenStorage{
        private int maxSize;
        //作为存储商品的容器
        private LinkedList<Date> storage;

        public EvenStorage() {
            maxSize = 10;
            storage = new LinkedList<>();
        }
        //生产
        public synchronized void put(){
            //当仓库中商品已满，则等待
            while(storage.size()==maxSize){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.add(new Date());
            System.out.println("仓库中有"+storage.size()+"个产品");
            notify();
        }

        //消费
        public synchronized  void take(){
            //当仓库中没有商品时，则等待
            while(storage.size()==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("拿到了"+storage.poll()+",现在仓库还剩下："+storage.size());
            notify();
        }
    }

