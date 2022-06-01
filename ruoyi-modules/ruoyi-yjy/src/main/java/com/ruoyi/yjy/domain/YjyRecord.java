package com.ruoyi.yjy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 签到记录对象 yjy_record
 * 
 * @author yjy
 * @date 2022-06-01
 */
public class YjyRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录ID */
    private Long recordId;

    /** 任务ID */
    @Excel(name = "任务ID")
    private Long recordTid;

    /** 签到者姓名 */
    @Excel(name = "签到者姓名")
    private String name;

    /** 签到时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "签到时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date signInTime;

    /** 签退时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "签退时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date signOutTime;

    private String taskName;

    public String getTaskName() {

        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setRecordId(Long recordId)
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setRecordTid(Long recordTid) 
    {
        this.recordTid = recordTid;
    }

    public Long getRecordTid() 
    {
        return recordTid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSignInTime(Date signInTime) 
    {
        this.signInTime = signInTime;
    }

    public Date getSignInTime() 
    {
        return signInTime;
    }
    public void setSignOutTime(Date signOutTime) 
    {
        this.signOutTime = signOutTime;
    }

    public Date getSignOutTime() 
    {
        return signOutTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("recordTid", getRecordTid())
            .append("name", getName())
            .append("signInTime", getSignInTime())
            .append("signOutTime", getSignOutTime())
            .append("remark", getRemark())
            .toString();
    }
}
