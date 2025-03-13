package ExcelImporter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.Customer;

import java.util.List;

public class CustomerImporter extends ExcelImporter<Customer> {

    @Override
    public Customer parseRow(Row row) {
        String customerId = getCellValueAsString(row.getCell(0));
        String name = getCellValueAsString(row.getCell(1));
        String phone = getCellValueAsString(row.getCell(2));
        String email = getCellValueAsString(row.getCell(3));
        String address = getCellValueAsString(row.getCell(4));

        if (customerId.isEmpty() || name.isEmpty() || phone.isEmpty()) {
            System.err.println("Bỏ qua dòng không hợp lệ tại hàng: " + row.getRowNum());
            return null;
        }

        return new Customer(customerId, name, phone, email, address);
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
        String filePath = "template/Khach_hang.xlsx";
        String sheetName = "Sheet1";

        CustomerImporter importer = new CustomerImporter();
        List<Customer> customers = importer.importFromExcel(filePath, sheetName);

        customers.forEach(System.out::println);
    }
}
