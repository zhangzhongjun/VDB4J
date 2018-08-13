package com.zhong;

import org.junit.Test;

/**
 * @author 张中俊
 **/
public class VDB_QueryTest {
    @Test
    public void computeQueryTime(){
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            VDB_Query.Query(0, 5);
        }
        long end = System.currentTimeMillis();
        System.out.println("所用时间为：" + (end - begin) + " ms");
    }
}
