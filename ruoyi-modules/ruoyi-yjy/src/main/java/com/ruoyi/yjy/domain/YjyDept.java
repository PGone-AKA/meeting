package com.ruoyi.yjy.domain;

import com.ruoyi.common.core.web.domain.BaseEntity;
import com.ruoyi.system.api.domain.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.TreeEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门表对象 yjy_dept
 * 
 * @author yjy
 * @date 2022-05-31
 */
public class YjyDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门ID */
    private Long deptId;

    private Long parentId;

    private String ancestors;


    /** 名称 */
    @Excel(name = "名称")
    private String deptName;

    /** 子部门 */
    private List<YjyDept> children = new ArrayList<YjyDept>();

    public void setChildren(List<YjyDept> children) {
        this.children = children;
    }

    public List<YjyDept> getChildren()
    {
        return children;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("parentId", getParentId())
            .append("deptName", getDeptName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
