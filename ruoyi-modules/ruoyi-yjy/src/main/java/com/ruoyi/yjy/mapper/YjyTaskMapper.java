package com.ruoyi.yjy.mapper;

import java.util.List;
import com.ruoyi.yjy.domain.YjyTask;

/**
 * 任务表Mapper接口
 * 
 * @author yjy
 * @date 2022-06-01
 */
public interface YjyTaskMapper 
{
    /**
     * 查询任务表
     * 
     * @param taskId 任务表主键
     * @return 任务表
     */
    public YjyTask selectYjyTaskByTaskId(Long taskId);

    /**
     * 查询任务表列表
     * 
     * @param yjyTask 任务表
     * @return 任务表集合
     */
    public List<YjyTask> selectYjyTaskList(YjyTask yjyTask);

    /**
     * 新增任务表
     * 
     * @param yjyTask 任务表
     * @return 结果
     */
    public int insertYjyTask(YjyTask yjyTask);

    /**
     * 修改任务表
     * 
     * @param yjyTask 任务表
     * @return 结果
     */
    public int updateYjyTask(YjyTask yjyTask);

    /**
     * 删除任务表
     * 
     * @param taskId 任务表主键
     * @return 结果
     */
    public int deleteYjyTaskByTaskId(Long taskId);

    /**
     * 批量删除任务表
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjyTaskByTaskIds(Long[] taskIds);
}
