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
public class VDB_Update {
    /**
     * 双线性对
     */
    final public static Pairing pairing = PairingFactory.getPairing("params/curves/a.properties");
    static File outputDir;
    static int T;
    static Element H_T;
    static ArrayList<Element> c_down;
    static ArrayList<Element> c_up;
    static ArrayList<Element> h;
    static ArrayList<Element> v;
    static Element y;

    public VDB_Update() {
        String rootDir = System.getProperty("user.dir").replace("\\", "/");
        outputDir = new File(rootDir, "output");

        File outputFile = new File(outputDir, "h");
        ArrayList<SerializableElement> s_h = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        h = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_h.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            h.add(next.getElement());
        }

        outputFile = new File(outputDir, "v");
        ArrayList<SerializableElement> s_v = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        v = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_v.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            v.add(next.getElement());
        }

        outputFile = new File(outputDir, "c_down");
        ArrayList<SerializableElement> s_c_down = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        c_down = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_c_down.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            c_down.add(next.getElement());
        }

        outputFile = new File(outputDir, "c_up");
        ArrayList<SerializableElement> s_c_up = (ArrayList<SerializableElement>) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());
        c_up = new ArrayList<>();
        for (Iterator<SerializableElement> serializableElementIterator = s_c_up.iterator(); serializableElementIterator.hasNext(); ) {
            SerializableElement next = serializableElementIterator.next();
            c_up.add(next.getElement());
        }

        outputFile = new File(outputDir, "H_T");
        H_T = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();

        outputFile = new File(outputDir, "T");
        T = SerializationDemonstrator.deserialize(outputFile.getAbsolutePath());

        outputFile = new File(outputDir, "y");
        y = ((SerializableElement) SerializationDemonstrator.deserialize(outputFile.getAbsolutePath())).getElement();
    }

    public static void main(String[] args) {
        String fileName = "+1xp7x9okHmjEIKT+n9zsg==.txt";
        Element v_x_new = VDB_Utils.getElementInZrFromString(fileName);

        System.out.println(T);
        System.out.println(v_x_new);

        VDB_Update vdb_update = new VDB_Update();

        // 更新的位置就是T
        vdb_update.Update(0, v_x_new);

        vdb_update.serializeOutput();
    }

    /**
     * 更新操作
     *
     * @param x       要更新的元素的索引
     * @param v_x_new 要更新的元素
     */
    public void Update(int x, Element v_x_new) {
        T = T + 1;
        Element C_up_T = (c_down.get(T - 1).duplicate().div(H_T.duplicate())).mul(h.get(x).duplicate().powZn(v_x_new.duplicate().sub(v.get(x).duplicate())));
        c_up.add(C_up_T);

        H_T = VDB_Utils.H(c_down.get(T - 1).duplicate(), c_up.get(T).duplicate(), T).powZn(y.duplicate());
        Element C_down_T = H_T.duplicate().mul(C_up_T.duplicate());
        c_down.add(C_down_T.duplicate());

        v.set(x, v_x_new.duplicate());
    }

    /**
     * 序列化输出
     * <p>
     * T H_T c_down c_up v
     */
    public void serializeOutput() {
        File outputFile = new File(outputDir, "T");
        SerializationDemonstrator.serialize(T, outputFile.getAbsolutePath());

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

    /**
     * 为了测试时间写的函数
     *
     * @param x       要更新的位置
     * @param v_x_new 要更新的元素
     */
    public void Update_time(int x, Element v_x_new) {
        T = T + 1;
        Element C_up_T = c_down.get(T - 1).duplicate().div(H_T.duplicate()).mul(h.get(x).duplicate().powZn(v_x_new.duplicate().sub(v.get(x).duplicate())));
        c_up.add(C_up_T);

        H_T = VDB_Utils.H(c_down.get(T - 1).duplicate(), c_up.get(T).duplicate(), T).powZn(y);
        Element C_down_T = H_T.duplicate().mul(C_up_T);
        T = T - 1;
    }
}
