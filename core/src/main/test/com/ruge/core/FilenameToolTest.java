package com.ruge.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class FilenameToolTest {

    @Test
    public void getExtension() {
        System.out.println(FilenameTool.getExtension("D:\\proc_ide\\Vscode.rar"));
        System.out.println(FilenameTool.getExtension("D:\\proc_ide\\Vscode.xls"));
    }

    @Test
    public void isExtension() {
        System.out.println(FilenameTool.isExtension("D:\\proc_ide\\Vscode.rar","rar"));
        System.out.println(FilenameTool.isExtension("D:\\proc_ide\\Vscode.rar","xls"));
    }

    @Test
    public void testIsExtension() {
        System.out.println(FilenameTool.isExtension("D:\\proc_ide\\Vscode.rar",FilenameConstant.EXCEL));
        System.out.println(FilenameTool.isExtension("D:\\proc_ide\\Vscode.xls",FilenameConstant.EXCEL));
    }
}