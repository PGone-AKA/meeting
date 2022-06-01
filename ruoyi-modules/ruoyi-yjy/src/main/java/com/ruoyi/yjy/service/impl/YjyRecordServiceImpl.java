package com.ruoyi.yjy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.YjyRecordMapper;
import com.ruoyi.yjy.domain.YjyRecord;
import com.ruoyi.yjy.service.IYjyRecordService;

/**
 * 签到记录Service业务层处理
 * 
 * @author yjy
 * @date 2022-06-01
 */
@Service
public class YjyRecordServiceImpl implements IYjyRecordService 
{
    @Autowired
    private YjyRecordMapper yjyRecordMapper;

    /**
     * 查询签到记录
     * 
     * @param recordId 签到记录主键
     * @return 签到记录
     */
    @Override
    public YjyRecord selectYjyRecordByRecordId(Long recordId)
    {
        return yjyRecordMapper.selectYjyRecordByRecordId(recordId);
    }

    /**
     * 查询签到记录列表
     * 
     * @param yjyRecord 签到记录
     * @return 签到记录
     */
    @Override
    public List<YjyRecord> selectYjyRecordList(YjyRecord yjyRecord)
    {
        return yjyRecordMapper.selectYjyRecordList(yjyRecord);
    }

    /**
     * 新增签到记录
     * 
     * @param yjyRecord 签到记录
     * @return 结果
     */
    @Override
    public int insertYjyRecord(YjyRecord yjyRecord)
    {
        return yjyRecordMapper.insertYjyRecord(yjyRecord);
    }

    /**
     * 修改签到记录
     * 
     * @param yjyRecord 签到记录
     * @return 结果
     */
    @Override
    public int updateYjyRecord(YjyRecord yjyRecord)
    {
        return yjyRecordMapper.updateYjyRecord(yjyRecord);
    }

    /**
     * 批量删除签到记录
     * 
     * @param recordIds 需要删除的签到记录主键
     * @return 结果
     */
    @Override
    public int deleteYjyRecordByRecordIds(Long[] recordIds)
    {
        return yjyRecordMapper.deleteYjyRecordByRecordIds(recordIds);
    }

    /**
     * 删除签到记录信息
     * 
     * @param recordId 签到记录主键
     * @return 结果
     */
    @Override
    public int deleteYjyRecordByRecordId(Long recordId)
    {
        return yjyRecordMapper.deleteYjyRecordByRecordId(recordId);
    }
}
