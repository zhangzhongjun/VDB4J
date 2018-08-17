package com.zhong;

import org.junit.Test;

import java.util.Iterator;

/**
 * @author 张中俊
 **/
public class VDB_UtilsTest {
    @Test
    public void t1(){
        String str = "abiding:9:{'7a780da0-cf1b-469f-a500-de94a241c1ad.txt', 'bd311d49-3258-464a-8eae-a8374e0d5c4a.txt', 'a9875554-1f61-4e48-9709-ec0edd17d19e.txt', 'c69ae895-9f49-4ae5-9fc5-13b107c25c1c.txt', 'fc857357-0e37-44dd-8fd6-e5576677e07f.txt', 'eefe2512-9ef4-4e09-9227-7a56c55d4fa2.txt', 'ba5dfd2e-6ff0-4d7a-b48f-36ec82d43617.txt', 'f89f60e4-b8ed-45f0-aad4-a3c4c91c414c.txt', '4a0ac863-47cf-4b36-aeca-2856444aa3a2.txt'}";
        String keyword = str.split(":")[0];
        int num = new Integer(str.split(":")[1]);
        String fileName = str.split(":")[2].replace("{","").replace("}","");
        fileName = fileName.replaceAll(" ","").replaceAll("'","");

        System.out.println(VDB_Utils.getElementInZrFromString(fileName));
    }
}
