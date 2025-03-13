package ExcelImporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.example.Revenue;

import java.util.List;

public class RevenueImporter extends ExcelImporter<Revenue> {

    @Override
    public Revenue parseRow(Row row) {
        if (row == null || row.getCell(0) == null || row.getCell(1) == null || row.getCell(2) == null) {
            return null;
        }

        String date = getCellValueAsString(row.getCell(0));
        String orderId = getCellValueAsString(row.getCell(1));
        String customer = getCellValueAsString(row.getCell(2));

        if (date.isEmpty() || orderId.isEmpty() || customer.isEmpty()) {
            return null;
        }

        double totalPrice = 0;
        String totalPriceStr = getCellValueAsString(row.getCell(3));
        try {
            if (!totalPriceStr.isEmpty()) {
                totalPrice = Double.parseDouble(totalPriceStr.replace(".", "").replace(",", "."));
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu totalPrice tại hàng: " + (row.getRowNum() + 1));
        }

        String paymentMethod = getCellValueAsString(row.getCell(4));

        return new Revenue(date, orderId, customer, totalPrice, paymentMethod);
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
        String filePath = "template/Doanh_thu.xlsx";
        String sheetName = "Revenue";

        RevenueImporter importer = new RevenueImporter();
        List<Revenue> revenues = importer.importFromExcel(filePath, sheetName);

        revenues.forEach(System.out::println);
    }
}

