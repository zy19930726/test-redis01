package com.zy.testzookeeper;

/**
 * @author: Mr.zhang
 * @create: 2021-02-24 19:20
 **/
public class MyConf {
    private String conf;

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    @Override
    public String toString() {
        return "MyConf{" +
                "conf='" + conf + '\'' +
                '}';
    }
}
