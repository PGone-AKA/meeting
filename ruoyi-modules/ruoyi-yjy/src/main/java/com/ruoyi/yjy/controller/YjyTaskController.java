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
import com.ruoyi.yjy.domain.YjyTask;
import com.ruoyi.yjy.service.IYjyTaskService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 任务表Controller
 * 
 * @author yjy
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/task")
public class YjyTaskController extends BaseController
{
    @Autowired
    private IYjyTaskService yjyTaskService;

    /**
     * 查询任务表列表
     */
    @RequiresPermissions("yjy:task:list")
    @GetMapping("/list")
    public TableDataInfo list(YjyTask yjyTask)
    {
        startPage();
        List<YjyTask> list = yjyTaskService.selectYjyTaskList(yjyTask);
        return getDataTable(list);
    }

    /**
     * 导出任务表列表
     */
    @RequiresPermissions("yjy:task:export")
    @Log(title = "任务表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjyTask yjyTask)
    {
        List<YjyTask> list = yjyTaskService.selectYjyTaskList(yjyTask);
        ExcelUtil<YjyTask> util = new ExcelUtil<YjyTask>(YjyTask.class);
        util.exportExcel(response, list, "任务表数据");
    }

    /**
     * 获取任务表详细信息
     */
    @RequiresPermissions("yjy:task:query")
    @GetMapping(value = "/{taskId}")
    public AjaxResult getInfo(@PathVariable("taskId") Long taskId)
    {
        return AjaxResult.success(yjyTaskService.selectYjyTaskByTaskId(taskId));
    }

    /**
     * 新增任务表
     */
    @RequiresPermissions("yjy:task:add")
    @Log(title = "任务表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjyTask yjyTask)
    {
        return toAjax(yjyTaskService.insertYjyTask(yjyTask));
    }

    /**
     * 修改任务表
     */
    @RequiresPermissions("yjy:task:edit")
    @Log(title = "任务表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjyTask yjyTask)
    {
        return toAjax(yjyTaskService.updateYjyTask(yjyTask));
    }

    /**
     * 删除任务表
     */
    @RequiresPermissions("yjy:task:remove")
    @Log(title = "任务表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{taskIds}")
    public AjaxResult remove(@PathVariable Long[] taskIds)
    {
        return toAjax(yjyTaskService.deleteYjyTaskByTaskIds(taskIds));
    }
}
