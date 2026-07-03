import java.util.*;
import java.io.*;
import java.nio.file.*;
public class DataManager {

    public static void CreateCorpus() {
        Path ReadPath = Path.of("CorpusToRead");
        System.out.println(ReadPath);
        Map<String, Integer> WordsAndFrequencies = new HashMap<>();
        try {
            String Content = Files.readString(ReadPath);
            String[] UnsortedRepeating = Content.split(" ");
        /*
        for (int i = 0; i<UnsortedRepeating.length; i++){
            if((Unsorted.contains(UnsortedRepeating[i].toLowerCase()) == false)&& !(UnsortedRepeating[i].toLowerCase().equals("\r"))){
                Unsorted.add(UnsortedRepeating[i].toLowerCase());
                //System.out.println(UnsortedRepeating[i].toLowerCase());
            }
        }
        */
            for (int i = 0; i < UnsortedRepeating.length; i++) {
                if (WordsAndFrequencies.containsKey(UnsortedRepeating[i].toLowerCase())) {
                    //System.out.println("Match, incrementing frequency by 1");
                    WordsAndFrequencies.put(UnsortedRepeating[i].toLowerCase(), WordsAndFrequencies.get(UnsortedRepeating[i].toLowerCase()) + 1);
                    //System.out.println(UnsortedRepeating[i].toLowerCase()+" "+WordsAndFrequencies.get(UnsortedRepeating[i]));
                } else {
                    System.out.println("No match, adding word to datum");
                    WordsAndFrequencies.put(UnsortedRepeating[i].toLowerCase(), 1);
                    //System.out.println(WordsAndFrequencies+" "+WordsAndFrequencies.get(UnsortedRepeating[i]));
                }
            }
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }


        List<String> Unsorted = new ArrayList<>(WordsAndFrequencies.keySet());
        Collections.sort(Unsorted);
        List<Map.Entry<String, Integer>> WordsList = new ArrayList<>(WordsAndFrequencies.entrySet());
        WordsList.sort(Map.Entry.comparingByValue());
    /*
    for(Map.Entry<String,Integer> entry:WordsList){
        System.out.println(entry.getKey()+" "+entry.getValue());
    }*/
        try {
            FileWriter WriteHead = new FileWriter("CorpusToWriteTo.txt");
            FileWriter FrequencyWriter = new FileWriter("Frequency.txt");
            for (int i = 0; i < Unsorted.size(); i++) {
                WriteHead.write(Unsorted.get(i) + " ");
                WriteHead.write("\n");
            }
            WriteHead.close();
            System.out.println("Successfuly written to file");
            for (Map.Entry<String, Integer> entry : WordsList) {
                FrequencyWriter.write(entry.getKey() + " " + entry.getValue());

                FrequencyWriter.write(" ");
                //System.out.println("value "+entry+" written to file");

            }
            System.out.println("Successfully written to file");
            FrequencyWriter.close();
        } catch (IOException e) {
            System.out.println("error " + e.getMessage());
        }
    }
    
    public static void main(String[] args){
        CreateCorpus();
    }

}
