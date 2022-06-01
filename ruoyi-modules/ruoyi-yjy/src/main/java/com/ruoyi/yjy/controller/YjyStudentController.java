package com.ruoyi.yjy.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.system.api.domain.SysUser;
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
import com.ruoyi.yjy.domain.YjyStudent;
import com.ruoyi.yjy.service.IYjyStudentService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 人员信息Controller
 * 
 * @author ruoyi
 * @date 2022-05-31
 */
@RestController
@RequestMapping("/student")
public class YjyStudentController extends BaseController
{
    @Autowired
    private IYjyStudentService yjyStudentService;

    /**
     * 查询人员信息列表
     */
    @RequiresPermissions("yjy:student:list")
    @GetMapping("/list")
    public TableDataInfo list(YjyStudent yjyStudent)
    {
        startPage();
        List<YjyStudent> list = yjyStudentService.selectYjyStudentList(yjyStudent);
        return getDataTable(list);
    }

    /**
     * 导出人员信息列表
     */
    @RequiresPermissions("yjy:student:export")
    @Log(title = "人员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, YjyStudent yjyStudent)
    {
        List<YjyStudent> list = yjyStudentService.selectYjyStudentList(yjyStudent);
        ExcelUtil<YjyStudent> util = new ExcelUtil<YjyStudent>(YjyStudent.class);
        util.exportExcel(response, list, "人员信息数据");
    }

    /**
     * 获取人员信息详细信息
     */
    @RequiresPermissions("yjy:student:query")
    @GetMapping(value = "/{stuId}")
    public AjaxResult getInfo(@PathVariable("stuId") Long stuId)
    {
        return AjaxResult.success(yjyStudentService.selectYjyStudentByStuId(stuId));
    }

    /**
     * 新增人员信息
     */
    @RequiresPermissions("yjy:student:add")
    @Log(title = "人员信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody YjyStudent yjyStudent)
    {
        return toAjax(yjyStudentService.insertYjyStudent(yjyStudent));
    }

    /**
     * 修改人员信息
     */
    @RequiresPermissions("yjy:student:edit")
    @Log(title = "人员信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody YjyStudent yjyStudent)
    {
        return toAjax(yjyStudentService.updateYjyStudent(yjyStudent));
    }

    /**
     * 删除人员信息
     */
    @RequiresPermissions("yjy:student:remove")
    @Log(title = "人员信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stuIds}")
    public AjaxResult remove(@PathVariable Long[] stuIds)
    {
        return toAjax(yjyStudentService.deleteYjyStudentByStuIds(stuIds));
    }

    @Log(title = "学生管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("yjy:student:import")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<YjyStudent> util = new ExcelUtil<YjyStudent>(YjyStudent.class);
        List<YjyStudent> userList = util.importExcel(file.getInputStream());
        String operName = SecurityUtils.getUsername();
        String message = yjyStudentService.importUStudent(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response) throws IOException
    {
        ExcelUtil<YjyStudent> util = new ExcelUtil<YjyStudent>(YjyStudent.class);
        util.importTemplateExcel(response, "用户数据");
    }
}
