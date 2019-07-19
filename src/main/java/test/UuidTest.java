package test;

import java.util.UUID;

/**
 * Created by LiBingyi on 2019/6/26 17:20
 */
public class UuidTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            System.out.println(UUID.randomUUID().toString().replace("-", ""));
        }
    }

}
