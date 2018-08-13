package com.zhong;

/**
 * @author 张中俊
 **/
public class VDB_Setup {
    public static void main(String[] args) {
        if (args.length == 2) {
            int q = new Integer(args[0]);
            int numHijRow = new Integer(args[1]);
            Setup(q, numHijRow);
        } else {
            Setup(5, 2);
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
