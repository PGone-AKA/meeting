package com.ruoyi.yjy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 场景签到对象 yjy_scene
 * 
 * @author yjy
 * @date 2022-06-01
 */
public class YjyScene extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景ID */
    private Long sceneId;

    /** 场景名称 */
    @Excel(name = "场景名称")
    private String sceneName;

    /** 场景表述 */
    @Excel(name = "场景表述")
    private String sceneDesc;

    public void setSceneId(Long sceneId) 
    {
        this.sceneId = sceneId;
    }

    public Long getSceneId() 
    {
        return sceneId;
    }
    public void setSceneName(String sceneName) 
    {
        this.sceneName = sceneName;
    }

    public String getSceneName() 
    {
        return sceneName;
    }
    public void setSceneDesc(String sceneDesc) 
    {
        this.sceneDesc = sceneDesc;
    }

    public String getSceneDesc() 
    {
        return sceneDesc;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sceneId", getSceneId())
            .append("sceneName", getSceneName())
            .append("sceneDesc", getSceneDesc())
            .append("createTime", getCreateTime())
            .toString();
    }
}
