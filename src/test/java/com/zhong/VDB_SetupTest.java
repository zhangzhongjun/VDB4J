package com.zhong;

import org.junit.Test;

import static com.zhong.VDB_Setup.Setup;

/**
 * @author 张中俊
 **/
public class VDB_SetupTest {
    @Test
    public void computerSetupTime_500(){
        int q = 500;
        int numHijRow = 2;
        Setup(q, numHijRow);
    }


    @Test
    public void computerSetupTime_6549(){
        int q = 500;
        int numHijRow = 2;
        Setup(q, numHijRow);
    }
}
