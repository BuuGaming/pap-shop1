package ExcelImporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.ImportReceipt;

import java.util.List;

public class ImportReceiptImporter extends ExcelImporter<ImportReceipt> {

    @Override
    public ImportReceipt parseRow(Row row) {
        String importId = getCellValueAsString(row.getCell(0));
        String importDate = getCellValueAsString(row.getCell(1));
        String supplier = getCellValueAsString(row.getCell(2));
        String product = getCellValueAsString(row.getCell(3));

        int quantity = 0;
        String quantityStr = getCellValueAsString(row.getCell(4));
        try {
            if (!quantityStr.isEmpty()) {
                quantity = (int) Double.parseDouble(quantityStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu quantity tại hàng: " + row.getRowNum());
        }

        double importPrice = 0;
        String importPriceStr = getCellValueAsString(row.getCell(5));
        try {
            if (!importPriceStr.isEmpty()) {
                importPrice = Double.parseDouble(importPriceStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu importPrice tại hàng: " + row.getRowNum());
        }

        double totalPrice = quantity * importPrice;

        if (importId.isEmpty() || importDate.isEmpty() || supplier.isEmpty() || product.isEmpty()) {
            System.err.println("Bỏ qua dòng không hợp lệ tại hàng: " + row.getRowNum());
            return null;
        }

        return new ImportReceipt(importId, importDate, supplier, product, quantity, importPrice, totalPrice);
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
        String filePath = "template/Phieu_nhap.xlsx";
        String sheetName = "Sheet1";

        ImportReceiptImporter importer = new ImportReceiptImporter();
        List<ImportReceipt> receipts = importer.importFromExcel(filePath, sheetName);

        receipts.forEach(System.out::println);
    }
}
