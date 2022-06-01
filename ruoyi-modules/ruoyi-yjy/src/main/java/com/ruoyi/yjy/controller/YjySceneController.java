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
import com.ruoyi.yjy.domain.YjyScene;
import com.ruoyi.yjy.service.IYjySceneService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 场景签到Controller
 * 
 * @author yjy
 * @date 2022-06-01
 */
@RestController
@RequestMapping("/scene")
public class YjySceneController extends BaseController
{
    @Autowired
    private IYjySceneService yjySceneService;

    /**
     * 查询场景签到列表
     */
    @RequiresPermissions("yjy:scene:list")
    @GetMapping("/list")
    public TableDataInfo list(YjyScene yjyScene)
    {
        startPage();
        List<YjyScene> list = yjySceneService.selectYjySceneList(yjyScene);
        return getDataTable(list);
    }

    /**
     * 导出场景签到列表
     */
    @RequiresPermissions("yjy:scene:export")
    @Log(title = "场景签到", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjyScene yjyScene)
    {
        List<YjyScene> list = yjySceneService.selectYjySceneList(yjyScene);
        ExcelUtil<YjyScene> util = new ExcelUtil<YjyScene>(YjyScene.class);
        util.exportExcel(response, list, "场景签到数据");
    }

    /**
     * 获取场景签到详细信息
     */
    @RequiresPermissions("yjy:scene:query")
    @GetMapping(value = "/{sceneId}")
    public AjaxResult getInfo(@PathVariable("sceneId") Long sceneId)
    {
        return AjaxResult.success(yjySceneService.selectYjySceneBySceneId(sceneId));
    }

    /**
     * 新增场景签到
     */
    @RequiresPermissions("yjy:scene:add")
    @Log(title = "场景签到", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjyScene yjyScene)
    {
        return toAjax(yjySceneService.insertYjyScene(yjyScene));
    }

    /**
     * 修改场景签到
     */
    @RequiresPermissions("yjy:scene:edit")
    @Log(title = "场景签到", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjyScene yjyScene)
    {
        return toAjax(yjySceneService.updateYjyScene(yjyScene));
    }

    /**
     * 删除场景签到
     */
    @RequiresPermissions("yjy:scene:remove")
    @Log(title = "场景签到", businessType = BusinessType.DELETE)
	@DeleteMapping("/{sceneIds}")
    public AjaxResult remove(@PathVariable Long[] sceneIds)
    {
        return toAjax(yjySceneService.deleteYjySceneBySceneIds(sceneIds));
    }
}
