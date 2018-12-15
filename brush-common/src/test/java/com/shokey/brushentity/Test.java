package com.shokey.brushentity;

import com.shokey.brushcommon.tool.GoogleAuthenticator;
import com.shokey.brushcommon.tool.QRCodeUtil;

/**
 * test 测试不好使
 */
public class Test {

    //当测试authTest时候，把genSecretTest生成的secret值赋值给它
    private static String secret="63VEOIIHHXMRU4MS";

    /**
     * 测试main
     */
    public static void main(String[] args) {
//        new Test().contextLoad();
        new Test().verifyTest();
    }


    /**
     * 生成key添加谷歌验证器
     */
    public void contextLoad() {
        secret = GoogleAuthenticator.generateSecretKey();
        String qrcode = GoogleAuthenticator.getQRBarcode("329057470@qq.com", secret);
        System.out.println("qrcode:" + qrcode + ",key:" + secret);
    }

    public void verifyTest() {
        long code = 117543;
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(6);
        boolean r = ga.check_code(secret, code, t);
        System.out.println("检查code是否正确？" + r);
    }

    public static void c(String[] args) {
        String text = "hello world!"; // 随机生成验证码
        System.out.println("随机码： " + text);
        int width = 100; // 二维码图片的宽
        int height = 100; // 二维码图片的高
        String format = "png"; // 二维码图片的格式

        try {
            // 生成二维码图片，并返回图片路径
            String pathName = QRCodeUtil.generateQRCode(text, width, height, format, "D:/new.png");
            System.out.println("生成二维码的图片路径： " + pathName);

            String content = QRCodeUtil.parseQRCode(pathName);
            System.out.println("解析出二维码的图片的内容为： " + content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
