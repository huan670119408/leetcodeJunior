package test;

/**
 * Created by LiBingyi on 2019/5/27 20:53
 */
public class AbAb {

    public static void main(String[] args) throws Exception {
        final AbAb abab = new AbAb();
        Runnable task1 = new Runnable() {
            public void run() {
                while (true) {
                    synchronized (abab) {
                        abab.notify();
                        System.out.println("AAA");
                        try {
                            abab.wait();
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
                    synchronized (abab) {
                        abab.notify();
                        System.out.println("BBB");
                        try {
                            abab.wait();
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
        t2.start();
    }

}
