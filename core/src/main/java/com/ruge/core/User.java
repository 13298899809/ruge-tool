package com.ruge.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName User
 * @date 2020.08.12 16:10
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /*当前对象的id*/
    private String id;
    /*职位名称*/
    private String name;
    /*职位的父级id*/
    private String pid;

    private List<User> child;
}
