import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Test {
    static Scanner scanner = new Scanner(System.in);

    /**
     * Làm sao để catch lỗi, và trả ra giá trị như y
     */
    public static int handleUserInput(ArrayList<Integer> functionIndices) {
        System.out.println(Arrays.toString(functionIndices.toArray()));
        int userOption = 0;
        do {
            try {
                System.out.print("Nhap lua chon cua ban: ");
                userOption = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Gia tri khong hop le. Vui long nhap lai");
                scanner.nextLine();
            }
        } while (functionIndices.contains(userOption) != true);
        return userOption;
    }

    public static void main(String[] args) {
        ArrayList<Integer> functionIndices = new ArrayList<>();
        functionIndices.add(1);
        functionIndices.add(2);
        int userOption = handleUserInput(functionIndices);
        System.out.println("User option is: " + userOption);
    }
}
