import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    // Danh sách các số tương ứng với các chức năng chính trong menu
    static final List<Integer> mainFunctionIndiceList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);




    public static void main(String[] args) {
        /**
         * todos:
         *  - try catch user's input:
         *    + not an integer
         *    + not a supported function
         *    + viet 1 ham cho user nhap, try catch trong do
         */
        SlangDictionaryApp slangApp = new SlangDictionaryApp();
        int userOption = 0;
        do {
            System.out.println("\n===== SLANG DICTIONARY =====" +
                    "\n1. Tim kiem theo slang word" +
                    "\n2. Tim kiem theo tu khoa co trong definition" +
                    "\n3. Hien thi lich su slang words da tim kiem" +
                    "\n4. Them 1 slang word moi" +
                    "\n5. Cap nhat 1 slang word" +
                    "\n6. Xoa 1 slang word" +
                    "\n7. Reset danh sach slang word goc" +
                    "\n8. Random 1 slang word" +
                    "\n9. Chuong trinh do vui dua tren slang word" +
                    "\n10. Chuong trinh do vui dua tren definition" +
                    "\n0. Thoat chuong trinh");
            // Khởi tạo danh sách số đại diện các chức năng tương ứng
            ArrayList<Integer> mainFunctionIndices = new ArrayList<>();
            mainFunctionIndices.addAll(mainFunctionIndiceList);

//            System.out.print("Nhap lua chon cua ban: ");
            userOption = HandleUserInput.getUserInput(mainFunctionIndices);

            switch (userOption) {
                case 1:
                    slangApp.searchSlangWord();
                    break;
                case 2:
                    slangApp.searchSlangWordsByDefinition();
//
//                    System.out.println("Cac slang tuong ung voi dinh nghia " + userSearchedDefinition + "la: slang1 slang2 slang3");
                    break;
                case 3:
                    slangApp.showHistory();
//                    System.out.println("Lich su tra cuu cua ban: ");
//                    System.out.println("slang1 - definition1\nslang2 - definition2\ndefinition1 - slang1 slang 2 slang3");
                    break;
                case 4:
                    /**
                     * Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người
                     * dùng, confirm có overwrite hay duplicate ra 1 slang word mới.
                     */

//                    slangApp.add();
//                    System.out.print("Nhap slang can them: ");
//                    String addedSlang = scanner.nextLine();
//                    System.out.print("Nhap dinh nghia: ");
//                    String addedDefinition = scanner.nextLine();
//
//                    boolean addResult = slangDict.addSlang(addedSlang,Arrays.asList(addedDefinition));
//                    if(addResult){
//                        System.out.println("Da them slang "+ addedSlang + " thanh cong.");
//                    }
//                    System.out.println("Slang " + addedSlang + " da ton tai, ban muon ghi de hay nhan ban ra 1 slang moi?\n1.Ghi de (overwrite)\n2.Nhan ban (duplicate)");
//                        int addedOption = scanner.nextInt();

//                        System.out.println("Cac slang tuong ung voi dinh nghia " + userSearchedDefinition + "la: slang1 slang2 slang3");
                    break;
                case 5:
//                    slangDict.printSlang();
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
                default:
                    break;
            }

        } while (userOption != 0);
    }
}