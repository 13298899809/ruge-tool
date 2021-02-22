package com.ruge.core;

import com.ruge.core.annotation.ExcelField;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author ruge.wu
 * @Description //TODO excel工具类$
 * @Date 2021/2/1 16:09
 * <p>
 **/
@Slf4j
public class ExcelTool {

    public static void setBorderStyle(CellStyle style, short bg) {
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        /*设置边框*/
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        /*设置颜色*/
        style.setFillForegroundColor(bg);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        /*设置自动换行*/
        style.setWrapText(true);
    }

    public static void setFont(Workbook wb, int px, boolean bold, CellStyle style) {
        Font ztFont = wb.createFont();
        /*将字体大小设置为12px*/
        ztFont.setFontHeightInPoints((short) px);
        /*将“宋体”字体应用到当前单元格上*/
        ztFont.setFontName("宋体");
        /*加粗*/
        ztFont.setBold(bold);
        ztFont.setColor(IndexedColors.AUTOMATIC.getIndex());
        style.setFont(ztFont);
    }

    private static CellStyle contextCellStyle(Workbook wb) {
        /*表头样式*/
        CellStyle style = wb.createCellStyle();
        setBorderStyle(style, IndexedColors.WHITE.getIndex());
        setFont(wb, 10, false, style);
        return style;
    }

    private static CellStyle titleCellStyle(Workbook wb) {
        /*表头样式*/
        CellStyle style = wb.createCellStyle();
        setBorderStyle(style, IndexedColors.PALE_BLUE.getIndex());
        setFont(wb, 14, false, style);
        return style;
    }

    private static CellStyle headCellStyle(Workbook wb) {
        /*表头样式*/
        CellStyle style = wb.createCellStyle();
        setBorderStyle(style, IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        setFont(wb, 10, false, style);
        return style;
    }

    public static <T> void writeExcelToStream(List list, Class<T> clazz, String fileName) {
        OutputStream out = null;
        HSSFWorkbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(fileName);
        Row row = sheet.createRow(1);
        List titles = parseAnnotation(clazz);
        /*设置标题*/
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, titles.size() - 1);
        sheet.addMergedRegion(region);
        Cell titleCell = sheet.createRow(0).createCell(0);
        titleCell.setCellValue(fileName);
        titleCell.setCellStyle(titleCellStyle(workbook));
        /*设置表头*/
        for (int i = 0; i < titles.size(); i++) {
            Map<String, Object> map = (Map<String, Object>) titles.get(i);
            Cell cell = row.createCell(i);
            cell.setCellValue(String.valueOf(map.get("title")));
            cell.setCellStyle(headCellStyle(workbook));
        }
        /*写入正文*/
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                /*从第二行开始写入*/
                Row contextRow = sheet.createRow(2 + i);
                String beanToJson = JsonTool.getBeanToJson(list.get(i));
                Map<String, Object> map = JsonTool.getJsonToMap(beanToJson);
                int j = 0;
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    Cell contextRowCell = contextRow.createCell(j++);
                    contextRowCell.setCellValue(String.valueOf(entry.getValue()));
                    contextRowCell.setCellStyle(contextCellStyle(workbook));
                }
            }
        }


        /*设置自动列宽*/
        for (int i = 0; i < titles.size(); i++) {
            sheet.autoSizeColumn(i);
        }

        try {
            OutputStream fileOut = new FileOutputStream("D:\\google\\" + fileName + ".xlsx");
            workbook.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static <T> List<T> parseAnnotation(Class<T> clazz) {
        List maps = new ArrayList<>();
        Field[] fs = clazz.getDeclaredFields();
        for (Field f : fs) {
            //获取字段上加的@Excel注解
            ExcelField ef = f.getAnnotation(ExcelField.class);
            Map<String, Object> objToMap = JsonTool.getObjToMap(ef);
            objToMap.put("key", f.getName());
            maps.add(objToMap);
        }
        return maps;
    }

    public static <T> List<T> readExcelToListBean(File excelFile, Class<T> clazz) {
        List<T> result = new ArrayList();
        List parseAnnotation = parseAnnotation(clazz);
        if (parseAnnotation.isEmpty()) {
            log.error("返回的class没有注解标识:{}", clazz);
        } else {
            Map<String, String> mapping = new HashMap<>();
            for (Object ignored : parseAnnotation) {
                Map<String, Object> map = (Map<String, Object>) ignored;
                mapping.put(String.valueOf(map.get("title")), String.valueOf(map.get("key")));
            }
            List<Map<String, Object>> maps = readExcelToListMap(excelFile, 1, 0, 0, mapping);
            String beanToJson = JsonTool.getBeanToJson(maps);
            result = JsonTool.getJsonToListBean(beanToJson, clazz);
        }
        return result;
    }

    public static List<Map<String, Object>> readExcelToListMap(File excelFile) {
        return readExcelToListMap(excelFile, 1, 0, 0, null);
    }

    public static List<Map<String, Object>> readExcelToListMap(File excelFile, Map<String, String> mapping) {
        return readExcelToListMap(excelFile, 1, 0, 0, mapping);
    }

    public static List<Map<String, Object>> readExcelToListMap(File excelFile, int startRow, int startCol, int sheetNum) {
        return readExcelToListMap(excelFile, startRow, startCol, sheetNum, null);
    }

    public static List<Map<String, Object>> readExcelToListMap(File excelFile, int startRow, int startCol, int sheetNum, Map<String, String> mapping) {
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(excelFile);
            Workbook wb = new HSSFWorkbook();
            if (excelFile.getName().endsWith("xls")) {
                POIFSFileSystem fs = new POIFSFileSystem(inputStream);
                wb = new HSSFWorkbook(fs.getRoot(), true);
            } else if (excelFile.getName().endsWith("xlsx")) {
                wb = new XSSFWorkbook(inputStream);
            }
            list = readExcel(wb, startRow, startCol, sheetNum, mapping);

        } catch (FileNotFoundException e) {
            log.error("文件路径不存在:{}", excelFile);
        } catch (IOException e) {
            log.error("IOException:{}", excelFile);
            e.printStackTrace();
        }
        return list;
    }

    /**
     * @param wb       {@link Workbook}
     * @param startRow 开始的行数
     * @param startCol 开始的列数
     * @param sheetNum 读取的sheet页
     * @param mapping  map的映射
     *                 <dependency>
     *                 <groupId>org.apache.poi</groupId>
     *                 <artifactId>poi</artifactId>
     *                 <version>${poi.version}</version>
     *                 </dependency>
     */
    private static List<Map<String, Object>> readExcel(Workbook wb, int startRow, int startCol, int sheetNum, Map<String, String> mapping) {
        Sheet sheet = wb.getSheetAt(sheetNum);  //sheet 从0开始
        int rowNum = sheet.getLastRowNum() + 1; //取得最后一行的行号
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        //行循环开始
        List<String> title = new ArrayList<>();
        Row titleRow = sheet.getRow(startRow - 1);
        //每行的最后一个单元格位置
        int titleCellNum = titleRow.getLastCellNum();
        for (int j = startCol; j < titleCellNum; j++) {
            Cell cell = titleRow.getCell(Short.parseShort(j + ""));
            String cellValue = getCellValue(cell);
            if (null != mapping) {
                if (mapping.containsKey(cellValue)) {
                    title.add(mapping.get(cellValue));
                } else {
                    title.add(cellValue);
                }
            } else {
                title.add(cellValue);
            }
        }

        for (int i = startRow; i < rowNum; i++) {
            Map<String, Object> map = new HashMap<>();
            Row row = sheet.getRow(i);
            //每行的最后一个单元格位置
            int cellNum = row.getLastCellNum();
            for (int j = startCol; j < cellNum; j++) {
                Cell cell = row.getCell(Short.parseShort(j + ""));
                String cellValue = getCellValue(cell);
                map.put(title.get(j), cellValue);
            }
            listMap.add(map);
        }
        return listMap;
    }


    private static String getCellValue(Cell cell) {
        String cellValue = null;
        if (null != cell) {
            // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
            switch (cell.getCellType()) {
                case NUMERIC:
                    cellValue = String.valueOf((int) cell.getNumericCellValue());
                    break;
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case BLANK:
                    cellValue = "";
                    break;
                case BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case ERROR:
                    cellValue = String.valueOf(cell.getErrorCellValue());
                    break;
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
