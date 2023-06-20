import java.util.*;

public class MiniGame {
    Scanner scanner = new Scanner(System.in);
    private String storageFileName;
    private String result;
    private String content;

    public MiniGame(String storageFileName) {
        this.content = "";
        this.storageFileName = storageFileName;
    }


    // MINI GAME GUESS DEFINITION BASE ON A SLANG
    public void startMiniGameOne() {
        try {
            ArrayList<String> answerOptions = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
            ArrayList<Integer> playOptions = new ArrayList<>(Arrays.asList(1, 0));

            // Load dữ liệu từ file lên dictionary data
            SlangDictionary slangDict = new SlangDictionary(this.storageFileName);
            if (slangDict.getData().size() < 4) {
                ColorPrinter.printlnYellowText("So luong slang words khong du de thuc hien mini game. Vui long them vao.");
                return;
            }

            int userPlayOption = 1;
            do {
                // Reset content khi chọn chơi lại
                this.content = "";
                // Random slang và đáp án tương ứng
                HashMap<String, String> answerList = new HashMap<>();
                int i = 0;
                while (true) {
                    Map.Entry<String, ArrayList<String>> slangEntry = slangDict.randomASlangWord();
                    String slang = slangEntry.getKey();
                    // Chỉ lấy definition thứ nhất (cho gọn gàng)
                    String definition = slangEntry.getValue().get(0);

                    // Kiểm tra 2 slang bị random trùng
                    if (answerList.containsValue(slang)) {
                        continue;
                    }
                    if (i == 0) {
                        this.content += "\na). " + definition;
                        answerList.put("a", slang);
                    } else if (i == 1) {
                        this.content += "\nb). " + definition;
                        answerList.put("b", slang);
                    } else if (i == 2) {
                        this.content += "\nc). " + definition;
                        answerList.put("c", slang);
                    } else if (i == 3) {
                        this.content += "\nd). " + definition;
                        answerList.put("d", slang);
                    }

                    if (i == 3) {
                        break;
                    }
                    i++;
                }

                // Random một giá trị là ket quả trong danh sách câu trả lời bên trên
                ArrayList<String> answers = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
                Random random = new Random();
                int randomIndex = random.nextInt(answers.size());
                this.result = answers.get(randomIndex);
                String slang = answerList.get(answers.get(randomIndex));

                this.content = "\nWHAT IS THE THE DEFINITION FOR \"" + slang + "\"?" + this.content;
                ColorPrinter.printlnGreenText(this.content);

                String userOption = HandleUserInput.getUserStringInput(answerOptions);

                if (userOption.equals(this.result)) {
                    ColorPrinter.printlnYellowText("Chinh xac.");
                } else {
                    ColorPrinter.printlnYellowText("Rat tiec. Dap an chinh xac la: " + this.result);
                }
                ColorPrinter.printlnGreenText("\nBan co muon tiep tuc?\n1.Choi tiep. \n0.Thoat");
                userPlayOption = HandleUserInput.getUserInput(playOptions);

            } while (userPlayOption != 0);
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Da xay ra loi trong he thong..");
            e.printStackTrace();
        }
    }

    // MINI GAME GUESS SLANG BASE ON A DEFINITION
    public void startMiniGameTwo() {
        /**
         * Lấy một định nghĩa,
         * Show 4 đáp án
         * tương tự câu trên
         */
        try {
            ArrayList<String> answerOptions = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
            ArrayList<Integer> playOptions = new ArrayList<>(Arrays.asList(1, 0));

            // Load dữ liệu từ file lên dictionary data
            SlangDictionary slangDict = new SlangDictionary(storageFileName);
            if (slangDict.getData().size() < 4) {
                ColorPrinter.printlnYellowText("So luong slang words khong du de thuc hien mini game. Vui long them vao.");
                return;
            }

            int userPlayOption = 1;
            do {
                // Reset content khi chọn chơi lại
                this.content = "";
                // Random slang và đáp án tương ứng
                HashMap<String, String> answerList = new HashMap<>();
                int i = 0;
                while (true) {
                    // Random slang và định nghĩa ra, sau đó thêm vào content (nội dung câu hỏi)
                    // Chỉ lấy definition thứ nhất
                    Map.Entry<String, ArrayList<String>> slangEntry = slangDict.randomASlangWord();
                    String slang = slangEntry.getKey();
                    String definition = slangEntry.getValue().get(0);

                    // Kiểm tra 2 definition bị random trùng
                    if (answerList.containsValue(definition)) {
                        continue;
                    }

                    if (i == 0) {
                        this.content += "\na). " + slang;
                        answerList.put("a", definition);
                    } else if (i == 1) {
                        this.content += "\nb). " + slang;
                        answerList.put("b", definition);
                    } else if (i == 2) {
                        this.content += "\nc). " + slang;
                        answerList.put("c", definition);
                    } else if (i == 3) {
                        this.content += "\nd). " + slang;
                        answerList.put("d", definition);
                    }

                    if (i == 3) {
                        break;
                    }
                    i++;
                }

                // Random một giá trị là ket quả trong danh sách câu trả lời bên trên
                ArrayList<String> answers = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
                Random random = new Random();
                int randomIndex = random.nextInt(answers.size());
                this.result = answers.get(randomIndex);
                String definition = answerList.get(answers.get(randomIndex));

                this.content = "\nCAN YOU GUESS SLANG FROM THE DEFINITION \"" + definition + "\"?" + this.content;
                ColorPrinter.printlnGreenText(this.content);

                String userOption = HandleUserInput.getUserStringInput(answerOptions);

                if (userOption.equals(this.result)) {
                    ColorPrinter.printlnYellowText("Chinh xac.");
                } else {
                    ColorPrinter.printlnYellowText("Rat tiec. Dap an chinh xac la: " + this.result);
                }
                ColorPrinter.printlnGreenText("\nBan co muon tiep tuc?\n1.Choi tiep. \n0.Thoat");
                userPlayOption = HandleUserInput.getUserInput(playOptions);

            } while (userPlayOption != 0);
        } catch (Exception e) {
            ColorPrinter.printlnRedText("Da xay ra loi trong he thong.");
            e.printStackTrace();
        }
    }

}
