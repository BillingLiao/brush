package com.shokey.brushentity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Billing
 * @date 2018/11/27 10:20
 */
@TableName("t_task_order")
public class TaskOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer orderId;
}
