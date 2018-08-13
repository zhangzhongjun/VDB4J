package com.zhong;

import it.unisa.dia.gas.jpbc.Element;

/**
 * VDB的query函数
 *
 * @author 张中俊
 **/
public class VDB_Query {
    public static void main(String[] args) {
        Proof res;
        if (args.length == 2) {
            int x = new Integer(args[0]);
            int q = new Integer(args[1]);
            res = Query(x, q);
        } else {
            res = Query(0, 5);
        }
        System.out.println(res.getPi_x());
        System.out.println(res.getT());
        System.out.println(res.getV_x());
    }

    public static Proof Query(int x, int q) {
        return VC_Open.Open(x, q);
    }
}

class Proof {
    private Element v_x;
    private Element pi_x;
    private Element H_T;
    private Element C_down_T_1;
    private Element C_up_T;
    private int T;

    public Proof(Element v_x, Element pi_x, Element h_T, Element c_down_T_1, Element c_up_T, int t) {
        this.v_x = v_x;
        this.pi_x = pi_x;
        H_T = h_T;
        C_down_T_1 = c_down_T_1;
        C_up_T = c_up_T;
        T = t;
    }

    public Element getV_x() {
        return v_x;
    }

    public Element getPi_x() {
        return pi_x;
    }

    public Element getH_T() {
        return H_T;
    }

    public Element getC_down_T_1() {
        return C_down_T_1;
    }

    public Element getC_up_T() {
        return C_up_T;
    }

    public int getT() {
        return T;
    }
}
