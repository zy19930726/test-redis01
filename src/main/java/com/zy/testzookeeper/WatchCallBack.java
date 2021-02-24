package com.zy.testzookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Mr.zhang
 * @create: 2021-02-24 19:10
 **/
public class WatchCallBack implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback {
   ZooKeeper zooKeeper;
   MyConf myConf;
    CountDownLatch cc = new CountDownLatch(1);
    public MyConf getMyConf() {
        return myConf;
    }

    public void setMyConf(MyConf myConf) {
        this.myConf = myConf;
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case None:
                break;
            case NodeCreated:
                zooKeeper.getData("/AppConf",this,this,"ctx");
                break;
            case NodeDeleted:
                break;
            case NodeDataChanged:
                zooKeeper.getData("/AppConf",this,this,"ctx");
                break;
            case NodeChildrenChanged:
                break;
        }

    }
        public void aWait(){
            zooKeeper.exists("/AppConf",this,this,"abc");
            try {
                cc.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    @Override
    public void processResult(int i, String s, Object o, byte[] bytes, Stat stat) {
            if(bytes != null ) {
                String str = new String(bytes);
                myConf.setConf(str);
                cc.countDown();
            }
    }

    @Override
    public void processResult(int i, String s, Object o, Stat stat) {
            if(stat != null) {
                zooKeeper.getData("/AppConf",this,this,"ctx");
            }
    }
}
