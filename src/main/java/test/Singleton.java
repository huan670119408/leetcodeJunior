package scr.main.java.test;


public class Singleton {

    private Singleton() {}

    private static class SingletonInstance {
        private static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonInstance.instance;
    }

}
