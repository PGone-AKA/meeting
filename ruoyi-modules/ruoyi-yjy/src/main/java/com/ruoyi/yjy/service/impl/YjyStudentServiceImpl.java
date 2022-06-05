package com.ruoyi.yjy.service.impl;

import java.util.List;

import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.utils.bean.BeanValidators;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.YjyStudentMapper;
import com.ruoyi.yjy.domain.YjyStudent;
import com.ruoyi.yjy.service.IYjyStudentService;

import javax.validation.Validator;

/**
 * 人员信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@Service
public class YjyStudentServiceImpl implements IYjyStudentService 
{
    @Autowired
    private YjyStudentMapper yjyStudentMapper;


    @Autowired
    protected Validator validator;

    /**
     * 查询人员信息
     * 
     * @param stuId 人员信息主键
     * @return 人员信息
     */
    @Override
    public YjyStudent selectYjyStudentByStuId(Long stuId)
    {
        return yjyStudentMapper.selectYjyStudentByStuId(stuId);
    }

    /**
     * 查询人员信息列表
     * 
     * @param yjyStudent 人员信息
     * @return 人员信息
     */
    @Override
    public List<YjyStudent> selectYjyStudentList(YjyStudent yjyStudent)
    {
        return yjyStudentMapper.selectYjyStudentList(yjyStudent);
    }

    /**
     * 新增人员信息
     * 
     * @param yjyStudent 人员信息
     * @return 结果
     */
    @Override
    public int insertYjyStudent(YjyStudent yjyStudent)
    {
        yjyStudent.setCreateTime(DateUtils.getNowDate());
        return yjyStudentMapper.insertYjyStudent(yjyStudent);
    }

    /**
     * 修改人员信息
     * 
     * @param yjyStudent 人员信息
     * @return 结果
     */
    @Override
    public int updateYjyStudent(YjyStudent yjyStudent)
    {
        return yjyStudentMapper.updateYjyStudent(yjyStudent);
    }

    /**
     * 批量删除人员信息
     * 
     * @param stuIds 需要删除的人员信息主键
     * @return 结果
     */
    @Override
    public int deleteYjyStudentByStuIds(Long[] stuIds)
    {
        return yjyStudentMapper.deleteYjyStudentByStuIds(stuIds);
    }

    /**
     * 删除人员信息信息
     * 
     * @param stuId 人员信息主键
     * @return 结果
     */
    @Override
    public int deleteYjyStudentByStuId(Long stuId)
    {
        return yjyStudentMapper.deleteYjyStudentByStuId(stuId);
    }

    @Override
    public YjyStudent selectYjyStudentBySno(String stuXh){
        return yjyStudentMapper.selectYjyStudentBySno(stuXh);
    }
    /**
     * 导入用户数据
     *
     * @param studentsList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importUStudent(List<YjyStudent> studentsList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(studentsList) || studentsList.size() == 0)
        {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (YjyStudent user : studentsList)
        {
            try
            {

                YjyStudent yjyStudents = yjyStudentMapper.selectYjyStudentBySno(user.getStuXh());

                if (yjyStudents==null)
                {
                    BeanValidators.validateWithException(validator, user);
                    successNum++;
                    this.insertYjyStudent(user);
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getStuName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    BeanValidators.validateWithException(validator, user);
                    successNum++;
                    this.updateYjyStudent(user);
                    successMsg.append("<br/>" + successNum + "、用户 " + user.getStuName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、用户 " + user.getStuName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、用户 " + user.getStuName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                //log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<YjyStudent> selectYjyStudentByDeptId(Long stuDid){
        return yjyStudentMapper.selectYjyStudentByDeptId(stuDid);
    }


}
