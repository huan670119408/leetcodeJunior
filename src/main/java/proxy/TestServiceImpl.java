package proxy;


public class TestServiceImpl implements ITestService {

    @Override
    public void saySomething(String str) {
        System.out.println(str);
    }

}
