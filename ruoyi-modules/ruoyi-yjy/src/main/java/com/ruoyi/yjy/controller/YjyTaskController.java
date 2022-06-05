package com.ruoyi.yjy.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.yjy.domain.YjyRecord;
import com.ruoyi.yjy.domain.YjyStudent;
import com.ruoyi.yjy.service.IYjyRecordService;
import com.ruoyi.yjy.service.IYjyStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private IYjyRecordService yjyRecordService;

    @Autowired
    private IYjyStudentService yjyStudentService;

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

    @GetMapping("show")
    public AjaxResult showInfo(@RequestParam("tid") Long tid){
        AjaxResult ajax = AjaxResult.success();
        YjyTask yjyTask = yjyTaskService.selectYjyTaskByTaskId(tid);
        YjyRecord yjyRecord = new YjyRecord();
        yjyRecord.setRecordTid(tid);
        List<YjyRecord> recordList = yjyRecordService.selectYjyRecordList(yjyRecord);
        if (yjyTask!=null){
            Long did = yjyTask.getTaskDid();
            List<YjyStudent> studentList = yjyStudentService.selectYjyStudentByDeptId(did);
            ajax.put("task",yjyTask);
            ajax.put("record",recordList);
            ajax.put("students",studentList);
        }

        return ajax;

    }

    @GetMapping("status")
    public AjaxResult setStatus(@RequestParam("tid")Long tid,@RequestParam("status") String status){
        YjyTask yjyTask = new YjyTask();
        yjyTask.setTaskId(tid);
        yjyTask.setTaskStatus(status);
        return toAjax(yjyTaskService.updateYjyTask(yjyTask));
    }


}
