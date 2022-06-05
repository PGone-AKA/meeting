package com.ruoyi.yjy.controller;

import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.yjy.domain.YjyRecord;
import com.ruoyi.yjy.domain.YjyStudent;
import com.ruoyi.yjy.domain.YjyTask;
import com.ruoyi.yjy.mapper.YjyTaskMapper;
import com.ruoyi.yjy.service.IYjyRecordService;
import com.ruoyi.yjy.service.IYjyStudentService;
import com.ruoyi.yjy.service.IYjyTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/sign")
@Controller
public class YjySignController extends BaseController {

    @Autowired
    private IYjyTaskService yjyTaskService;

    @Autowired
    private IYjyRecordService yjyRecordService;

    @Autowired
    private IYjyStudentService yjyStudentService;


    @GetMapping("check")
    public AjaxResult check(@RequestParam("tid") Long tid){
        YjyTask yjyTask  = yjyTaskService.selectYjyTaskByTaskId(tid);
        AjaxResult ajax = AjaxResult.error("当前不在签到时间内");
        if (yjyTask!=null&&"1".equals(yjyTask.getTaskStatus())){
            ajax = AjaxResult.success();
            ajax.put("task",yjyTask);
        }
        return ajax;
    }
    @PostMapping("/in")
    public AjaxResult signIn(@RequestBody YjyRecord yjyRecord){
        AjaxResult ajax = AjaxResult.error();
        if (yjyRecord!=null){
            String name = yjyRecord.getName();
            Long tid = yjyRecord.getRecordTid();
            if (tid!=null){
                YjyTask yjyTask  = yjyTaskService.selectYjyTaskByTaskId(tid);
                //判断该任务是否正在进行
                if ("1".equals(yjyTask.getTaskStatus())){
                    Long did = yjyTask.getTaskDid();
                    YjyStudent yjyStudent = new YjyStudent();
                    yjyStudent.setStuName(name);
                    List<YjyStudent> yjyStudents = yjyStudentService.selectYjyStudentList(yjyStudent);
                    if (yjyStudents.size()==0){
                        int result = yjyRecordService.insertYjyRecord(yjyRecord);
                        if (result==1){
                            ajax = AjaxResult.success("签到成功");
                        }
                    }else {
                        ajax.put("error","无需重复签到");
                    }
                }else {
                    ajax.put("error","当前不在签到时间内");
                }
            }

        }else {
            ajax.put("error","非法请求");
        }

        return  ajax;


    }


}
