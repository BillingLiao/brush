package com.shokey.brushuser.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * @author Billing
 * @date 2018/11/27 10:21
 */
@TableName("t_task_platform")
public class TaskPlatform implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 任务平台编号
     */
    @TableId
    private Integer platformId;

    /**
     * 任务平台名称
     */
    private String platfromName;
}
