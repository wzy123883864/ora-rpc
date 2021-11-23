package com.ora.common.utils;

import junit.framework.TestCase;

public class ReflectionUtilsTest extends TestCase {

    public void testNewInstance() {
        TestClass testClass = ReflectionUtils.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    public void testGetPublicMethods() {
    }

    public void testInvoke() {
    }
}