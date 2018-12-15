package com.shokey.brushadmin.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.shokey.brushcommon.tool.GoogleAuthenticator;
import com.shokey.brushcommon.tool.QRCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Hashtable;

@RestController
public class QRCodeController {

    @RequestMapping("/QRCode")
    public BufferedImage getImage(String phone) throws IOException, WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");// 指定编码格式
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);// 指定纠错等级
        hints.put(EncodeHintType.MARGIN, 1);
        String qrcode = GoogleAuthenticator.getQRBarcode(phone, GoogleAuthenticator.generateSecretKey());
        return QRCodeUtil.toBufferedImage(new MultiFormatWriter().encode(qrcode, BarcodeFormat.QR_CODE, 200, 200, hints));
    }
}
