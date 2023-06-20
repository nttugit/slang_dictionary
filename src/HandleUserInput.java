import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HandleUserInput {
    static Scanner scanner = new Scanner(System.in);

    /**
     * @param functionIndices Các số tương ứng với các chức năng trong menu
     * @return
     */
    public static int getUserInput(ArrayList<Integer> functionIndices) {
        int userOption = 999;
        do {
            try {
                System.out.print("Nhap lua chon: ");
                userOption = scanner.nextInt();
                scanner.nextLine();
                if (functionIndices.contains(userOption) != true) {
                    ColorPrinter.printlnRedText("Gia tri khong hop le. Vui long nhap cac so tuong ung voi cac chuc nang co trong menu.");
                }
            } catch (Exception e) {
                ColorPrinter.printlnRedText("Gia tri khong hop le. Vui long nhap lai");
                scanner.nextLine();
            }
        } while (functionIndices.contains(userOption) != true);
        return userOption;
    }

    public static String getUserStringInput(ArrayList<String> functionIndices) {
        String userOption = "";
        do {
            try {
                System.out.print("Nhap lua chon: ");
                userOption = scanner.nextLine();
                if (functionIndices.contains(userOption) != true) {
                    ColorPrinter.printlnRedText("Gia tri khong hop le. Vui long nhap lai.");
                }
            } catch (Exception e) {
                ColorPrinter.printlnRedText("Gia tri khong hop le. Vui long nhap lai");
            }
        } while (functionIndices.contains(userOption) != true);
        return userOption;
    }
}
