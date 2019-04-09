package com.hhit.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpmessageUtils {

    InetAddress addr = InetAddress.getLocalHost();
    /*构造器*/
    public IpmessageUtils() throws UnknownHostException {
    }
    /*
    * 自动获得本地电脑所在网段的IP
    * */
    public String getIphostAddress() throws Exception {
        return addr.getHostAddress();
    }
    /*获得hostname名*/
    public String getIphostName() throws Exception{
        return addr.getHostName();
    }
}
