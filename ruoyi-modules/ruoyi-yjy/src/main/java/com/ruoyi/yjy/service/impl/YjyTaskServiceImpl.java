package com.ruoyi.yjy.service.impl;

import java.util.List;
import com.ruoyi.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.YjyTaskMapper;
import com.ruoyi.yjy.domain.YjyTask;
import com.ruoyi.yjy.service.IYjyTaskService;

/**
 * 任务表Service业务层处理
 * 
 * @author yjy
 * @date 2022-06-01
 */
@Service
public class YjyTaskServiceImpl implements IYjyTaskService 
{
    @Autowired
    private YjyTaskMapper yjyTaskMapper;

    /**
     * 查询任务表
     * 
     * @param taskId 任务表主键
     * @return 任务表
     */
    @Override
    public YjyTask selectYjyTaskByTaskId(Long taskId)
    {
        return yjyTaskMapper.selectYjyTaskByTaskId(taskId);
    }

    /**
     * 查询任务表列表
     * 
     * @param yjyTask 任务表
     * @return 任务表
     */
    @Override
    public List<YjyTask> selectYjyTaskList(YjyTask yjyTask)
    {
        return yjyTaskMapper.selectYjyTaskList(yjyTask);
    }

    /**
     * 新增任务表
     * 
     * @param yjyTask 任务表
     * @return 结果
     */
    @Override
    public int insertYjyTask(YjyTask yjyTask)
    {
        yjyTask.setCreateTime(DateUtils.getNowDate());
        return yjyTaskMapper.insertYjyTask(yjyTask);
    }

    /**
     * 修改任务表
     * 
     * @param yjyTask 任务表
     * @return 结果
     */
    @Override
    public int updateYjyTask(YjyTask yjyTask)
    {
        return yjyTaskMapper.updateYjyTask(yjyTask);
    }

    /**
     * 批量删除任务表
     * 
     * @param taskIds 需要删除的任务表主键
     * @return 结果
     */
    @Override
    public int deleteYjyTaskByTaskIds(Long[] taskIds)
    {
        return yjyTaskMapper.deleteYjyTaskByTaskIds(taskIds);
    }

    /**
     * 删除任务表信息
     * 
     * @param taskId 任务表主键
     * @return 结果
     */
    @Override
    public int deleteYjyTaskByTaskId(Long taskId)
    {
        return yjyTaskMapper.deleteYjyTaskByTaskId(taskId);
    }

    @Override
    public boolean isWork(Long id){
        boolean flag = false;
        YjyTask yjyTask  = yjyTaskMapper.selectYjyTaskByTaskId(id);
        if (yjyTask!=null&&"1".equals(yjyTask.getTaskStatus())){
            flag = true;
        }
        return flag;
    }

}
