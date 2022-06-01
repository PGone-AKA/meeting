package com.ruoyi.yjy.mapper;

import java.util.List;
import com.ruoyi.yjy.domain.YjyStudent;

/**
 * 人员信息Mapper接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public interface YjyStudentMapper 
{
    /**
     * 查询人员信息
     * 
     * @param stuId 人员信息主键
     * @return 人员信息
     */
    public YjyStudent selectYjyStudentByStuId(Long stuId);

    public YjyStudent selectYjyStudentBySno(String stuXh);


    /**
     * 查询人员信息列表
     * 
     * @param yjyStudent 人员信息
     * @return 人员信息集合
     */
    public List<YjyStudent> selectYjyStudentList(YjyStudent yjyStudent);

    /**
     * 新增人员信息
     * 
     * @param yjyStudent 人员信息
     * @return 结果
     */
    public int insertYjyStudent(YjyStudent yjyStudent);

    /**
     * 修改人员信息
     * 
     * @param yjyStudent 人员信息
     * @return 结果
     */
    public int updateYjyStudent(YjyStudent yjyStudent);

    /**
     * 删除人员信息
     * 
     * @param stuId 人员信息主键
     * @return 结果
     */
    public int deleteYjyStudentByStuId(Long stuId);

    /**
     * 批量删除人员信息
     * 
     * @param stuIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjyStudentByStuIds(Long[] stuIds);
}
