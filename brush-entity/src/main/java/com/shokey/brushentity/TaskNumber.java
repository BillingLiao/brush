package com.shokey.brushentity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * @author Billing
 * @date 2018/11/27 10:20
 */
@TableName("t_task_number")
public class TaskNumber implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务号编号
     */
    @TableId
    private Integer taskNumberId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 会员名
     */
    private String membershipName;
}
