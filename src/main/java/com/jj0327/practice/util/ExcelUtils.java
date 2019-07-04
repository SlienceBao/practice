package com.jj0327.practice.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jinbao
 * @date 2019/3/20 15:27
 * @description:
 */
public class ExcelUtils {

    public static void main(String[] args) {
        try {
            createJsonToExcel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createJsonToExcel() throws IOException {
        Set<String> keys = null;
        // 创建HSSFWorkbook对象
        HSSFWorkbook wb = new HSSFWorkbook();
        // 创建HSSFSheet对象
        HSSFSheet sheet = wb.createSheet("sheet0");

        FileReader reader = new FileReader("F:\\下载\\download-20190319.txt");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        int roleNo = 0;
        int rowNo = 0;
        Set<String> set = new HashSet<>();

        while ((str = br.readLine()) != null) {
            set.add(str);
        }
        for (String str1: set) {
            JSONObject jsonObject = JSONObject.parseObject(str1);
            // 创建HSSFRow对象
            HSSFRow row = sheet.createRow(roleNo++);
            // 创建HSSFCell对象
            if (keys == null) {
                //标题
                keys = jsonObject.keySet();
                for (String s : keys) {
                    HSSFCell cell = row.createCell(rowNo++);
                    cell.setCellValue(s);
                }
                rowNo = 0;
                row = sheet.createRow(roleNo++);
            }

            for (String s : keys) {
                HSSFCell cell = row.createCell(rowNo++);
                cell.setCellValue(jsonObject.getString(s));
            }
            rowNo = 0;
            System.out.println(rowNo);

        }

        br.close();
        reader.close();

        // 输出Excel文件
        FileOutputStream output = new FileOutputStream("F://target1.xls");
        wb.write(output);
        output.flush();
        output.close();

    }
}
