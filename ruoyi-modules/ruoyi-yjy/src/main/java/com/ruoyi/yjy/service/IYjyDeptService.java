package com.ruoyi.yjy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.yjy.domain.YjyDept;
import com.ruoyi.yjy.domain.vo.TreeSelect;

/**
 * 部门表Service接口
 * 
 * @author yjy
 * @date 2022-05-31
 */
public interface IYjyDeptService 
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
     * 批量删除部门表
     * 
     * @param deptIds 需要删除的部门表主键集合
     * @return 结果
     */
    public int deleteYjyDeptByDeptIds(Long[] deptIds);

    /**
     * 删除部门表信息
     * 
     * @param deptId 部门表主键
     * @return 结果
     */
    public int deleteYjyDeptByDeptId(Long deptId);


    public List<TreeSelect> buildDeptTreeSelect(List<YjyDept> depts);
    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */

    public List<YjyDept> buildDeptTree(List<YjyDept> depts);

}
