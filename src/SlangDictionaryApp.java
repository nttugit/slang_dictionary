import java.util.*;

public class SlangDictionaryApp {
    static Scanner scanner = new Scanner(System.in);
    private SlangDictionary slangDict;

    static final List<Integer> addedSlangFunctionIndiceList = Arrays.asList(0,1,2);

    public SlangDictionaryApp() {
        // todo: thêm đường dẫn file khởi tạo ở đây
        this.slangDict = new SlangDictionary();
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

    //            4. Chức năng add 1 slang words mới. Nếu slang words trùng thì thông báo cho người
//    dùng, confirm có overwrite hay duplicate ra 1 slang word mới.
    public void addSlangWord() {
        // Kiểm tra tồn tại, overwrite hoặc duplicate
        try {
            System.out.print("Nhap slang word can them: ");
            String addedSlang = scanner.nextLine();
            System.out.print("Nhap dinh nghia: ");
            String addedDefinition = scanner.nextLine();
            boolean existingSlang = slangDict.isExistingSlang(addedSlang);

            ArrayList<Integer> addedSlangFunctionIndices = new ArrayList<>();
            addedSlangFunctionIndices.addAll(addedSlangFunctionIndiceList);

            if (existingSlang) {
                System.out.println("Slang word \"" + addedSlang + "\" da ton tai. " +
                        "\nBam phim 1 de ghi de (overwrite)." +
                        "\nBam phim 2 de tao them mot dinh nghia moi (duplicate)" +
                        "\nBam phim 0 de huy thao tac");
                System.out.print("Lua chon cua ban: ");
                int userOption = HandleUserInput.getUserInput(addedSlangFunctionIndices);
                switch (userOption){
                    case 0:
//                        System.out.println("Thao tac da duoc huy.");
                        break;
                    case 1:

                        break;
                    case 2:
                        break;
                }
            } else {

            }

        } catch (Exception e) {
            System.out.println("Them that bai. Vui long thu lai.");
            e.printStackTrace();
        }
//        return false;
    }

//5. Chức năng edit 1 slang word.
//            6. Chức năng delete 1 slang word. Confirm trước khi xoá.
//            7. Chức năng reset danh sách slang words gốc.
//            8. Chức năng random 1 slang word (On this day slang word).
//            9. Chức năng đố vui, chương trình hiển thị 1 random slang word, với 4 đáp án cho
//    người dùng chọn.
//10. Chức năng đố vui, chương trình hiển thị 1 definition, với 4 slang words đáp án cho
//    người dùng chọn
}
