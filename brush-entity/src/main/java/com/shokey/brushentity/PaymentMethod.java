package com.shokey.brushentity;

import java.io.Serializable;

/**
 * 允许支付方式表
 * @author Billing
 * @date 2018/11/27 10:19
 */
public class PaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer paymentMethodId;

    /**
     * 允许支付方式
     */
    private String paymentMethodName;
}
