package com.zy.testzookeeper;

import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ZKUtils {
    private static ZooKeeper zk;
    private static Properties p ;
    private static DefaultWatch watch = new DefaultWatch();
    private  static CountDownLatch init = new CountDownLatch(1);
//    static {
//        try {
//            p.load(ZKUtils.class.getResourceAsStream("config.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static ZooKeeper getZK()  {
        try {

            String address = "192.168.111.128:2181,192.168.111.132:2181,192.168.111.133:2181/testConfig";
            zk = new ZooKeeper(address,1000,watch);
            watch.setCc(init);
            init.await();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return zk;
    }
}
