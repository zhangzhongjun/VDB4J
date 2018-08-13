package com.zhong;

import com.zhong.utils.MysqlUtils;
import it.unisa.dia.gas.jpbc.Element;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author 张中俊
 **/
public class MysqlUtilsTest {
    @Test
    public void t1() {
        int q = 4;
        for (int i = 0; i < q; i++) {
            ArrayList<Element> Hi = MysqlUtils.getHi(q, i);
            System.out.println(Hi.get(0));
        }
    }
}
