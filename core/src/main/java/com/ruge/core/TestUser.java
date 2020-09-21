package com.ruge.core;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author 嘿丷如歌
 * @version V1.0
 * @Description:
 * @date 2020/9/14 21:51
 */
public class TestUser {
    public static void main(String[] args) {
// s

        User user = User.builder().id("1").name("董事长").pid("0").child(
                Arrays.asList(
                        User.builder().id("2").name("副总经理").pid("1").build(),
                        User.builder().id("3").name("行政总监").pid("1").build()
                )
        ).build();
        System.out.println(user);
    }
}
