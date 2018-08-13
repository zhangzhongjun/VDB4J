package com.zhong;

import com.zhong.utils.MysqlUtils;
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
public class VC_Open {
    /**
     * 双线性对
     */
    final static public Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");
    static File outputDir;
    static ArrayList<Element> Hx;
    static ArrayList<Element> v;
    static Element H_T;
    static ArrayList<Element> C_down;
    static ArrayList<Element> C_up;
    static int T;

    static {
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        outputDir = new File(rootDir, "output");

        File outputFile = new File(outputDir, "v");
        ArrayList<SerializableElement> s_v = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        v = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_v.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            v.add(next.getElement());
        }

        outputFile = new File(outputDir, "H_T");
        H_T = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();

        outputFile = new File(outputDir, "c_down");
        ArrayList<SerializableElement> s_c_down = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        C_down = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_c_down.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            C_down.add(next.getElement());
        }

        outputFile = new File(outputDir, "c_up");
        ArrayList<SerializableElement> s_c_up = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        C_up = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_c_up.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            C_up.add(next.getElement());
        }

        outputFile = new File(outputDir, "T");
        T = SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
    }

    public static Proof Open(int x, int q) {
        Hx = MysqlUtils.getHi(q, x);

        Element pi_x = pairing.getG1().newOneElement();

        for (int j = 0; j < q; j++) {
            if (x != j) {
                Element temp = Hx.get(j).duplicate().powZn(v.get(j).duplicate());
                pi_x = pi_x.duplicate().mul(temp.duplicate());
            }
        }

        Element v_x = v.get(x);

        Element C_down_T_1 = C_down.get(T - 1);
        Element C_up_T = C_up.get(T);

        return new Proof(v_x, pi_x, H_T, C_down_T_1, C_up_T, T);
    }
}
