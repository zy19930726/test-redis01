package com.zy.testzookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestConfig {
    ZooKeeper zk;
    @Before
    public void conn(){
        zk = ZKUtils.getZK();
    }
    @After
    public void close(){
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void getConf(){
        WatchCallBack watchCallBack = new WatchCallBack();
        watchCallBack.setZooKeeper(zk);
        MyConf myConf = new MyConf();
        watchCallBack.setMyConf(myConf);

        watchCallBack.aWait();
        while (true){
            System.out.println(myConf.getConf().toString());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
