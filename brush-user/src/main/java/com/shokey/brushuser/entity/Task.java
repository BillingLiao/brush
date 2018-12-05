package com.shokey.brushuser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 任务
 * @author Billing
 * @date 2018/11/27 10:17
 */
@TableName("t_task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer taskId;

    /**
     *
     */
    private Integer taskStatus;

    private Integer existenceState;

    private String taskTitle;

    private Integer platfromId;

    private Integer taskEntry;

    private String keyWord;

    private Integer searchSort;

    private BigDecimal minimumPrice;

    private BigDecimal highestPrice;

    private String ambush;

    private String qrCode;

}
