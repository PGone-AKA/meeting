package com.ruoyi.yjy.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 任务表对象 yjy_task
 * 
 * @author yjy
 * @date 2022-06-01
 */
public class YjyTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 任务ID */
    private Long taskId;

    /** 会议主题 */
    @Excel(name = "会议主题")
    private String taskTitle;

    /** 会议内容 */
    @Excel(name = "会议内容")
    private String taskContent;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "开始时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date overTime;

    /** 部门ID */
    private Long taskDid;

    @Excel(name = "签到部门")
    private String deptName;

    /** 场景ID */
    private Long taskSid;

    @Excel(name = "签到场景")
    private String sceneName;

    /** 任务状态 */
    @Excel(name = "任务状态")
    private String taskStatus;

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }
    public void setTaskTitle(String taskTitle) 
    {
        this.taskTitle = taskTitle;
    }

    public String getTaskTitle() 
    {
        return taskTitle;
    }
    public void setTaskContent(String taskContent) 
    {
        this.taskContent = taskContent;
    }

    public String getTaskContent() 
    {
        return taskContent;
    }
    public void setBeginTime(Date beginTime) 
    {
        this.beginTime = beginTime;
    }

    public Date getBeginTime() 
    {
        return beginTime;
    }
    public void setOverTime(Date overTime) 
    {
        this.overTime = overTime;
    }

    public Date getOverTime() 
    {
        return overTime;
    }
    public void setTaskDid(Long taskDid) 
    {
        this.taskDid = taskDid;
    }

    public Long getTaskDid() 
    {
        return taskDid;
    }
    public void setTaskSid(Long taskSid) 
    {
        this.taskSid = taskSid;
    }

    public Long getTaskSid() 
    {
        return taskSid;
    }
    public void setTaskStatus(String taskStatus) 
    {
        this.taskStatus = taskStatus;
    }

    public String getTaskStatus() 
    {
        return taskStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskId", getTaskId())
            .append("taskTitle", getTaskTitle())
            .append("taskContent", getTaskContent())
            .append("beginTime", getBeginTime())
            .append("overTime", getOverTime())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("taskDid", getTaskDid())
            .append("taskSid", getTaskSid())
            .append("taskStatus", getTaskStatus())
            .toString();
    }
}
