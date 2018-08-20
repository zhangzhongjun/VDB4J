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
public class VC_KeyGen {
    /**
     * 双线性对
     */
    final static public Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");
    // 获得项目的文件夹resources下面的a.txt
    static String rootDir = System.getProperty("user.dir").replace("\\", "/");
    static File outputDir = new File(rootDir, "output");
    static Element g;
    static ArrayList<Element> z;
    static ArrayList<Element> h;
    static Element y;
    static Element Y;
    static int T;

    public static void main(String[] args) {
        int q = 4;
        KeyGen(q);
        System.out.println(g.toString());
        System.out.println(z.get(0).toString());
        System.out.println(z.get(q - 1).toString());
        System.out.println(h.get(0).toString());
        System.out.println(h.get(q - 1).toString());
        System.out.println("===========================");
        serializeOutput();
        System.out.println(g.toString());
        System.out.println(z.get(0).toString());
        System.out.println(z.get(q - 1).toString());
        System.out.println(h.get(0).toString());
        System.out.println(h.get(q - 1).toString());
        System.out.println("===========================");
        deserializeOutput();
        System.out.println(g.toString());
        System.out.println(z.get(0).toString());
        System.out.println(z.get(q - 1).toString());
        System.out.println(h.get(0).toString());
        System.out.println(h.get(q - 1).toString());
        System.out.println("===========================");
    }

    /**
     * 生成z h y Y
     *
     * @param q vdb中的q，是指关键词的个数
     */
    public static void KeyGen(int q) {
        T = 0;

        g = pairing.getG1().newRandomElement();
        z = new ArrayList<>();
        h = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            Element temp = pairing.getZr().newRandomElement();
            z.add(temp);
            h.add(g.duplicate().powZn(temp.duplicate()));
        }

        y = pairing.getZr().newRandomElement();
        Y = g.duplicate().powZn(y.duplicate());
    }

    /**
     * 生成h H
     *
     * @param hangShu 要生成几行
     * @param q       vdb中的q，是指关键词的个数
     */
    public static void KeyGen2(int hangShu, int q) {
        for (int i = 0; i < hangShu; i++) {
            for (int j = 0; j < q; j++) {
                Element temp = z.get(i).duplicate().mul(z.get(j).duplicate());
                Element Hij = g.duplicate().powZn(temp.duplicate());
                MysqlUtils.saveHij(q, i, j, new SerializableElement(Hij));
            }
        }
    }

    /**
     * g z h y Y序列化
     */
    public static void serializeOutput() {
        File outputFile = new File(outputDir, "g");
        SerializationDemonstrator.serialize(new SerializableElement(g), outputFile.getAbsolutePath());

        ArrayList<SerializableElement> s_h = new ArrayList<>();
        for (Iterator<Element> elementIterator = h.iterator(); elementIterator.hasNext(); ) {
            Element next = elementIterator.next();
            s_h.add(new SerializableElement(next));
        }
        outputFile = new File(outputDir, "h");
        SerializationDemonstrator.serialize(s_h, outputFile.getAbsolutePath());


        outputFile = new File(outputDir, "T");
        SerializationDemonstrator.serialize(T, outputFile.getAbsolutePath());


        ArrayList<SerializableElement> s_z = new ArrayList<>();
        for (Iterator<Element> elementIterator = z.iterator(); elementIterator.hasNext(); ) {
            Element next = elementIterator.next();
            s_z.add(new SerializableElement(next));
        }
        outputFile = new File(outputDir, "z");
        SerializationDemonstrator.serialize(s_z, outputFile.getAbsolutePath());

        outputFile = new File(outputDir, "y");
        SerializationDemonstrator.serialize(new SerializableElement(y), outputFile.getAbsolutePath());

        outputFile = new File(outputDir, "yy");
        SerializationDemonstrator.serialize(new SerializableElement(Y), outputFile.getAbsolutePath());
    }

    /**
     * g z h反序列化
     */
    public static void deserializeOutput() {
        File outputFile = new File(outputDir, "g");
        g = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();

        z = new ArrayList<>();
        outputFile = new File(outputDir, "z");
        ArrayList<SerializableElement> s_z = SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        for (Iterator<SerializableElement> serializableElementIterator = s_z.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            z.add(next.getElement());
        }

        h = new ArrayList<>();
        outputFile = new File(outputDir, "h");
        ArrayList<SerializableElement> s_h = SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        for (Iterator<SerializableElement> serializableElementIterator = s_h.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            h.add(next.getElement());
        }
    }
}
