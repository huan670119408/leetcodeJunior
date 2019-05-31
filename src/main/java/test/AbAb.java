package main.java.test;

/**
 * Created by LiBingyi on 2019/5/27 20:53
 */
public class AbAb {

    public static void main(String[] args) throws Exception {
        Runnable task1 = new Runnable() {
            public void run() {
                while (true) {
                    synchronized (AbAb.class) {
                        AbAb.class.notify();
                        System.out.println("AAA");
                        try {
                            AbAb.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Runnable task2 = new Runnable() {
            public void run() {
                while (true) {
                    synchronized (AbAb.class) {
                        AbAb.class.notify();
                        System.out.println("BBB");
                        try {
                            AbAb.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);
        t1.start();
        Thread.sleep(100);
        t2.start();
    }

}
