package com.hhit.controller;

import java.io.*;

public class MySQLDatebaseBackup {
    /**
     *  数据库备份
     *  hostIP :数据库所在的IP地址 localhost:8080
     *  userName:root
     *  password:密码123456
     *  savePath:保存路径
     *  fileName：导出的文件名
     *  databaseName：要导出的数据库名
     *
     */
    public static boolean exportDatabaseTool(String hostIP,String userName,String password,String savePath,
                                             String fileName,String databaseName)throws InterruptedException{
        File saveFile=new File(savePath);
        if (!saveFile.exists()){
            saveFile.mkdir();//不存在就创建文件夹
        }
        if (!savePath.endsWith(File.separator)){//File.separator  不同的平台下相当于  /或者\
            savePath=savePath+File.separator;
        }

        PrintWriter printWriter=null;
        BufferedReader bufferedReader=null;
        try{
            printWriter=new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath+fileName),"utf-8"));
            Process process=Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader=new InputStreamReader(process.getInputStream(),"utf8");
            bufferedReader=new BufferedReader(inputStreamReader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                printWriter.println(line);
            }
            printWriter.flush();
            if (process.waitFor()==0){
                return true;
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (printWriter!=null){
                    printWriter.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            if (exportDatabaseTool("100.100.104.125:3306", "root", "123456", "E:/backupDatabase", "2014-10-14.sql", "androidsql")){
                System.out.println("---数据库备份成功");
            }
            else{
                System.out.println("---数据库备份失败");
            }

        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
