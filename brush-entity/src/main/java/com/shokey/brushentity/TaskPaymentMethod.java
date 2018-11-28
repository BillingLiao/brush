package com.shokey.brushentity;

import java.io.Serializable;

/**
 * @author Billing
 * @date 2018/11/27 10:20
 */
public class TaskPaymentMethod implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 任务编号
     */
    private Integer taskId;

    /**
     * 允许支付编号
     */
    private Integer paymentMethodId;
}
