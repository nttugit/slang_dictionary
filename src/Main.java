import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        /**
         * todos:
         *  - try catch user's input:
         *    + not an integer
         *    + not a supported function
         *    + viet 1 ham cho user nhap, try catch trong do
         */
        String storageFileName = "src/slang.txt";

        SlangDictionaryApp slangApp = new SlangDictionaryApp(storageFileName);
        MiniGame miniGame = new MiniGame();

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
            // Danh sách các số tương ứng với các chức năng chính trong menu
            ArrayList<Integer> mainFunctionIndices = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

            // Cho user nhập lựa chọn và xử lý
            userOption = HandleUserInput.getUserInput(mainFunctionIndices);

            switch (userOption) {
                case 1:
                    slangApp.searchSlangWord();
                    break;
                case 2:
                    slangApp.searchSlangWordsByDefinition();
                    break;
                case 3:
                    slangApp.showHistory();
                    break;
                case 4:
                    slangApp.addSlangWord();
                    break;
                case 5:
                    slangApp.updateSlangWord();
                    break;
                case 6:
                    slangApp.deleteSlangWord();
                    break;
                case 7:
                    slangApp.resetToOriginalDictionary();
                    break;
                case 8:
                    slangApp.randomASlangWord();
                    break;
                case 9:
                    miniGame.startMiniGameOne();
                    break;
                case 10:
                    slangApp.printSlangWords();
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