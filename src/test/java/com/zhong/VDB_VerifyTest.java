package com.zhong;

import org.junit.Test;

/**
 * @author 张中俊
 **/
public class VDB_VerifyTest {
    @Test
    public void computerVerifyTime(){
        Proof res = VDB_Query.Query(0, 5);
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            VDB_Verify.Verify(0, res);
        }
        long end = System.currentTimeMillis();
        System.out.println("所用时间为：" + (end - begin) + " ms");
    }
}
