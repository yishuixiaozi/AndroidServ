package com.hhit.controller;

import com.hhit.model.BackupBean;
import com.hhit.service.BackupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/BackupServlet")
public class BackupServlet {

    @Resource
    private BackupService backupService;
    BackupBean backupBean=new BackupBean();
    List<BackupBean> backupBeanList=new ArrayList<>();

    /**
     * 这里是数据备份内容
     * @param modelMap
     * @return
     */
    @RequestMapping("/querybackup")
    public String querybackup(ModelMap modelMap){
        backupBeanList=backupService.querybackup();
        modelMap.addAttribute("backuplist",backupBeanList);
        return "backupquery";
    }

}
