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

        VDB_Update vdb_update = new VDB_Update();

        // 更新的位置就是T
        vdb_update.Update(0, v_x_new);
        //序列化
        vdb_update.serializeOutput();


        long begin = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            // 更新的位置就是T
            vdb_update.Update_time(0, v_x_new);
        }
        long end = System.currentTimeMillis();
        System.out.println("10次update所用时间为：" + (end - begin) + " ms");
    }
}
