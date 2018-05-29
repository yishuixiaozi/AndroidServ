package com.hhit.controller;

import com.hhit.model.BackupBean;
import com.hhit.service.BackupService;
import com.hhit.utils.DbOperate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.hhit.utils.DbOperate.dbBackup;

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
            dbBackup("root", "123456", "localhost","3306", "androidsql", backupfilepath+"//"+backupfilename);
            backupService.addbackup(backupBean);
            System.out.println("------数据备份完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:querybackup";
    }

    @RequestMapping("/backup1")
    @ResponseBody
    public Map<String,Object> backup1(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<String, Object>();
        String remark=request.getParameter("remark");
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
        backupBean.setRemark(remark);
        try {
           dbBackup("root", "123456", "localhost","3306", "androidsql", backupfilepath+"//"+backupfilename);
            backupService.addbackup(backupBean);
            map.put("msg","success");
        } catch (Exception e) {
            map.put("msg","false");
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 这里是数据库恢复区域
     * @return
     */
    @RequestMapping("/recover")
    public String recover(HttpServletRequest request,HttpServletResponse response){
        String backupfilepath="F://Databeifen//";
        String backupfilename=request.getParameter("backupfilename");
        System.out.println("-----------backupfilename="+backupfilepath+backupfilename);
        try {
            DbOperate.dbRecover("root", "123456", "localhost","3306", "androidsql", backupfilepath+backupfilename);
            System.out.println("数据恢复完成！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:querybackup";
    }

   @RequestMapping("/testbeifen")
   public void testbeifen(HttpServletRequest request,HttpServletResponse response){

   }
}
