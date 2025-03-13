package ExcelImporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.Product;

import java.util.List;

public class ProductImporter extends ExcelImporter<Product> {

    @Override
    public Product parseRow(Row row) {
        String productId = getCellValueAsString(row.getCell(0));
        String name = getCellValueAsString(row.getCell(1));
        String category = getCellValueAsString(row.getCell(2));

        double importPrice = 0;
        String importPriceStr = getCellValueAsString(row.getCell(3));
        try {
            if (!importPriceStr.isEmpty()) {
                importPrice = Double.parseDouble(importPriceStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu importPrice tại hàng: " + row.getRowNum());
        }

        double salePrice = 0;
        String salePriceStr = getCellValueAsString(row.getCell(4));
        try {
            if (!salePriceStr.isEmpty()) {
                salePrice = Double.parseDouble(salePriceStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu salePrice tại hàng: " + row.getRowNum());
        }

        int stock = 0;
        String stockStr = getCellValueAsString(row.getCell(5));
        try {
            if (!stockStr.isEmpty()) {
                stock = (int) Double.parseDouble(stockStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu stock tại hàng: " + row.getRowNum());
        }

        String supplier = getCellValueAsString(row.getCell(6));

        if (productId.isEmpty() || name.isEmpty() || category.isEmpty()) {
            System.err.println("Bỏ qua dòng không hợp lệ tại hàng: " + row.getRowNum());
            return null;
        }

        return new Product(productId, name, category, (int) importPrice, (int) salePrice, stock, supplier);
    }


    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            default:
                return "";
        }
    }

    public static void main(String[] args) {
        String filePath = "template/San_pham.xlsx";
        String sheetName = "Sheet1";

        ProductImporter importer = new ProductImporter();
        List<Product> products = importer.importFromExcel(filePath, sheetName);

        products.forEach(System.out::println);
    }
}

