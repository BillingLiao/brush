package com.shokey.brushentity;

import com.shokey.brushcommon.tool.GoogleAuthenticator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BrushEntityApplicationTests {

    //当测试authTest时候，把genSecretTest生成的secret值赋值给它
    private static String secret="R2Q3S52RNXBTFTOM";

    @Test
    public void contextLoads() {
        //test 测试会启动springboot，但我并不想启动
        secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcode("329057470@qq.com", secret);
        System.out.println("qrcode:" + qrcode + ",key:" + secret);
    }

    @Test
    public void verifyTest() {
        long code = 807337;
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(5);
        boolean r = ga.check_code(secret, code, t);
        System.out.println("检查code是否正确？" + r);
    }

}
