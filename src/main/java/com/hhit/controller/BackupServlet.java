package com.hhit.controller;

import com.hhit.model.BackupBean;
import com.hhit.service.BackupService;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * 数据库的备份
     * @param request
     * @param response
     */
    @RequestMapping("/backup")
    public String backup(HttpServletRequest request, HttpServletResponse response){
        String backupfilepath="F://Databeifen";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String backuptime=simpleDateFormat1.format(new Date());
        //System.out.println("--------当前时间"+backuptime);
        Calendar calendar=Calendar.getInstance();
        String currenttime=simpleDateFormat.format(calendar.getTime());
        String backupfilename=currenttime+".sql";
        String username=request.getSession().getAttribute("username").toString();
        backupBean.setBackupusername(username);
        backupBean.setBackuptime(backuptime);
        backupBean.setBackupfilename(backupfilename);
        backupBean.setBackupfilepath(backupfilepath);
        //System.out.println("username的值是------"+username);
        try {
            DbOperate.dbBackup("root", "123456", "localhost","3306", "androidsql", backupfilepath+"//"+backupfilename);
            backupService.addbackup(backupBean);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:querybackup";
    }
}
