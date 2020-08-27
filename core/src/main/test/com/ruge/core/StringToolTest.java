package com.ruge.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringToolTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void isBlank() {
        System.out.println(StringTool.isBlank(null));
        System.out.println(StringTool.isBlank(""));
        System.out.println(StringTool.isBlank(" "));
    }

    @org.junit.Test
    public void isNotBlank() {
        System.out.println(StringTool.isNotBlank(null));
        System.out.println(StringTool.isNotBlank(""));
        System.out.println(StringTool.isNotBlank(" "));
    }

    @Test
    public void isAllLowerCase() {
        System.out.println(StringTool.isAllLowerCase("a1a"));
    }

    @Test
    public void isAllUpperCase() {
        System.out.println(StringTool.isAllUpperCase("A2A"));
    }
}