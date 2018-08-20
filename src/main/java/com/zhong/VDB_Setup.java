package com.zhong;

/**
 * @author 张中俊
 **/
public class VDB_Setup {
    public static void main(String[] args) {
        if (args.length == 2) {
            int q = new Integer(args[0]);
            int numHijRow = new Integer(args[1]);
            long begin = System.currentTimeMillis();
            Setup(q, numHijRow);
            long end = System.currentTimeMillis();
            System.out.println("q= " + q + " 时候setup花费的时间为： " + (end - begin) + " ms");
        } else {
            long begin = System.currentTimeMillis();
            Setup(6549, 2);
            long end = System.currentTimeMillis();
            System.out.println("q= " + 6549 + " 时候setup花费的时间为： " + (end - begin) + " ms");
        }
    }

    public static void Setup(int q, int numHijRow) {
        VC_KeyGen.KeyGen(q);
        VC_KeyGen.serializeOutput();
        VC_Com.Com(q);
        VC_Com.serializeOutput();

        VC_KeyGen.KeyGen2(numHijRow, q);
    }
}
