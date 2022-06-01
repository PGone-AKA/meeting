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
import com.ruoyi.yjy.domain.YjyRecord;
import com.ruoyi.yjy.service.IYjyRecordService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 签到记录Controller
 * 
 * @author yjy
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/record")
public class YjyRecordController extends BaseController
{
    @Autowired
    private IYjyRecordService yjyRecordService;

    /**
     * 查询签到记录列表
     */
    @RequiresPermissions("yjy:record:list")
    @GetMapping("/list")
    public TableDataInfo list(YjyRecord yjyRecord)
    {
        startPage();
        List<YjyRecord> list = yjyRecordService.selectYjyRecordList(yjyRecord);
        return getDataTable(list);
    }

    /**
     * 导出签到记录列表
     */
    @RequiresPermissions("yjy:record:export")
    @Log(title = "签到记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjyRecord yjyRecord)
    {
        List<YjyRecord> list = yjyRecordService.selectYjyRecordList(yjyRecord);
        ExcelUtil<YjyRecord> util = new ExcelUtil<YjyRecord>(YjyRecord.class);
        util.exportExcel(response, list, "签到记录数据");
    }

    /**
     * 获取签到记录详细信息
     */
    @RequiresPermissions("yjy:record:query")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return AjaxResult.success(yjyRecordService.selectYjyRecordByRecordId(recordId));
    }

    /**
     * 新增签到记录
     */
    @RequiresPermissions("yjy:record:add")
    @Log(title = "签到记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjyRecord yjyRecord)
    {
        return toAjax(yjyRecordService.insertYjyRecord(yjyRecord));
    }

    /**
     * 修改签到记录
     */
    @RequiresPermissions("yjy:record:edit")
    @Log(title = "签到记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjyRecord yjyRecord)
    {
        return toAjax(yjyRecordService.updateYjyRecord(yjyRecord));
    }

    /**
     * 删除签到记录
     */
    @RequiresPermissions("yjy:record:remove")
    @Log(title = "签到记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(yjyRecordService.deleteYjyRecordByRecordIds(recordIds));
    }
}
