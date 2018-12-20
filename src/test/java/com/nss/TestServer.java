package com.nss;

import com.nss.main.NssServerStarter;

/**
 * 测试服务
 */
public class TestServer {

    public static void main(String[] args) {
        NssServerStarter nssServerStarter = new NssServerStarter();
        nssServerStarter.start(10057);
    }
}
