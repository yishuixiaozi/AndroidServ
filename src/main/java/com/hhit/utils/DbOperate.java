package com.hhit.utils;

import java.io.*;

public class DbOperate {
    /**
     * 数据库表备份
     *
     * @throws Exception
     */
    public static void tableBackup(String dbUser,String dbPass,String dbHost,String dbPort,String dbName,String savePath, String tableName)
            throws Exception {

        Runtime runtime = Runtime.getRuntime();
        // -u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字
        Process process = runtime.exec("mysqldump -h " + dbHost + " -P "
                + dbPort + " -u " + dbUser + " -p" + dbPass + " " + dbName
                + " " + tableName);
        InputStream inputStream = process.getInputStream();// 得到输入流，写成.sql文件
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        StringBuffer sb = new StringBuffer();
        while ((s = br.readLine()) != null) {
            sb.append(s + "\r\n");
        }
        s = sb.toString();
        File file = new File(savePath);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
        br.close();
        reader.close();
        inputStream.close();
    }

    /**
     * 备份数据库
     *
     * @param savePath
     * @throws Exception
     */
    public static void dbBackup(String dbUser, String dbPass, String dbHost,
                                String dbPort, String dbName, String savePath) throws Exception {

        Runtime runtime = Runtime.getRuntime();
        // -u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字
        String path1="D:\\SoftwareInstall\\Mysql\\bin\\";
        Process process = runtime.exec(path1+"mysqldump -h " + dbHost + " -p"
                + dbPort + " -u" +dbUser + " -p" +dbPass + "  --set-charset=UTF8  " + dbName);
        // mysqldump -hlocalhost -p3306 -uroot -p123456 androidsql
        InputStream inputStream = process.getInputStream();// 得到输入流，写成.sql文件
        InputStreamReader reader = new InputStreamReader(inputStream,"utf8");
        BufferedReader br = new BufferedReader(reader);
        String s = null;
        StringBuffer sb = new StringBuffer();
        while ((s = br.readLine()) != null) {
            sb.append(s + "\r\n");
        }
        s = sb.toString();
        File file = new File(savePath);
        file.getParentFile().mkdirs();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
        br.close();
        reader.close();
        inputStream.close();
    }

    /**
     * 执行sql文件
     *
     * @param savePath
     * @throws Exception
     */
    public static void dbRecover(String dbUser, String dbPass, String dbHost,
                                 String dbPort, String dbName, String savePath) throws Exception {
        // 获取操作数据库的相关属性

        Runtime runtime = Runtime.getRuntime();
        String path1="D:\\SoftwareInstall\\Mysql\\bin\\";
        Process process = runtime.exec(path1+"mysql -h" + dbHost + " -p" + dbPort
                + " -u" + dbUser + " -p" + dbPass
                + " --default-character-set=utf8 " + dbName);
        //D:\SoftwareInstall\Mysql\bin mysql -hlocalhost -p3306 -uroot -p123456 androidsql
        OutputStream outputStream = process.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(savePath)));
        String str = null;
        StringBuffer sb = new StringBuffer();
        while ((str = br.readLine()) != null) {
            sb.append(str + "\r\n");
        }
        str = sb.toString();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,
                "utf-8");
        writer.write(str);
        writer.flush();
        outputStream.close();
        br.close();
        writer.close();
    }

    public static void main(String[] args) {
        try {
            dbBackup("root", "123456", "localhost","3306", "androidsql", "F://Databeifen//52918.sql");
            System.out.println("完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            dbRecover("root", "123456", "localhost","3306", "androidsql", "F://Databeifen//2018.sql");
            System.out.println("完成！");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }


}
