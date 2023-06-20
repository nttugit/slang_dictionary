import java.io.*;
import java.util.*;

public class SlangDictionary {
    private String storageFileName;
    // Bởi vì cho thêm slang duplicate, nên một slang có nhieu definition
    private HashMap<String, ArrayList<String>> data;

    // Danh sách các slang words đã tìm kiếm
    private ArrayList<String> history;
    private final static String HISTORY_FILE_NAME = "src/history.txt";


    public void loadSlangDictionaryDataFromFile(String filename) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filename));
            String line;
            // Xử lý từng dòng dữ liêu
            while ((line = bufferedReader.readLine()) != null) {
                String[] chunks = line.split("`");

                if (chunks.length == 2) {
                    String slang = chunks[0].trim();
                    String[] definitionsChunks = chunks[1].split("\\|");

                    ArrayList<String> definitions = new ArrayList<>(Arrays.asList(definitionsChunks));
                    // Dùng String Build hoặc cộng chuỗi đều được
                    StringBuilder definition = new StringBuilder();

                    for (String def : definitions) {
                        definition.append(def.trim()).append("| ");
                    }
                    // substring
                    definition.setLength(definition.length() - 2);
                    this.data.put(slang, definitions);
                } else {
                    // Dòng nào dữ liệu sai thì next
                    continue;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            ColorPrinter.printlnRedText("Da xay ra loi khi load file. Error: " + e.getMessage());
        }
    }

    public void writeSlangDictionaryIntoFile() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.storageFileName))) {
            for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
                String slang = entry.getKey();
                ArrayList<String> definitions = entry.getValue();
                String writeFileContent = slang + "`";
                for (String definition : definitions) {
                    writeFileContent += definition.trim() + "| ";
                }
                writeFileContent = writeFileContent.substring(0, writeFileContent.length() - 2);
                writeFileContent += "\n";
                bufferedWriter.write(writeFileContent);
            }
        } catch (IOException e) {
            ColorPrinter.printlnRedText("Xay ra loi trong qua trinh luu lich su xuong file." + e.getMessage());
            e.printStackTrace();
        }
    }

    public void appendSlangIntoFile(String slang, ArrayList<String> definitions) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.storageFileName, true))) {
            String writeFileContent = slang + "`";
            for (String definition : definitions) {
                writeFileContent += definition.trim() + "| ";
            }
            writeFileContent = writeFileContent.substring(0, writeFileContent.length() - 2);
            bufferedWriter.write(writeFileContent);
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUserSearchedHistoryFromFile() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(HISTORY_FILE_NAME));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String slang = line.trim();
                this.history.add(slang);
            }
            bufferedReader.close();
        } catch (IOException e) {
            ColorPrinter.printlnRedText("Da xay ra loi khi load file. Error: " + e.getMessage());
        }
    }

    // Review
    public void writeSlangToHistoryFile(String newSlang) {
        this.history.add(newSlang);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(HISTORY_FILE_NAME))) {
            for (String slang : this.history) {
                bufferedWriter.write(slang);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            ColorPrinter.printlnRedText("Xay ra loi trong qua trinh luu lich su xuong file." + e.getMessage());
            e.printStackTrace();
        }
    }

    public SlangDictionary(String storageFileName) {
        // Khởi tạo giá trị ban đầu của từ điển
        this.data = new HashMap<>();
        this.loadSlangDictionaryDataFromFile(storageFileName);
        this.storageFileName = storageFileName;
        // Lấy lịch sử của user đã lưu trước đó
        this.history = new ArrayList<>();
        this.loadUserSearchedHistoryFromFile();
    }

    public HashMap<String, ArrayList<String>> getData() {
        return data;
    }


    public ArrayList<String> search(String slang) {
        return this.data.get(slang);
    }

    public ArrayList<String> searchSlangByDefinition(String keyword) {
        ArrayList<String> slangList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            List<String> definitions = entry.getValue();
            for (String definition : definitions) {
                if (definition.contains(keyword.trim())) {
                    slangList.add(entry.getKey());
                    break;
                }
            }
        }
        return slangList;
    }


    public ArrayList<String> getHistory() {
        return this.history;
    }

    public boolean isExistingSlang(String slang) {
        return this.data.containsKey(slang);
    }

    public ArrayList<String> add(String slang, ArrayList<String> definitions) {
        return this.data.put(slang, definitions);
    }

    public ArrayList<String> update(String slang, ArrayList<String> definitions) {
        return this.data.put(slang, definitions);
    }

    public boolean deleteSlangWord(String slang) {
        ArrayList<String> deletedSlangWords = this.data.remove(slang);
        if (deletedSlangWords.size() > 0) {
            return true;
        }
        return false;
    }


    public void resetToOriginalDictionary(String backupFileName) {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(backupFileName));
                BufferedWriter writer = new BufferedWriter(new FileWriter(this.storageFileName))
        ) {
            this.data.clear();
            // copy data từ backup file -> storage file
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            // load lai du lieu vao bien data
            this.loadSlangDictionaryDataFromFile(backupFileName);
        } catch (IOException e) {
            ColorPrinter.printlnRedText("Da xay ra loi trong khi reset du lieu.");
            e.printStackTrace();
        }
    }

    public Map.Entry<String, ArrayList<String>> randomASlangWord() {
        List<String> slangList = new ArrayList<>(this.data.keySet());
        Random random = new Random();
        int randomIndex = random.nextInt(slangList.size());
        String randomSlang = slangList.get(randomIndex);
        Map.Entry<String, ArrayList<String>> entry = Map.entry(randomSlang, this.search(randomSlang));
        return entry;
    }
}

















