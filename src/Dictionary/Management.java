package Dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Data
 * Created by lddong
 * Date 11/18/2022 - 12:47 PM
 * Description: Core system of dictionary
 */
public class Management {
    private HashMap<String, String> data;
    private ArrayList<String> historyOfSearch = new ArrayList<>();

    /**
     * Constructor of system
     */
    public Management() throws IOException {
        data = new HashMap<String, String>();

        this.importData("slang.txt");
    }

    /**
     *
     * @param fileName is name of input file ("slang.txt")
     * @throws IOException
     */
    private void importData(String fileName) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch(FileNotFoundException e) {
            System.out.println("File is not exist.");
            return;
        }

        String temp = "";
        while (br.ready()) {
            String value = br.readLine();

            if (!value.contains("`")) {
                value += " / " + data.get(temp);
                data.replace(temp, value);
            }
            else {
                String[] parts = value.split("`");
                data.put(parts[0], parts[1]);
                temp = parts[0];
            }
        }

        br.close();
    }

    /**
     *
     * @param fileName is name of output file
     */
    private void exportData(String fileName) throws IOException {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            System.out.println("Cannot create file.");
            return;
        }

        for (String i : data.keySet()) {
            try {
                bw.write(i + "`");
                String meaning = data.get(i);
                if (meaning.contains("/")) {
                    String[] temp = meaning.split(" / ");
                    for (String s : temp) {
                        bw.write(s + "\n");
                    }
                }
                else bw.write(meaning + "\n");
            } catch (IOException e) {
                System.out.println("Error when write to file.");
                return;
            }
        }

        bw.close();
    }
    private String[] convertArrayListToArray(ArrayList<String> arrayList) {
        String[] arr = new String[arrayList.size()];
        arr = arrayList.toArray(arr);

        return arr;
    }
    public String[] findSlangWord(String val) {
        ArrayList<String> ans = new ArrayList<String>();
        data.forEach((key,value) -> {
            String temp = key + ": " + value;

            if (key.contains(val)) {
                ans.add(temp);
                historyOfSearch.add(temp);
            }
        });

        return convertArrayListToArray(ans);
    }
    public String[] findSlangWordByDef(String val) {
        ArrayList<String> ans = new ArrayList<String>();

        data.forEach((key,value) -> {
            String temp = key + ": " + value;

            if (value.contains(val)) {
                ans.add(temp);
                historyOfSearch.add(temp);
            }
        });

        return convertArrayListToArray(ans);
    }

    public int addSlangWord(String keyword, String meaning) {
        boolean isFoundK = data.containsKey(keyword);

        if (isFoundK) {
            if (data.get(keyword).equals(meaning)) {
                return 0;
            }
            else return 2;
        }
        else data.put(keyword, meaning);

        return 1;
    }

    public String overwriteSlangWord(String keyword, String meaning) {
        return data.replace(keyword,meaning);
    }

    public void duplicateSlangWord(String keyword, String meaning) {
        data.replace(keyword, data.get(keyword), data.get(keyword) + " / " + meaning );
    }

    public String deleteSlangWord(String keyword) {
        return data.remove(keyword);
    }

    public void reset() throws IOException {
        data.clear();
        importData("slang.txt");
    }

    public String random() {
        List<String> keysAsArray = new ArrayList<String>(data.keySet());
        Random r = new Random();

        String keyword = keysAsArray.get(r.nextInt(keysAsArray.size()));

        return keyword;
    }

    public String randomSlangWord() {
        String keyword = random();
        return "Slang word: " + keyword + "\nMeaning: " + data.get(keyword);
    }

    public String randomMeaning() {
        String keyword = random();
        return data.get(keyword);
    }

    public String[] randomQuestion() {
        String keyword = random();
        String[] info = {keyword, data.get(keyword)};

        return info;
    }

    public String[] showHistoryOfSearch() {
        return convertArrayListToArray(historyOfSearch);
    }
}
