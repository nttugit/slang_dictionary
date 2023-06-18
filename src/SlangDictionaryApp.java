import java.util.*;


/**
 * todos: thêm vòng lặp cho userOption câu 4
 */

public class SlangDictionaryApp {
    static Scanner scanner = new Scanner(System.in);
    private SlangDictionary slangDict;

    public SlangDictionaryApp() {
        // todo: thêm đường dẫn file khởi tạo ở đây
        this.slangDict = new SlangDictionary();
    }

    public void printSlangWords() {
        for (Map.Entry<String, ArrayList<String>> entry : this.slangDict.getData().entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    public void printListWithOrder(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    // 1. Tìm kiếm theo slang word.
    public void searchSlangWord() {
        System.out.print("Nhap slang ban muon tim kiem: ");
        String slang = scanner.nextLine();

        List<String> result = slangDict.search(slang);
        if (result != null) {
            System.out.println("Cac dinh nghia cho slang word \"" + slang + "\":");
            printListWithOrder(result);
            slangDict.saveHistory(slang);
        } else {
            System.out.println("Khong tim thay dinh nghia cua slang word: " + slang + ".");
        }
    }

    // 2. Tìm kiếm theo definition, hiển thị ra tất cả các slang words mà trong defintion có chứa keyword gõ vào.
    public void searchSlangWordsByDefinition() {
        System.out.print("Nhap tu khoa dinh nghia cua slang: ");
        String keyword = scanner.nextLine();
        List<String> result = slangDict.searchSlangByDefinition(keyword);
        if (result.size() > 0) {
            System.out.println("Cac slang trung voi tu khoa \"" + keyword + "\" la:");
            printListWithOrder(result);
        } else {
            System.out.println("Khong co slang nao phu hop voi tu khoa \"" + keyword + "\"");
        }
    }

    //  3. Hiển thị history, danh sách các slang word đã tìm kiếm.
    public void showHistory() {
        System.out.println("Cac slang ban da tim kiem:");
        List<String> history = slangDict.getHistory();
        // Review
//        Collections.reverse(history);
        if (history.size() > 0) {
            printListWithOrder(history);
        } else {
            System.out.println("Lich su rong. Ban chua tim kiem slang nao.");
        }
    }

    //  4. Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người
//    dùng, confirm có overwrite hay duplicate ra 1 slang word mới.
    public void addSlangWord() {
        // Kiểm tra tồn tại, overwrite hoặc duplicate
        try {
            System.out.print("Nhap slang word can them: ");
            String addedSlang = scanner.nextLine();
            System.out.print("Nhap dinh nghia: ");
            String addedDefinition = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(addedSlang);

            ArrayList<Integer> addedSlangFunctionIndices = new ArrayList<>(Arrays.asList(0,1,2));

            if (existingSlang) {
                System.out.println("Slang word \"" + addedSlang + "\" da ton tai. " +
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
            System.out.println("Them that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

    //5. Chức năng edit 1 slang word.
    public void updateSlangWord() {
        try {
            System.out.print("Nhap slang word can chinh sua: ");
            String updatedSlang = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(updatedSlang);

            if (!existingSlang) {
                System.out.println("Slang word khong ton tai.");
            } else {
                System.out.print("Nhap dinh nghia moi: ");
                String updatedDefinition = scanner.nextLine();
                this.slangDict.add(updatedSlang, new ArrayList<>(Arrays.asList(updatedDefinition)));
            }
        } catch (Exception e) {
            System.out.println("Cap nhat that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }

//            6. Chức năng delete 1 slang word. Confirm trước khi xoá.
    public void deleteSlangWord(){
        try {
            System.out.print("Nhap slang word can xoa: ");
            String deletedSlang = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(deletedSlang);

            if (!existingSlang) {
                System.out.println("Slang word khong ton tai.");
            } else {
                ArrayList<Integer> deletedSlangFunctionIndices = new ArrayList<>(Arrays.asList(0,1));
                System.out.println("Ban co chac chan muon xoa slang word \"" + deletedSlang + "\"?. " +
                        "\n  + Bam phim 1 de ghi de xac nhan xoa." +
                        "\n  + Bam phim 0 de huy thao tac");
                int userOption = HandleUserInput.getUserInput(deletedSlangFunctionIndices);
                switch (userOption) {
                    case 0:
                        break;
                    case 1:
                        boolean deletedResult = this.slangDict.deleteSlangWord(deletedSlang);
                        System.out.println("Xoa slang word \"" + deletedSlang + "\"" + (deletedResult ? " thanh cong." : " that bai."));
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Xoa that bai. Vui long thu lai.");
            e.printStackTrace();
        }
    }
//            7. Chức năng reset danh sách slang words gốc.
//            8. Chức năng random 1 slang word (On this day slang word).
//            9. Chức năng đố vui, chương trình hiển thị 1 random slang word, với 4 đáp án cho
//    người dùng chọn.
//10. Chức năng đố vui, chương trình hiển thị 1 definition, với 4 slang words đáp án cho
//    người dùng chọn
}
