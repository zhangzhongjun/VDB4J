package com.zhong;

import it.unisa.dia.gas.jpbc.Element;
import org.junit.Test;

/**
 * @author 张中俊
 **/
public class VDB_UpdateTest {
    @Test
    public void computeUpdateTime() {
        String fileName = "+1xp7x9okHmjEIKT+n9zsg==.txt";
        Element v_x_new = VDB_Utils.getElementInZrFromString(fileName);

        long begin = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            // 更新的位置就是T
            VDB_Update.Update_time(0, v_x_new);
        }
        long end = System.currentTimeMillis();
        System.out.println("所用时间为：" + (end - begin) + " ms");
    }
}
