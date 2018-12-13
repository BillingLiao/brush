package com.shokey.brushadmin.black;

import org.kocakosm.jblake2.Blake2b;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Black2b {

    /** logger */
    private static final Logger log = LoggerFactory.getLogger(Black2b.class);
    private static final byte[] key = "Bright moonlight in front of bed, suspected frost on the ground.".getBytes();

    /**
     * 使用black算法加密密码
     * 默认key
     * @param num
     * @param value
     * @return
     */
    public static String encode(int num,String value){
        byte[] s;
        Blake2b blake2b = new Blake2b(32,key);
        s = blake2b.digest(value.getBytes());
        for (int i = 0;i<num;i++){
            s = blake2b.digest(s);
        }
        return bytesToHex(s);
    }

    /**
     * 使用black算法加密密码
     * 自定义key
     * @param num
     * @param value
     * @param salt
     * @return
     */
    public static String encode(int num,String value,String salt){
        byte[] s;
        Blake2b blake2b = new Blake2b(32,salt.getBytes());
        s = blake2b.digest(value.getBytes());
        for (int i = 0;i<num;i++){
            s = blake2b.digest(s);
        }
        return bytesToHex(s);
    }

    public static String bytesToHex(byte[] src){
        if (src == null || src.length <= 0) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i < src.length; i++) {
            // 之所以用byte和0xff相与，是因为int是32位，与0xff相与后就舍弃前面的24位，只保留后8位
            String str = Integer.toHexString(src[i] & 0xff);
            if (str.length() < 2) { // 不足两位要补0
                stringBuilder.append(0);
            }
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
