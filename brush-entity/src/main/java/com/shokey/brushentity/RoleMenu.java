package com.shokey.brushentity;


import java.io.Serializable;

/**
 * 角色与菜单对应关系
 *
 * @author Billing
 * @date 2018/11/27 10:11
 */
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 设置：
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     * @return Long
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置：角色ID
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取：角色ID
     * @return Long
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置：菜单ID
     * @param menuId 菜单ID
     */
    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    /**
     * 获取：菜单ID
     * @return Long
     */
    public Integer getMenuId() {
        return menuId;
    }

}
