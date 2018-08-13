package com.zhong.utils;

import it.unisa.dia.gas.jpbc.Element;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author 张中俊
 **/
public class MysqlUtils {
    private static Connection conn;                                      //连接
    private static PreparedStatement pres;                                      //PreparedStatement对象

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");              //加载驱动
            System.out.println("数据库加载成功!!!");
            //String url = "jdbc:mysql://10.170.32.244:3306/vdb";
            String url = "jdbc:mysql://127.0.0.1:3306/vdb?serverTimezone=UTC";
            String user = "root";
            String password = "root";

            conn = DriverManager.getConnection(url, user, password); //建立连接
            System.out.println("数据库连接成功!!!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 向数据库中的表TSets中插入数据
     *
     * @param i
     *         在第几行
     * @param j
     *         在第几列
     * @param Hij
     *         要插入的数据
     */
    public static void saveHij(int q, int i, int j, SerializableElement Hij) {
        String sql = "insert into SetupOutput_H_" + (q) + "(i,j,H_ij) values(?,?,?)";
        try {
            pres = conn.prepareStatement(sql);
            pres.setInt(1, i);
            pres.setInt(2, j);
            pres.setBytes(3, MyUtils.object2Bytes(Hij));
            pres.execute();

            if (pres != null)
                pres.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从数据库中读出存入的对象
     *
     * @return 得到第i行的H
     */
    public static ArrayList<Element> getHi(int q, int i) {
        ArrayList<Element> Hi = null;

        String sql = "select i,j,H_ij from SetupOutput_H_" + (q) + " WHERE i=(?) order by j";
        try {
            pres = conn.prepareStatement(sql);
            pres.setInt(1, i);

            Hi = new ArrayList<>();
            ResultSet res = pres.executeQuery();
            while (res.next()) {
                byte[] b_Hij = res.getBytes(3);
                Element Hij = ((SerializableElement) MyUtils.byte2Object(b_Hij)).getElement();
                Hi.add(Hij);
            }

            if (pres != null)
                pres.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Hi;
    }
}

