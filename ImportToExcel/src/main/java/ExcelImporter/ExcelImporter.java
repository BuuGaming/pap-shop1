package ExcelImporter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelImporter<T> {

    public abstract T parseRow(Row row);

    public List<T> importFromExcel(String filePath, String sheetName) {
        List<T> items = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' không tồn tại trong file Excel.");
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                T item = parseRow(row);
                if (item != null) {
                    items.add(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

}