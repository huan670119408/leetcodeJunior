package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


public class MyInvocationHandler implements InvocationHandler {

    private Object target;

    public MyInvocationHandler(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("写在sayHello之前");
        method.invoke(target, args);
        System.out.println("写在sayHello之后");
        return null;
    }
}
