package com.zhong;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSuiteDemo extends TestSuite {

    public static Test suite() {
        //创建TestSuite对象
        TestSuite suite = new TestSuite();
        //为TestSuite添加一个测试用例集合，参数为：Classtest Class
        //通过参数可以知道，其实该参数就是TestCase的子类
        suite.addTestSuite(TestDemo.class);
        //创建具体的测试用例
        Test test = TestSuite.createTest(TestDemo.class, "testMethod1");
        //添加一个具体的测试用例
        suite.addTest(test);
        return suite;
    }

}