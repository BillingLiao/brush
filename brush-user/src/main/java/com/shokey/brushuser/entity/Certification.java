package com.shokey.brushuser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 实名认证
 * @author Billing
 * @date 2018/11/27 10:30
 */
@TableName("t_certification")
public class Certification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer readNameId;

    /**
     * 实名姓名
     */
    private String realName;

    /**
     * 身份证号
     */
    private String idNumber;

    /**
     * 身份证正面照
     */
    private String imgFront;

    /**
     * 身份证背面照
     */
    private String imgBack;

    public Integer getReadNameId() {
        return readNameId;
    }

    public void setReadNameId(Integer readNameId) {
        this.readNameId = readNameId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getImgFront() {
        return imgFront;
    }

    public void setImgFront(String imgFront) {
        this.imgFront = imgFront;
    }

    public String getImgBack() {
        return imgBack;
    }

    public void setImgBack(String imgBack) {
        this.imgBack = imgBack;
    }
}
