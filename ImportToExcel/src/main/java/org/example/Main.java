package org.example;

import ExcelImporter.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n===> CHƯƠNG TRÌNH QUẢN LÝ DỮ LIỆU <===");
            System.out.println("1. Nhập dữ liệu");
            System.out.println("2. Xuất dữ liệu");
            System.out.println("3. Thoát chương trình");
            System.out.print("Chọn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n---> Nhập dữ liệu <---");

                    CustomerImporter customerImporter = new CustomerImporter();
                    customerImporter.importFromExcel("template/Khach_hang.xlsx", "Sheet1");

                    OrderImporter orderImporter = new OrderImporter();
                    orderImporter.importFromExcel("template/Don_hang.xlsx", "Sheet1");

                    ImportReceiptImporter receiptImporter = new ImportReceiptImporter();
                    receiptImporter.importFromExcel("template/Nhap_hang.xlsx", "Sheet1");

                    ProductImporter productImporter = new ProductImporter();
                    productImporter.importFromExcel("template/San_pham.xlsx", "Sheet1");

                    RevenueImporter revenueImporter = new RevenueImporter();
                    revenueImporter.importFromExcel("template/Doanh_thu.xlsx", "Sheet1");

                    System.out.println("\n--- Nhập dữ liệu thành công! ---");
                    break;

                case 2:
                    System.out.println("\n---> Xuất dữ liệu trực tiếp <---");

                    System.out.println("\n<<< Danh sách khách hàng >>>");
                    new CustomerImporter().importFromExcel("template/Khach_hang.xlsx", "Sheet1")
                            .forEach(System.out::println);

                    System.out.println("\n<<< Danh sách đơn hàng >>>");
                    new OrderImporter().importFromExcel("template/Don_hang.xlsx", "Sheet1")
                            .forEach(System.out::println);

                    System.out.println("\n<<< Danh sách phiếu nhập >>>");
                    new ImportReceiptImporter().importFromExcel("template/Nhap_hang.xlsx", "Sheet1")
                            .forEach(System.out::println);

                    System.out.println("\n<<< Danh sách sản phẩm >>>");
                    new ProductImporter().importFromExcel("template/San_pham.xlsx", "Sheet1")
                            .forEach(System.out::println);

                    System.out.println("\n<<< Danh sách doanh thu >>>");
                    new RevenueImporter().importFromExcel("template/Doanh_thu.xlsx", "Sheet1")
                            .forEach(System.out::println);

                    System.out.println("\n--- Xuất dữ liệu thành công! ---");
                    break;

                case 3:
                    System.out.println("===> Đã thoát chương trình! <===");
                    exit = true;
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1 đến 3.");
            }
        }

        scanner.close();
    }
}
