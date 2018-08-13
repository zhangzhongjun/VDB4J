package com.zhong.utils;

import java.io.*;

/**
 * @author 张中俊
 **/
public class MyUtils {
    /**
     * 将对象转化为byte数组
     *
     * @param o
     *         待转化的对象
     *
     * @return byte[]数组
     */
    public static byte[] object2Bytes(Object o) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(baos);
            out.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }


    /**
     * 将byte[]数组转化为对象
     *
     * @param bytes
     *         待转化的数组
     *
     * @return 转化为的对象
     */
    public static Object byte2Object(byte[] bytes) {
        ByteArrayInputStream bais;
        ObjectInputStream in = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            in = new ObjectInputStream(bais);

            return in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
