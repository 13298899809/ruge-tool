package com.ruge.core.tmp;

import com.ruge.core.annotation.ExcelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author ruge.wu
 * @Description //TODO user$
 * @Date 2021/2/19 10:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @ExcelField(title = "编号22222222222222222222222222")
    private int id;
    @ExcelField(title = "姓名")
    private String name;
}
