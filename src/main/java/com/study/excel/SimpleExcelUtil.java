package com.study.excel;

import com.study.free.vo.FreeBoardVO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class SimpleExcelUtil {

    private XSSFWorkbook wb;
    private Sheet sheet;
    private Class<?> clazz;
    private List<?> list;

    public SimpleExcelUtil(String sheetName, Class<?> clazz, List<?> list) throws IllegalAccessException {
        wb = new XSSFWorkbook();
        sheet = wb.createSheet(sheetName);
        this.clazz = clazz;
        this.list = list;
        excelHeader();
        excelBody();
    }

    private void excelHeader() {
        // reflection을 이용해서 모든 필드 이름 얻어내서 필드이름을 header로 ...
        // 나중에 @붙혀서 annotation에 설정한 필드 이름값 나게...
        Row row = sheet.createRow(0);
        Cell cell = null;
        Field[] fields = clazz.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
            if (annotation != null) {
                cell = row.createCell(i);
                cell.setCellValue(annotation.headerName());
            }
        }
    }

    private void excelBody() throws IllegalAccessException {
        Row row = null;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            row = sheet.createRow(i + 1);    // header에서 0번째 이미 만들었으니까...
            Cell cell = null;
            Field[] fields = clazz.getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                Field field = fields[j];
                field.setAccessible(true);  // 원래 필드는 private이죠... 접근 가능하게
                ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                if (annotation != null) {
                    Object o = field.get(obj);
                    if (o != null) {
                        cell = row.createCell(j);
                        cell.setCellValue(o.toString());
                    } else {
                        cell = row.createCell(j);
                        cell.setCellValue("null");
                    }
                }
            }
        }
    }

    public void writeOutPutStream(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        wb.write(response.getOutputStream());
    }
}
