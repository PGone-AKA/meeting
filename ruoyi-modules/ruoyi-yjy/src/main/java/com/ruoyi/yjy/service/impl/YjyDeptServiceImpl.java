package com.ruoyi.yjy.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.yjy.domain.vo.TreeSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.yjy.mapper.YjyDeptMapper;
import com.ruoyi.yjy.domain.YjyDept;
import com.ruoyi.yjy.service.IYjyDeptService;

/**
 * 部门表Service业务层处理
 * 
 * @author yjy
 * @date 2022-05-31
 */
@Service
public class YjyDeptServiceImpl implements IYjyDeptService 
{
    @Autowired
    private YjyDeptMapper yjyDeptMapper;

    /**
     * 查询部门表
     * 
     * @param deptId 部门表主键
     * @return 部门表
     */
    @Override
    public YjyDept selectYjyDeptByDeptId(Long deptId)
    {
        return yjyDeptMapper.selectYjyDeptByDeptId(deptId);
    }

    /**
     * 查询部门表列表
     * 
     * @param yjyDept 部门表
     * @return 部门表
     */
    @Override
    public List<YjyDept> selectYjyDeptList(YjyDept yjyDept)
    {
        return yjyDeptMapper.selectYjyDeptList(yjyDept);
    }

    /**
     * 新增部门表
     * 
     * @param yjyDept 部门表
     * @return 结果
     */
    @Override
    public int insertYjyDept(YjyDept yjyDept)
    {
        yjyDept.setCreateTime(DateUtils.getNowDate());
        return yjyDeptMapper.insertYjyDept(yjyDept);
    }

    /**
     * 修改部门表
     * 
     * @param yjyDept 部门表
     * @return 结果
     */
    @Override
    public int updateYjyDept(YjyDept yjyDept)
    {
        return yjyDeptMapper.updateYjyDept(yjyDept);
    }

    /**
     * 批量删除部门表
     * 
     * @param deptIds 需要删除的部门表主键
     * @return 结果
     */
    @Override
    public int deleteYjyDeptByDeptIds(Long[] deptIds)
    {
        return yjyDeptMapper.deleteYjyDeptByDeptIds(deptIds);
    }

    /**
     * 删除部门表信息
     * 
     * @param deptId 部门表主键
     * @return 结果
     */
    @Override
    public int deleteYjyDeptByDeptId(Long deptId)
    {
        return yjyDeptMapper.deleteYjyDeptByDeptId(deptId);
    }

    /**
     * 构建前端所需要下拉树结构
     *
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
    @Override
    public List<TreeSelect> buildDeptTreeSelect(List<YjyDept> depts)
    {
        List<YjyDept> deptTrees = buildDeptTree(depts);
        return deptTrees.stream().map(TreeSelect:: new).collect(Collectors.toList());
    }
    /**
     * 构建前端所需要树结构
     *
     * @param depts 部门列表
     * @return 树结构列表
     */
    @Override
    public List<YjyDept> buildDeptTree(List<YjyDept> depts)
    {
        List<YjyDept> returnList = new ArrayList<YjyDept>();
        List<Long> tempList = new ArrayList<Long>();
        for (YjyDept dept : depts)
        {
            tempList.add(dept.getDeptId());
        }
        for (YjyDept dept : depts)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(dept.getParentId()))
            {
                //depts所有节点,dept该节点拥有子节点
                recursionFn(depts, dept);
                returnList.add(dept);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = depts;
        }
        return returnList;
    }


    /**
     * 递归列表
     */
    private void recursionFn(List<YjyDept> list, YjyDept t)
    {
        // 得到子节点列表
        List<YjyDept> childList = getChildList(list, t);
        t.setChildren(childList);
        for (YjyDept tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
    /**
     * 得到子节点列表
     */
    private List<YjyDept> getChildList(List<YjyDept> list, YjyDept t)
    {
        List<YjyDept> tlist = new ArrayList<YjyDept>();
        Iterator<YjyDept> it = list.iterator();
        while (it.hasNext())
        {
            YjyDept n = (YjyDept) it.next();
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getDeptId().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<YjyDept> list, YjyDept t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}
