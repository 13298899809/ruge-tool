package com.ruge.core.annotation;

import org.apache.poi.hssf.util.HSSFColor;

import java.lang.annotation.*;

/**
 * @Author ruge.wu
 * @Description //TODO excel 导入导出注解$
 * @Date 2021/2/19 10:21
 **/
@Documented
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    /**
     * @return 列名
     */
    String title();

    int type() default 0;

    int align() default 0;

    /**
     * @return 导出排序
     */
    int sort() default 0;

    String dictType() default "";

    Class<?> fieldType() default Class.class;

    int[] groups() default {};

    /**
     * @return 导出是是否忽略该字段, 默认不忽略
     */
    boolean ignore() default false;

    /**
     * @return 行高
     */
    int rowHeight() default 400;

    /**
     * @return 列宽
     */
    int colWidth() default 6000;

    /**
     * @return 字体颜色，默认黑色
     */
//    short color() default HSSFColor.BLACK.index;
}
