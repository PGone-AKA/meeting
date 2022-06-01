package com.ruoyi.yjy.service;

import java.util.List;
import com.ruoyi.yjy.domain.YjyScene;

/**
 * 场景签到Service接口
 * 
 * @author yjy
 * @date 2022-06-01
 */
public interface IYjySceneService 
{
    /**
     * 查询场景签到
     * 
     * @param sceneId 场景签到主键
     * @return 场景签到
     */
    public YjyScene selectYjySceneBySceneId(Long sceneId);

    /**
     * 查询场景签到列表
     * 
     * @param yjyScene 场景签到
     * @return 场景签到集合
     */
    public List<YjyScene> selectYjySceneList(YjyScene yjyScene);

    /**
     * 新增场景签到
     * 
     * @param yjyScene 场景签到
     * @return 结果
     */
    public int insertYjyScene(YjyScene yjyScene);

    /**
     * 修改场景签到
     * 
     * @param yjyScene 场景签到
     * @return 结果
     */
    public int updateYjyScene(YjyScene yjyScene);

    /**
     * 批量删除场景签到
     * 
     * @param sceneIds 需要删除的场景签到主键集合
     * @return 结果
     */
    public int deleteYjySceneBySceneIds(Long[] sceneIds);

    /**
     * 删除场景签到信息
     * 
     * @param sceneId 场景签到主键
     * @return 结果
     */
    public int deleteYjySceneBySceneId(Long sceneId);
}
