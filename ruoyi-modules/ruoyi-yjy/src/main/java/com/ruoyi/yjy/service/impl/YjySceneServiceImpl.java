package com.ruoyi.yjy.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.YjySceneMapper;
import com.ruoyi.yjy.domain.YjyScene;
import com.ruoyi.yjy.service.IYjySceneService;

/**
 * 场景签到Service业务层处理
 * 
 * @author yjy
 * @date 2022-06-01
 */
@Service
public class YjySceneServiceImpl implements IYjySceneService 
{
    @Autowired
    private YjySceneMapper yjySceneMapper;

    /**
     * 查询场景签到
     * 
     * @param sceneId 场景签到主键
     * @return 场景签到
     */
    @Override
    public YjyScene selectYjySceneBySceneId(Long sceneId)
    {
        return yjySceneMapper.selectYjySceneBySceneId(sceneId);
    }

    /**
     * 查询场景签到列表
     * 
     * @param yjyScene 场景签到
     * @return 场景签到
     */
    @Override
    public List<YjyScene> selectYjySceneList(YjyScene yjyScene)
    {
        return yjySceneMapper.selectYjySceneList(yjyScene);
    }

    /**
     * 新增场景签到
     * 
     * @param yjyScene 场景签到
     * @return 结果
     */
    @Override
    public int insertYjyScene(YjyScene yjyScene)
    {
        yjyScene.setCreateTime(DateUtils.getNowDate());
        return yjySceneMapper.insertYjyScene(yjyScene);
    }

    /**
     * 修改场景签到
     * 
     * @param yjyScene 场景签到
     * @return 结果
     */
    @Override
    public int updateYjyScene(YjyScene yjyScene)
    {
        return yjySceneMapper.updateYjyScene(yjyScene);
    }

    /**
     * 批量删除场景签到
     * 
     * @param sceneIds 需要删除的场景签到主键
     * @return 结果
     */
    @Override
    public int deleteYjySceneBySceneIds(Long[] sceneIds)
    {
        return yjySceneMapper.deleteYjySceneBySceneIds(sceneIds);
    }

    /**
     * 删除场景签到信息
     * 
     * @param sceneId 场景签到主键
     * @return 结果
     */
    @Override
    public int deleteYjySceneBySceneId(Long sceneId)
    {
        return yjySceneMapper.deleteYjySceneBySceneId(sceneId);
    }
}
