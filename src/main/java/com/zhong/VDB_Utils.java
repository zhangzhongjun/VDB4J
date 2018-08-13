package com.zhong;


import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.security.MessageDigest;

/**
 * @author 张中俊
 **/
public class VDB_Utils {
    /**
     * 双线性对 H:G1 x G1 x {0,1}^* -> G1 的哈希函数
     */
    final static public Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");

    public static Element H(Element x, Element y, int T) {
        try {
            String temp = x.toString() + y.toString() + T;
            return getElementInG1FromString(temp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Element getElementInG1FromString(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("utf-8"));
            return pairing.getG1().newElementFromHash(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Element getElementInZrFromString(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes("utf-8"));
            return pairing.getZr().newElementFromHash(bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
