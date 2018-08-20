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
public class VC_Com {
    /**
     * 双线性对
     */
    final static public Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");
    static ArrayList<Element> h;
    static ArrayList<Element> v;
    static ArrayList<Element> c_up;
    static ArrayList<Element> c_down;
    static Element C_R;
    static Element H_T;

    static Element y;

    static File outputDir = null;

    static {
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        outputDir = new File(rootDir, "output");

        File outputFile = new File(outputDir, "y");
        y = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();


        outputFile = new File(outputDir, "h");
        ArrayList<SerializableElement> s_h = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        h = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_h.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            h.add(next.getElement());
        }
    }

    public static void main(String[] args) {
        Com(4);
        serializeOutput();
    }

    /**
     * 生成v c_up c_down C_R
     *
     * @param q vdb中的q，代表关键词的个数
     */
    public static void Com(int q) {
        v = new ArrayList<>();
        c_up = new ArrayList<>();
        c_down = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            v.add(pairing.getZr().newRandomElement());
        }
        C_R = pairing.getG1().newOneElement();
        for (int i = 0; i < q; i++) {
            Element temp = h.get(i).duplicate().powZn(v.get(i).duplicate());
            C_R = C_R.duplicate().mul(temp.duplicate());
        }

        c_up.add(C_R);
        H_T = VDB_Utils.H(C_R.duplicate(), C_R.duplicate(), 0).duplicate().powZn(y.duplicate());
        c_down.add(H_T.duplicate().mul(c_up.get(0).duplicate()));
    }


    /**
     * 对v c_up c_down C_R H_T进行序列化
     */
    public static void serializeOutput() {
        File outputFile = new File(outputDir, "c_r");
        SerializationDemonstrator.serialize(new SerializableElement(C_R), outputFile.getAbsolutePath());

        outputFile = new File(outputDir, "H_T");
        SerializationDemonstrator.serialize(new SerializableElement(H_T), outputFile.getAbsolutePath());

        ArrayList<SerializableElement> s_c_down = new ArrayList<>();
        for (Iterator<Element> elementIterator = c_down.iterator(); elementIterator.hasNext(); ) {
            Element next = elementIterator.next();
            s_c_down.add(new SerializableElement(next));
        }
        outputFile = new File(outputDir, "c_down");
        SerializationDemonstrator.serialize(s_c_down, outputFile.getAbsolutePath());

        ArrayList<SerializableElement> s_c_up = new ArrayList<>();
        for (Iterator<Element> elementIterator = c_up.iterator(); elementIterator.hasNext(); ) {
            Element next = elementIterator.next();
            s_c_up.add(new SerializableElement(next));
        }
        outputFile = new File(outputDir, "c_up");
        SerializationDemonstrator.serialize(s_c_up, outputFile.getAbsolutePath());

        ArrayList<SerializableElement> s_v = new ArrayList<>();
        for (Iterator<Element> elementIterator = v.iterator(); elementIterator.hasNext(); ) {
            Element next = elementIterator.next();
            s_v.add(new SerializableElement(next));
        }
        outputFile = new File(outputDir, "v");
        SerializationDemonstrator.serialize(s_v, outputFile.getAbsolutePath());
    }
}
