import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Bo sung search by case insensitive
 */

public class SlangDictionary {
    private String storageFileName;
    // Bởi vì cho thêm slang duplicate, nên một slang có nhieu definition
    HashMap<String, ArrayList<String>> data;

    // Danh sách các slang words đã tìm kiếm
    ArrayList<String> history;


    public void loadDataFromFile(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("`");
                if (parts.length == 2) {
                    String slang = parts[0].trim();
                    String[] definitionsArr = parts[1].split("\\|");
                    ArrayList<String> definitions = new ArrayList<>(Arrays.asList(definitionsArr));

                    StringBuilder definition = new StringBuilder();

                    for (String def : definitions) {
                        definition.append(def.trim()).append(" | ");
                    }
                    definition.setLength(definition.length() - 3); // Remove the last " | "
                    this.data.put(slang, definitions);
                }
            }
            reader.close();
            System.out.println("Slang words loaded from file: " + filename);
        } catch (IOException e) {
            System.out.println("Error loading slang words from file: " + e.getMessage());
        }
    }

    public SlangDictionary(String storageFileName) {
        this.data = new HashMap<>();

        this.loadDataFromFile(storageFileName);
        // Todo: Load từ file
//
//        this.data.put("LOL", new ArrayList<>(Arrays.asList("Laugh out loud", "League of Legend")));
//        this.data.put("AKA", new ArrayList<>(Arrays.asList("As know as")));
//        this.data.put("NTT", new ArrayList<>(Arrays.asList("Nguyen Tat Thanh")));
//        this.data.put("NTTU", new ArrayList<>(Arrays.asList("Nguyen Tuan Tu", "Nguyen Tat Thanh University")));
//        // Todo: Cho nó lưu vào file luôn
        this.history = new ArrayList<>();
    }

    public HashMap<String, ArrayList<String>> getData() {
        return data;
    }

    public void setData(HashMap<String, ArrayList<String>> data) {
        this.data = data;
    }

    public void setHistory(ArrayList<String> history) {
        this.history = history;
    }

    public ArrayList<String> search(String slang) {
        return this.data.get(slang);
    }

    public ArrayList<String> searchSlangByDefinition(String keyword) {
        ArrayList<String> slangList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            List<String> definitions = entry.getValue();
            for (String definition : definitions) {
                if (definition.contains(keyword)) {
                    slangList.add(entry.getKey());
                    break;
                }
            }
        }
        return slangList;
    }

    public void saveHistory(String slang) {
        this.history.add(slang);
    }

    public ArrayList<String> getHistory() {
        return history;
    }

    public boolean isExistingSlang(String slang) {
        return this.data.containsKey(slang);
    }

    public ArrayList<String> add(String slang, ArrayList<String> definitions) {
        return this.data.put(slang, definitions);
    }

//    public ArrayList<String> update(String slang, ArrayList<String> definitions) {
//        return this.data.put(slang, definitions);
//    }


    public boolean deleteSlangWord(String slang) {
        ArrayList<String> deletedSlangWords = this.data.remove(slang);
        if (deletedSlangWords.size() > 0) {
            return true;
        }
        return false;
    }

    public void printSlang() {
//        HashMap<String, List<String>> data = new HashMap<>();
//        data.put("nice",Arrays.asList("nguyen tuan tu","tuan tu", "tu"));
//        data.put("beautiful",Arrays.asList("tu dep trai"));

        for (Map.Entry<String, ArrayList<String>> entry : this.data.entrySet()) {
            System.out.println("Slang: " + entry.getKey() + ", Definitions: " + entry.getValue());
        }
    }


    public void resetToOriginalDictionary() {
        /**
         * todos:
         * - update file (su dung backup file)
         *   + originalData = getFromBackup();
         *   + write file hien tai bang noi dung file backup, write(originalData)
         *   + cap nhat this.data bang noi dung, this.data = orignalData
         */
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

















