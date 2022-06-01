package com.ruoyi.yjy.service;

import java.util.List;
import com.ruoyi.yjy.domain.YjyStudent;

/**
 * 人员信息Service接口
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
public interface IYjyStudentService 
{
    /**
     * 查询人员信息
     * 
     * @param stuId 人员信息主键
     * @return 人员信息
     */
    public YjyStudent selectYjyStudentByStuId(Long stuId);

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
     * 批量删除人员信息
     * 
     * @param stuIds 需要删除的人员信息主键集合
     * @return 结果
     */
    public int deleteYjyStudentByStuIds(Long[] stuIds);

    /**
     * 删除人员信息信息
     * 
     * @param stuId 人员信息主键
     * @return 结果
     */
    public int deleteYjyStudentByStuId(Long stuId);

    /**
     * 导入用户数据
     *
     * @param studentsList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importUStudent(List<YjyStudent> studentsList, Boolean isUpdateSupport, String operName);

    public YjyStudent selectYjyStudentBySno(String stuXh);
}
