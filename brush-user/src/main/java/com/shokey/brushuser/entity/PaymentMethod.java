package com.shokey.brushuser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * 允许支付方式表
 * @author Billing
 * @date 2018/11/27 10:19
 */
@TableName("t_payment_method")
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer paymentMethodId;

    /**
     * 允许支付方式
     */
    private String paymentMethodName;
}
