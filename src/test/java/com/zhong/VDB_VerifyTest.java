package com.zhong;

import org.junit.Test;

/**
 * @author 张中俊
 **/
public class VDB_VerifyTest {


    @Test
    public void computerVerifyTime_6549(){
        // 下面这两句话是先得到一个查询结果
        VDB_Query  vdb_query = new VDB_Query();
        Proof res = vdb_query.Query(0, 6549);

        //下面这句话有IO时间
        VDB_Verify vdb_verify = new VDB_Verify();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            vdb_verify.Verify(0, res);
        }
        long end = System.currentTimeMillis();
        System.out.println("q=6549 10次verify所用时间为：" + (end - begin) + " ms");
    }

    @Test
    public void computerVerifyTime_500(){
        VDB_Query  vdb_query = new VDB_Query();
        Proof res = vdb_query.Query(0, 500);
        //新建VDB_Verify将花费大量IO时间
        VDB_Verify vdb_verify = new VDB_Verify();

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            vdb_verify.Verify(0, res);
        }
        long end = System.currentTimeMillis();
        System.out.println("q=500 10次verify所用时间为：" + (end - begin) + " ms");
    }
}
