package com.zhong;

import org.junit.Test;

import java.io.File;

/**
 * @author 张中俊
 **/
public class VC_KeyGenTest {
    @Test
    public void t1() {
        int q = 4;
        // 获得项目的文件夹resources下面的a.txt
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        File outputDir = new File(rootDir, "output");
        VC_KeyGen.deserializeOutput();
        System.out.println(VC_KeyGen.g.toString());
        System.out.println(VC_KeyGen.z.get(0).toString());
        System.out.println(VC_KeyGen.z.get(q - 1).toString());
        System.out.println(VC_KeyGen.h.get(0).toString());
        System.out.println(VC_KeyGen.h.get(q - 1).toString());
        System.out.println("===========================");
    }
}
