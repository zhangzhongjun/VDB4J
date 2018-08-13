package com.zhong;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class TestDemo
        extends TestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.out.println("setUp , hashCode = " + hashCode());
    }

    public void testMethod1() {
        System.out.println("testMethod1 , hashCode = " + hashCode());
    }

    public void testMethod2() {
        System.out.println("testMethod2 , hashCode = " + hashCode());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.out.println("tearDown , hashCode = " + hashCode());
    }
}
