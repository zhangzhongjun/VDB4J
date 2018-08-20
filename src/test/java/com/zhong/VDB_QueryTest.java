package com.zhong;

import org.junit.Test;

/**
 * @author 张中俊
 **/
public class VDB_QueryTest {


    @Test
    public void computeQueryTime_500() {
        VDB_Query vdb_query = new VDB_Query();
        //下面新建VC_Open对象这句话要花费IO时间
        VC_Open vc_open = new VC_Open();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            vdb_query.Query_Time(0, 500, vc_open);
        }
        long end = System.currentTimeMillis();
        System.out.println("q=500 10次query所用时间为：" + (end - begin) + " ms");
    }

    @Test
    public void computeQueryTime_6549() {
        VDB_Query vdb_query = new VDB_Query();
        //下面新建VC_Open对象这句话要花费IO时间
        VC_Open vc_open = new VC_Open();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            vdb_query.Query_Time(0, 6549, vc_open);
        }
        long end = System.currentTimeMillis();
        System.out.println("q=6549 10次query所用时间为：" + (end - begin) + " ms");
    }
}
