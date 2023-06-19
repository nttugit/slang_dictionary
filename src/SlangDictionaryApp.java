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
        ColorPrinter.printGreenText("Nhap slang ban muon tim kiem (phan biet chu hoa va chu thuong): ");
        String slang = scanner.nextLine();

        List<String> result = slangDict.search(slang);
        if (result != null) {
            ColorPrinter.printlnGreenText("Cac dinh nghia cho slang word \"" + slang + "\":");
            printListInOrder(result);
            slangDict.saveHistory(slang);
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

    //  3. Hiển thị history, danh sách các slang word đã tìm kiếm.
    public void showHistory() {
        ColorPrinter.printlnGreenText("Cac slang ban da tim kiem:");
        List<String> history = slangDict.getHistory();
        // Review
//        Collections.reverse(history);
        if (history.size() > 0) {
            printListInOrder(history);
        } else {
            ColorPrinter.printlnGreenText("Lich su rong. Ban chua tim kiem slang nao.");
        }
    }

    //  4. Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người
//    dùng, confirm có overwrite hay duplicate ra 1 slang word mới.
    public void addSlangWord() {
        // Kiểm tra tồn tại, overwrite hoặc duplicate
        try {
            ColorPrinter.printGreenText("Nhap slang word can them: ");
            String addedSlang = scanner.nextLine();
            ColorPrinter.printGreenText("Nhap dinh nghia: ");
            String addedDefinition = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(addedSlang);

            ArrayList<Integer> addedSlangFunctionIndices = new ArrayList<>(Arrays.asList(0, 1, 2));

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
                        this.slangDict.add(addedSlang, new ArrayList<>(Arrays.asList(addedDefinition)));
                        break;
                    case 2:
                        ArrayList<String> newDefinitions = this.slangDict.search(addedSlang);
                        newDefinitions.add(addedDefinition);
                        this.slangDict.add(addedSlang, newDefinitions);
                        break;
                }
            } else {
                this.slangDict.add(addedSlang, new ArrayList<>(Arrays.asList(addedDefinition)));
            }
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Them that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    //5. Chức năng edit 1 slang word.
    public void updateSlangWord() {
        try {
            ColorPrinter.printGreenText("Nhap slang word can chinh sua: ");
            String updatedSlang = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(updatedSlang);

            if (!existingSlang) {
                ColorPrinter.printlnYellowText("Slang word khong ton tai.");
            } else {
                System.out.print("Nhap dinh nghia moi: ");
                String updatedDefinition = scanner.nextLine();
                this.slangDict.add(updatedSlang, new ArrayList<>(Arrays.asList(updatedDefinition)));
            }
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Cap nhat that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    //            6. Chức năng delete 1 slang word. Confirm trước khi xoá.
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

    //            7. Chức năng reset danh sách slang words gốc.
    public void resetToOriginalDictionary() {
        try {
            this.slangDict.resetToOriginalDictionary();
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

    //  9. Chức năng đố vui, chương trình hiển thị 1 random slang word, với 4 đáp án cho
    //    người dùng chọn.
    public void showMiniGameOne() {
        try {
            /**
             * Tạo bộ câu hỏi dành riêng cho mini game
             *  - Random 1 slang
             *   + Lấy nghĩa của slang đó
             *   + Lấy các câu trả lời đánh lừa có sẵn trong bộ câu hỏi
             *  => Câu hỏi hay, nhưng bị giới hạn trong bộ câu hỏi
             *  => Nhưng thầy không yêu cầu câu hỏi phải chuẩn
             *
             *
             * Sử dụng bộ câu hỏi hiện tại
             *  - Random 1 slang
             *    + Lấy nghĩa của slang đó
             *    + Lấy nghĩa của các slang khác (được random nhưng loại trừ slang đó)
             *
             *
             * -> tạo 1 class để làm mini game
             *
             *
             *
             */
//            Map.Entry<String, ArrayList<String>> slangEntry = this.slangDict.randomASlangWord();
//            Map.Entry<String, ArrayList<String>> slangEntry = this.slangDict.randomASlangWord();
//            Map.Entry<String, ArrayList<String>> slangEntry = this.slangDict.randomASlangWord();
//            Map.Entry<String, ArrayList<String>> slangEntry = this.slangDict.randomASlangWord();

//            ColorPrinter.printlnGreenText("Mini Game One");
//            ColorPrinter.printlnGreenText("What is the definition of the slang \"" +slangEntry.getKey() +"\"?");


//            ColorPrinter.printGreenText("a) ");
//            printListInOrder(slangEntry.getValue());
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Da xay ra loi he thong. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    //10. Chức năng đố vui, chương trình hiển thị 1 definition, với 4 slang words đáp án cho
    //    người dùng chọn
    public void showMiniGameTwo() {
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
