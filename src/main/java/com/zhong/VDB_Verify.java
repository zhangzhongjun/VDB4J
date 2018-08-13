package com.zhong;

import com.zhong.utils.SerializableElement;
import com.zhong.utils.SerializationDemonstrator;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.Pairing;
import it.unisa.dia.gas.plaf.jpbc.pairing.PairingFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author 张中俊
 **/
public class VDB_Verify {
    /**
     * 双线性对
     */
    final static public Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");
    static Element g;
    static Element Y;
    static ArrayList<Element> h;
    static ArrayList<Element> C_down;

    static {
        // 获得项目的文件夹resources下面的a.txt
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        File outputDir = new File(rootDir, "output");

        File outputFile = new File(outputDir, "g");
        g = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();

        outputFile = new File(outputDir, "yy");
        Y = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();

        outputFile = new File(outputDir, "h");
        ArrayList<SerializableElement> s_h = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        h = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_h.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            h.add(next.getElement());
        }

        outputFile = new File(outputDir, "c_down");
        ArrayList<SerializableElement> s_c_down = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        C_down = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_h.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            C_down.add(next.getElement());
        }
    }

    public static void main(String[] args) {
        if (args.length == 2) {
            int x = new Integer(args[0]);
            int q = new Integer(args[1]);
            Proof res = VDB_Query.Query(x, q);
            System.out.println(Verify(0, res));
        } else {
            Proof res = VDB_Query.Query(0, 5);
            System.out.println(Verify(0, res));
        }
    }

    public static boolean Verify(int x, Proof delta) {
        Element H_T = delta.getH_T();
        Element v_x = delta.getV_x();
        Element pi_x = delta.getPi_x();
        Element C_down_T_1 = delta.getC_down_T_1();
        Element C_up_T = delta.getC_up_T();
        int T = delta.getT();

        Element left1 = pairing.pairing(H_T.duplicate(), g);
        Element right1_left = VDB_Utils.H(C_down_T_1.duplicate(), C_up_T.duplicate(), T);
        Element right1 = pairing.pairing(right1_left.duplicate(), Y.duplicate());
        if (!left1.isEqual(right1)) {
            return false;
        }

         Element left2_temp = h.get(x).duplicate().powZn(v_x.duplicate());
         Element left2_temp_2 = H_T.duplicate().mul(left2_temp.duplicate());
         Element left2_left = C_down.get(T).duplicate().div(left2_temp_2.duplicate());
         Element left2 = pairing.pairing(left2_left.duplicate(), h.get(x).duplicate());

         Element right2 = pairing.pairing(pi_x.duplicate(), g.duplicate());

         return left2.isEqual(right2);
    }
}
