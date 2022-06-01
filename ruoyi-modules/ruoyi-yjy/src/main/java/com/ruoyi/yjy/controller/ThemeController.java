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
import com.ruoyi.yjy.domain.Theme;
import com.ruoyi.yjy.service.IThemeService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 会议注意Controller
 * 
 * @author ruoyi
 * @date 2022-05-28
 */
@RestController
@RequestMapping("/theme")
public class ThemeController extends BaseController
{
    @Autowired
    private IThemeService themeService;

    /**
     * 查询会议注意列表
     */
    @RequiresPermissions("yjy:theme:list")
    @GetMapping("/list")
    public TableDataInfo list(Theme theme)
    {
        startPage();
        List<Theme> list = themeService.selectThemeList(theme);
        return getDataTable(list);
    }

    /**
     * 导出会议注意列表
     */
    @RequiresPermissions("yjy:theme:export")
    @Log(title = "会议注意", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Theme theme)
    {
        List<Theme> list = themeService.selectThemeList(theme);
        ExcelUtil<Theme> util = new ExcelUtil<Theme>(Theme.class);
        util.exportExcel(response, list, "会议注意数据");
    }

    /**
     * 获取会议注意详细信息
     */
    @RequiresPermissions("yjy:theme:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(themeService.selectThemeById(id));
    }

    /**
     * 新增会议注意
     */
    @RequiresPermissions("yjy:theme:add")
    @Log(title = "会议注意", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Theme theme)
    {
        return toAjax(themeService.insertTheme(theme));
    }

    /**
     * 修改会议注意
     */
    @RequiresPermissions("yjy:theme:edit")
    @Log(title = "会议注意", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Theme theme)
    {
        return toAjax(themeService.updateTheme(theme));
    }

    /**
     * 删除会议注意
     */
    @RequiresPermissions("yjy:theme:remove")
    @Log(title = "会议注意", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(themeService.deleteThemeByIds(ids));
    }
}
