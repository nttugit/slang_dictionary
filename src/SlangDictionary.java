import java.util.*;

public class SlangDictionary {
    // Bởi vì cho thêm slang duplicate, nên một slang có nhieu definition
    HashMap<String, List<String>> data;

    // Danh sách các slang words đã tìm kiếm
    List<String> history;

    public SlangDictionary() {
        // Todo: Load từ file
        this.data = new HashMap<>();
        this.data.put("LOL", Arrays.asList("Laugh out loud", "League of Legend"));
        this.data.put("AKA", Arrays.asList("As know as"));
        this.data.put("NTT", Arrays.asList("Nguyen Tat Thanh"));
        this.data.put("NTTU", Arrays.asList("Nguyen Tuan Tu", "Nguyen Tat Thanh University"));
        // Todo: Cho nó lưu vào file luôn
        this.history = new ArrayList<>();
    }

    public List<String> search(String slang) {
        return this.data.get(slang);
    }

    public List<String> searchSlangByDefinition(String keyword) {
        List<String> slangList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : this.data.entrySet()) {
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

    public List<String> getHistory() {
        return history;
    }

    public boolean isExistingSlang(String slang){
        return  this.data.containsKey(slang);
    }

    public List<String> add(String slang, List<String> definitions) {
        return this.data.put(slang, definitions);
    }


    public void printSlang() {
//        HashMap<String, List<String>> data = new HashMap<>();
//        data.put("nice",Arrays.asList("nguyen tuan tu","tuan tu", "tu"));
//        data.put("beautiful",Arrays.asList("tu dep trai"));

        for (Map.Entry<String, List<String>> entry : this.data.entrySet()) {
            System.out.println("Slang: " + entry.getKey() + ", Definitions: " + entry.getValue());
        }
    }


}
