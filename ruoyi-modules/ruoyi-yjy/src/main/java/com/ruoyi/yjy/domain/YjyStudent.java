package com.ruoyi.yjy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 人员信息对象 yjy_student
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public class YjyStudent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long stuId;

    /** 学号 */
    @Excel(name = "学号")
    private String stuXh;

    /** 姓名 */
    @Excel(name = "姓名")
    private String stuName;

    /** 性别 */
    @Excel(name = "性别(0:女,1:男,2未知)")
    private String stuSex;

    /** 部门ID */
    @Excel(name = "部门编号")
    private Long stuDid;
    /** 部门 */
    //@Excel(name = "部门")
    private String stuDept;

    public String getStuDept() {
        return stuDept;
    }

    public void setStuDept(String stuDept) {
        this.stuDept = stuDept;
    }

    public void setStuId(Long stuId)
    {
        this.stuId = stuId;
    }

    public Long getStuId() 
    {
        return stuId;
    }
    public void setStuXh(String stuXh) 
    {
        this.stuXh = stuXh;
    }

    public String getStuXh() 
    {
        return stuXh;
    }
    public void setStuName(String stuName) 
    {
        this.stuName = stuName;
    }

    public String getStuName() 
    {
        return stuName;
    }
    public void setStuSex(String stuSex) 
    {
        this.stuSex = stuSex;
    }

    public String getStuSex() 
    {
        return stuSex;
    }
    public void setStuDid(Long stuDid) 
    {
        this.stuDid = stuDid;
    }

    public Long getStuDid() 
    {
        return stuDid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stuId", getStuId())
            .append("stuXh", getStuXh())
            .append("stuName", getStuName())
            .append("stuSex", getStuSex())
            .append("stuDid", getStuDid())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .toString();
    }
}
