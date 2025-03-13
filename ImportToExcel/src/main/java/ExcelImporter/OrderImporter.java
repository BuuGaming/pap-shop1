package ExcelImporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.example.Order;

import java.util.List;

public class OrderImporter extends ExcelImporter<Order> {

    @Override
    public Order parseRow(Row row) {
        String orderId = getCellValueAsString(row.getCell(0));
        String orderDate = getCellValueAsString(row.getCell(1));
        String customer = getCellValueAsString(row.getCell(2));
        String product = getCellValueAsString(row.getCell(3));

        int quantity = 0;
        String quantityStr = getCellValueAsString(row.getCell(4));
        try {
            if (!quantityStr.isEmpty()) {
                quantity = (int) Double.parseDouble(quantityStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu quantity tại hàng: " + row.getRowNum() + " - Giá trị: " + quantityStr);
        }

        double totalPrice = 0;
        String totalPriceStr = getCellValueAsString(row.getCell(5));
        try {
            if (!totalPriceStr.isEmpty()) {
                totalPrice = Double.parseDouble(totalPriceStr);
            }
        } catch (NumberFormatException e) {
            System.err.println("Lỗi chuyển đổi dữ liệu totalPrice tại hàng: " + row.getRowNum() + " - Giá trị: " + totalPriceStr);
        }

        String status = getCellValueAsString(row.getCell(6));

        return new Order(orderId, orderDate, customer, product, quantity, totalPrice, status);
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
        String filePath = "template/Don_hang.xlsx";
        String sheetName = "Sheet1";

        OrderImporter importer = new OrderImporter();
        List<Order> orders = importer.importFromExcel(filePath, sheetName);

        orders.forEach(System.out::println);
    }
}

