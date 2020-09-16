package com.ruge.core;

import cn.hutool.core.text.csv.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author ruge.wu
 * @version 0.0.1
 * @ClassName CsvTool
 * @date 2020.09.15 10:06
 */
public class CsvTool {
    /**
     * -rw-rw-r--    1 1005     1001         2789 Sep 15  2020 Music_Data_Report_20200101.csv
     * -rw-rw-r--    1 1005     1001          662 Sep 11 09:50 ETCP_Data_Report_20200101.csv
     * -rw-rw-r--    1 1005     1001          517 Sep 15 01:46 Violation_Data_Report_20200101.csv
     * @param args
     */
    public static void main(String[] args) {
        CsvReader reader = CsvUtil.getReader();
        String file1 = "./Music_Data_Report_20200101.csv";
        String file2 = "./ETCP_Data_Report_20200101.csv";
        String file3 = "./Violation_Data_Report_20200101.csv";
        //根据特定的编码方式读取File的内容
        CsvData read = reader.read(new File(file1), Charset.forName("UTF-8"));
        List<CsvRow> rows = read.getRows();
        for (CsvRow row : rows) {
            System.out.println(row.getRawList());
        }

    }

}
