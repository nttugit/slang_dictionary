import java.util.*;

public class MiniGame {
    Scanner scanner = new Scanner(System.in);

    private String result;


    //
//    private String firstDistractor;
//    private String secondDistractor;
//    private String thirdDistractor;

    private String content;


    public MiniGame() {
        this.content = "";
    }

    /**
     * @param
     * @return
     */
    // Map<String, ArrayList<String>> slangDictData
    private void randomResult() {
//        ArrayList<String> answers = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
//        Random random = new Random();
//        int randomIndex = random.nextInt(answers.size());
//        this.result = answers.get(randomIndex);
    }

    public void startMiniGameOne() {
        try {
            ArrayList<String> answerOptions = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
            ArrayList<Integer> playOptions = new ArrayList<>(Arrays.asList(1, 0));
            String storageFileName ="src/slang.txt";

            int userPlayOption = 1;
            do {
                // Reset content khi chọn chơi lại
                this.content = "";
                SlangDictionary slangDict = new SlangDictionary(storageFileName);

                // Random slang và đáp án tương ứng
                HashMap<String, String> answerList = new HashMap<>();
                int i = 0;
                while (true) {
                    Map.Entry<String, ArrayList<String>> slangEntry = slangDict.randomASlangWord();
                    // Kiểm tra 2 slang bị random trùng
                    if (answerList.containsValue(slangEntry.getKey())) {
                        continue;
                    }
                    if (i == 0) {
                        this.content += "\na). " + slangEntry.getValue();
                        answerList.put("a", slangEntry.getKey());
                    } else if (i == 1) {
                        this.content += "\nb). " + slangEntry.getValue();
                        answerList.put("b", slangEntry.getKey());
                    } else if (i == 2) {
                        this.content += "\nc). " + slangEntry.getValue();
                        answerList.put("c", slangEntry.getKey());
                    } else if (i == 3) {
                        this.content += "\nd). " + slangEntry.getValue();
                        answerList.put("d", slangEntry.getKey());
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
            ColorPrinter.printlnRedText("Da xay ra loi trong he thong. Quy khach vui long khoi don lai.");
            e.printStackTrace();
        }


    }

//    public void startMiniGameTwo(){
//
//    }

}
