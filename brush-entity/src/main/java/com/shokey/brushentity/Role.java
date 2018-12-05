package com.shokey.brushentity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 角色表
 *
 * @author Billing
 * @date 2018/11/27 10:11
 */
@TableName("t_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色编号
     */
    @TableId
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private List<Integer> menuIdList;

    /**
     * 创建时间
     */
    private Date createTime;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
