import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    // Danh sách các số tương ứng với các chức năng chính trong menu
    static final List<Integer> mainFunctionIndiceList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


    /**
     * @param functionIndices Các số tương ứng với các chức năng trong menu
     * @return
     */
    public static int handleUserInput(ArrayList<Integer> functionIndices) {
        System.out.println(functionIndices.toString());
        int userOption = 0;
        do {
            try {
                System.out.print("Nhap lua chon cua ban: ");
                userOption = scanner.nextInt();
                if (functionIndices.contains(userOption) != true) {
                    System.out.println("Gia tri khong hop le. Vui long nhap cac so tuong ung voi cac chuc nang co trong menu.");
                }
            } catch (Exception e) {
                System.out.println("Gia tri khong hop le. Vui long nhap lai");
                scanner.nextLine();
            }
        } while (functionIndices.contains(userOption) != true);
        return userOption;
    }

    public static void main(String[] args) {
        /**
         * todos:
         *  - try catch user's input:
         *    + not an integer
         *    + not a supported function
         *    + viet 1 ham cho user nhap, try catch trong do
         */
        int userOption = 0;
        do {
            System.out.print("Nhap lua chon cua ban: ");
            ArrayList<Integer> mainFunctionIndices = new ArrayList<>();
            mainFunctionIndices.addAll(mainFunctionIndiceList);
            userOption = handleUserInput(mainFunctionIndices);
            System.out.println(userOption);

            switch (userOption) {
                case 1:
                    System.out.println("111");
//                    System.out.print("Nhap slang ban muon tim kiem: ");
//                    String userSearchedSlang = scanner.nextLine();
//                    System.out.println("Nghia tu ung voi slang " + userSearchedSlang + "la: definition");
                    break;
                case 2:
                    System.out.println("222");
//                    System.out.print("Nhap dinh nghia cua cac slang ban muon tim kiem: ");
//                    String userSearchedDefinition = scanner.nextLine();
//                    System.out.println("Cac slang tuong ung voi dinh nghia " + userSearchedDefinition + "la: slang1 slang2 slang3");
                    break;
                case 3:
                    System.out.println("333");
//                    System.out.println("Lich su tra cuu cua ban: ");
//                    System.out.println("slang1 - definition1\nslang2 - definition2\ndefinition1 - slang1 slang 2 slang3");
                    break;
                case 4:
                    System.out.println("444");
//                    System.out.print("Nhap slang can them: ");
//                    String addedSlang = scanner.nextLine();
//                    System.out.print("Nhap dinh nghia: ");
//                    String addedDefinition = scanner.nextLine();
//                    System.out.println("Slang " + addedSlang + " da ton tai, ban muon ghi de hay nhan ban ra 1 slang moi?\n1.Ghi de (overwrite)\n2.Nhan ban (duplicate)");
//                        int addedOption = scanner.nextInt();

//                        System.out.println("Cac slang tuong ung voi dinh nghia " + userSearchedDefinition + "la: slang1 slang2 slang3");
                    break;
                case 5:
                    System.out.println("555");
                    break;
                case 6:
                    System.out.println("666");
                    break;
                case 7:
                    System.out.println("777");
                    break;
                case 8:
                    System.out.println("888");
                    break;
                case 9:
                    System.out.println("999");
                    break;
                case 10:
                    System.out.println("101010");
                    break;
                case 0:
                    System.out.println("Xin cam on. Hen gap lai.");
                    break;

            }

        } while (userOption != 0);
    }
}