package com.ruge.core;

import lombok.Data;

import java.util.UUID;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName User
 * @date 2020.08.12 16:10
 */
@Data
public class User {
    private String id = UUID.randomUUID().toString();
    private String name;
}
