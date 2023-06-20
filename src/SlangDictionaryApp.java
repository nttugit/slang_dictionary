import java.util.*;

public class SlangDictionaryApp {

    static Scanner scanner = new Scanner(System.in);
    private SlangDictionary slangDict;

    public SlangDictionaryApp(String storageFileName) {
        // todo: thêm đường dẫn file khởi tạo ở đây
        this.slangDict = new SlangDictionary(storageFileName);
    }

    public void printSlangWords() {
        for (Map.Entry<String, ArrayList<String>> entry : this.slangDict.getData().entrySet()) {
            ColorPrinter.printlnGreenText(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void printListInOrder(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            ColorPrinter.printlnGreenText((i + 1) + ". " + list.get(i));
        }
    }

    // 1. Tìm kiếm theo slang word.
    public void searchSlangWord() {
        ColorPrinter.printGreenText("Nhap slang ban muon tim kiem (co phan biet chu hoa va chu thuong): ");
        String slang = scanner.nextLine();

        List<String> result = slangDict.search(slang);
        if (result != null) {
            ColorPrinter.printlnGreenText("Cac dinh nghia cho slang word \"" + slang + "\":");
            printListInOrder(result);
            slangDict.writeSlangToHistoryFile(slang);
        } else {
            ColorPrinter.printlnYellowText("Khong tim thay dinh nghia nao ung voi slang word: \"" + slang + "\".");
        }
    }

    // 2. Tìm kiếm theo definition, hiển thị ra tất cả các slang words mà trong defintion có chứa keyword gõ vào.
    public void searchSlangWordsByDefinition() {
        ColorPrinter.printGreenText("Nhap tu khoa thuoc dinh nghia cua slang: ");
        String keyword = scanner.nextLine();
        List<String> result = slangDict.searchSlangByDefinition(keyword);
        if (result.size() > 0) {
            ColorPrinter.printlnGreenText("Cac slang trung voi tu khoa \"" + keyword + "\" la:");
            printListInOrder(result);
        } else {
            ColorPrinter.printlnYellowText("Khong co slang nao phu hop voi tu khoa \"" + keyword + "\"");
        }
    }

    // 3. Hiển thị history, danh sách các slang word đã tìm kiếm.
    public void showHistory() {
        List<String> history = slangDict.getHistory();
        Collections.reverse(history);
        if (history.size() > 0) {
            ColorPrinter.printlnGreenText("Cac slang ban da tim kiem:");
            printListInOrder(history);
        } else {
            ColorPrinter.printlnGreenText("Lich su rong. Ban chua tim kiem slang nao.");
        }
    }

    // 4. Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người dùng, confirm có overwrite hay duplicate ra 1 slang word mới.
    public void addSlangWord() {
        try {
            ColorPrinter.printGreenText("Nhap slang word can them: ");
            String addedSlang = scanner.nextLine();
            ColorPrinter.printGreenText("Nhap dinh nghia: ");
            String addedDefinition = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(addedSlang);

            ArrayList<Integer> addedSlangFunctionIndices = new ArrayList<>(Arrays.asList(0, 1, 2));
            ArrayList<String> newDefinitions = new ArrayList<>();

            if (existingSlang) {
                ColorPrinter.printlnGreenText("Slang word \"" + addedSlang + "\" da ton tai. " +
                        "\n  + Bam phim 1 de ghi de (overwrite)." +
                        "\n  + Bam phim 2 de tao them mot dinh nghia moi cho slang (duplicate)" +
                        "\n  + Bam phim 0 de huy thao tac");
                int userOption = HandleUserInput.getUserInput(addedSlangFunctionIndices);
                switch (userOption) {
                    case 0:
                        break;
                    case 1:
                        newDefinitions.addAll(Arrays.asList(addedDefinition));
                        this.slangDict.add(addedSlang, newDefinitions);
                        this.slangDict.writeSlangDictionaryIntoFile();
                        System.out.println("Them slang word \""+addedSlang + "\" thanh cong.");
                        break;
                    case 2:
                        newDefinitions = this.slangDict.search(addedSlang);
                        newDefinitions.add(addedDefinition);
                        this.slangDict.add(addedSlang, newDefinitions);
                        this.slangDict.writeSlangDictionaryIntoFile();
                        System.out.println("Them slang word \""+addedSlang + "\" thanh cong.");
                        break;
                }
            } else {
                // Append data, khong can overwrite lai het toan bo file
                newDefinitions.addAll(Arrays.asList(addedDefinition));
                this.slangDict.add(addedSlang, newDefinitions);
                this.slangDict.appendSlangIntoFile(addedSlang, newDefinitions);
               ColorPrinter.printlnGreenText("Them slang word \""+addedSlang + "\" thanh cong.");
            }
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Them that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    // 5. Chức năng edit 1 slang word.
    public void updateSlangWord() {
        try {
            ColorPrinter.printGreenText("Nhap slang word can chinh sua: ");
            String updatedSlang = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(updatedSlang);

            if (!existingSlang) {
                ColorPrinter.printlnYellowText("Slang word khong ton tai.");
            } else {
                ColorPrinter.printGreenText("Nhap dinh nghia moi: ");
                String updatedDefinition = scanner.nextLine();

                this.slangDict.update(updatedSlang, new ArrayList<>(Arrays.asList(updatedDefinition)));
                ColorPrinter.printlnGreenText("Cap nhat thanh cong");
                this.slangDict.writeSlangDictionaryIntoFile();
            }
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Cap nhat that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    // 6. Chức năng delete 1 slang word. Confirm trước khi xoá.
    public void deleteSlangWord() {
        try {
            ColorPrinter.printGreenText("Nhap slang word can xoa: ");
            String deletedSlang = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(deletedSlang);

            if (!existingSlang) {
                ColorPrinter.printlnYellowText("Slang word khong ton tai.");
            } else {
                ArrayList<Integer> deletedSlangFunctionIndices = new ArrayList<>(Arrays.asList(0, 1));
                ColorPrinter.printlnGreenText("Ban co chac chan muon xoa slang word \"" + deletedSlang + "\"?. " +
                        "\n  + Bam phim 1 de ghi de xac nhan xoa." +
                        "\n  + Bam phim 0 de huy thao tac");
                int userOption = HandleUserInput.getUserInput(deletedSlangFunctionIndices);
                switch (userOption) {
                    case 0:
                        break;
                    case 1:
                        boolean deletedResult = this.slangDict.deleteSlangWord(deletedSlang);
                        ColorPrinter.printlnGreenText("Xoa slang word \"" + deletedSlang + "\"" + (deletedResult ? " thanh cong." : " that bai."));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Xoa that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    // 7. Chức năng reset danh sách slang words gốc.
    public void resetToOriginalDictionary(String backupFileName) {
        try {
            this.slangDict.resetToOriginalDictionary(backupFileName);
            ColorPrinter.printlnGreenText("Reset thanh cong.");
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Reset that bai.");
            e.printStackTrace();
        }
    }

    // 8. Chức năng random 1 slang word (On this day slang word).
    public void randomASlangWord() {
        try {
            Map.Entry<String, ArrayList<String>> slangEntry = this.slangDict.randomASlangWord();
            ColorPrinter.printlnGreenText("On this day slang word");
            ColorPrinter.printlnGreenText("Slang: " + slangEntry.getKey());
            ColorPrinter.printlnGreenText("Definitions: ");
            printListInOrder(slangEntry.getValue());
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Da xay ra loi he thong. Vui long thu lai.");
            e.printStackTrace();
        }
    }
}
