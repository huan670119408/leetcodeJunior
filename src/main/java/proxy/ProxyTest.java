package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by LiBingyi on 2019/6/18 9:26
 */
public class ProxyTest {

    public static void main(String[] args) {
        ITestService target = new TestServiceImpl();
        InvocationHandler handler = new MyInvocationHandler(target);
        ITestService proxy = (ITestService) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), target.getClass().getInterfaces(), handler);
        proxy.saySomething("hello");
    }

}
