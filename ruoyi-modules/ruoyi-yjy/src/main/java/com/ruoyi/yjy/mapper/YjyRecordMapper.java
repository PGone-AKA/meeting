package com.ruoyi.yjy.mapper;

import java.util.List;
import com.ruoyi.yjy.domain.YjyRecord;

/**
 * 签到记录Mapper接口
 * 
 * @author yjy
 * @date 2022-06-01
 */
public interface YjyRecordMapper 
{
    /**
     * 查询签到记录
     * 
     * @param recordId 签到记录主键
     * @return 签到记录
     */
    public YjyRecord selectYjyRecordByRecordId(Long recordId);

    /**
     * 查询签到记录列表
     * 
     * @param yjyRecord 签到记录
     * @return 签到记录集合
     */
    public List<YjyRecord> selectYjyRecordList(YjyRecord yjyRecord);

    /**
     * 新增签到记录
     * 
     * @param yjyRecord 签到记录
     * @return 结果
     */
    public int insertYjyRecord(YjyRecord yjyRecord);

    /**
     * 修改签到记录
     * 
     * @param yjyRecord 签到记录
     * @return 结果
     */
    public int updateYjyRecord(YjyRecord yjyRecord);

    /**
     * 删除签到记录
     * 
     * @param recordId 签到记录主键
     * @return 结果
     */
    public int deleteYjyRecordByRecordId(Long recordId);

    /**
     * 批量删除签到记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjyRecordByRecordIds(Long[] recordIds);
}
