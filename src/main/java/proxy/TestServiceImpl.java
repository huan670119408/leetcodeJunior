package proxy;

/**
 * Created by LiBingyi on 2019/6/18 9:30
 */
public class TestServiceImpl implements ITestService {

    @Override
    public String saySomething(String str) {
        System.out.println(str);
        return null;
    }

}
