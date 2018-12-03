package com.shokey.brushuser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 店铺绑定
 * @author Billing
 * @date 2018/11/27 10:17
 */
@TableName("t_shop")
public class Shop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 店铺编号
     */
    @TableId
    private Integer shopId;

    /**
     * 平台编号
     */
    private Integer platformId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 店铺名称
     */
    private String shopName;

    private String qq;

    /**
     * 手机号码
     */
    private String telphone;

    /**
     * 商品链接
     */
    private String linkAddress;
}
