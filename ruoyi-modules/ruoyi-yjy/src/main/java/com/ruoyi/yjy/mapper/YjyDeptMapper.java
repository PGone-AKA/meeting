package com.ruoyi.yjy.mapper;

import java.util.List;
import com.ruoyi.yjy.domain.YjyDept;

/**
 * 部门表Mapper接口
 * 
 * @author yjy
 * @date 2022-05-31
 */
public interface YjyDeptMapper 
{
    /**
     * 查询部门表
     * 
     * @param deptId 部门表主键
     * @return 部门表
     */
    public YjyDept selectYjyDeptByDeptId(Long deptId);

    /**
     * 查询部门表列表
     * 
     * @param yjyDept 部门表
     * @return 部门表集合
     */
    public List<YjyDept> selectYjyDeptList(YjyDept yjyDept);

    /**
     * 新增部门表
     * 
     * @param yjyDept 部门表
     * @return 结果
     */
    public int insertYjyDept(YjyDept yjyDept);

    /**
     * 修改部门表
     * 
     * @param yjyDept 部门表
     * @return 结果
     */
    public int updateYjyDept(YjyDept yjyDept);

    /**
     * 删除部门表
     * 
     * @param deptId 部门表主键
     * @return 结果
     */
    public int deleteYjyDeptByDeptId(Long deptId);

    /**
     * 批量删除部门表
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteYjyDeptByDeptIds(Long[] deptIds);
}
