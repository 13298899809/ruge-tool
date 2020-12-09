package com.ruge.core;

import org.junit.Test;


public class IdToolTest {



    @Test
    public void uuid() {
        System.out.println(IdTool.uuid());
    }

    @Test
    public void orderId() {
        System.out.println(IdTool.orderId("AF"));
    }
}