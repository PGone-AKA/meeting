package com.ruoyi.yjy.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.yjy.domain.YjyDept;
import com.ruoyi.yjy.service.IYjyDeptService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;

/**
 * 部门表Controller
 * 
 * @author yjy
 * @date 2022-05-31
 */
@RestController
@RequestMapping("/dept")
public class YjyDeptController extends BaseController
{
    @Autowired
    private IYjyDeptService yjyDeptService;

    /**
     * 查询部门表列表
     */
    @RequiresPermissions("yjy:dept:list")
    @GetMapping("/list")
    public AjaxResult list(YjyDept yjyDept)
    {
        List<YjyDept> list = yjyDeptService.selectYjyDeptList(yjyDept);
        return AjaxResult.success(list);
    }

    /**
     * 导出部门表列表
     */
    @RequiresPermissions("yjy:dept:export")
    @Log(title = "部门表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjyDept yjyDept)
    {
        List<YjyDept> list = yjyDeptService.selectYjyDeptList(yjyDept);
        ExcelUtil<YjyDept> util = new ExcelUtil<YjyDept>(YjyDept.class);
        util.exportExcel(response, list, "部门表数据");
    }

    /**
     * 获取部门表详细信息
     */
    @RequiresPermissions("yjy:dept:query")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        return AjaxResult.success(yjyDeptService.selectYjyDeptByDeptId(deptId));
    }

    /**
     * 新增部门表
     */
    @RequiresPermissions("yjy:dept:add")
    @Log(title = "部门表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjyDept yjyDept)
    {

        String ancestors = yjyDeptService.selectYjyDeptByDeptId(yjyDept.getParentId()).getAncestors();
        yjyDept.setAncestors(ancestors+","+yjyDept.getParentId());
        return toAjax(yjyDeptService.insertYjyDept(yjyDept));
    }

    /**
     * 修改部门表
     */
    @RequiresPermissions("yjy:dept:edit")
    @Log(title = "部门表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjyDept yjyDept)
    {
        return toAjax(yjyDeptService.updateYjyDept(yjyDept));
    }

    /**
     * 删除部门表
     */
    @RequiresPermissions("yjy:dept:remove")
    @Log(title = "部门表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        return toAjax(yjyDeptService.deleteYjyDeptByDeptIds(deptIds));
    }


    /**
     * 获取部门下拉树列表
     */
    @GetMapping("/treeselect")
    public AjaxResult treeselect(YjyDept dept)
    {
        List<YjyDept> depts = yjyDeptService.selectYjyDeptList(dept);
        return AjaxResult.success(yjyDeptService.buildDeptTreeSelect(depts));
    }

    /**
     * 加载对应角色部门列表树
     */
    @GetMapping(value = "/roleDeptTreeselect/{roleId}")
    public AjaxResult roleDeptTreeselect(@PathVariable("roleId") Long roleId)
    {
        List<YjyDept> depts = yjyDeptService.selectYjyDeptList(new YjyDept());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("checkedKeys", yjyDeptService.selectYjyDeptByDeptId(roleId));
        ajax.put("depts", yjyDeptService.buildDeptTreeSelect(depts));
        return ajax;
    }
}
