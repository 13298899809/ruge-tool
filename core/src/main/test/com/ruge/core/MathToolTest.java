package com.ruge.core;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MathToolTest {

    @Test
    public void getNumberPercent() {
        Double numberPercent = MathTool.getNumberPercent(100L, 0.82);
        System.out.println(numberPercent);
    }
}