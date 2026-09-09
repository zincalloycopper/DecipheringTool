import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class CorpusCreator {
    private String[] words;
    private Map<String,Integer> tetragrams = new HashMap<String, Integer>();

    private void WriteTetras(){
        //Fetches the tetragrams from the corpus and writes them into a hashmap alongside the frequency
        Path Read = Path.of("CorpusToRead");

        try {
            String CorpusText = Files.readString(Read);
            CorpusText = CorpusText.replace(" ", "");
            for (int i=0; i<CorpusText.length()-4;i++){
                String Tetra =CorpusText.substring(i,i+4);
                if (!((tetragrams.containsKey(Tetra)))){
                    //checks if the hashmap does NOT contain the tetragram already
                    tetragrams.put(Tetra,1);
                }
                else{
                    tetragrams.put(Tetra, tetragrams.get(Tetra)+1);
                    //updates the frequency
                }

            }

        } catch (IOException e) {
            System.out.println("No Corpus found");
        }
    }
    private void SortTetras(){
        ArrayList<String> TempWords = new ArrayList<>();//a temporary arraylist to add the tetragrams to before sorting and writing to file

        for( Map.Entry<String,Integer> Entry: tetragrams.entrySet()){  //iterates through the tetragrams map and adds them to the temp array
            TempWords.add(Entry.getKey());
        }
        words = new String[TempWords.size()];
        for(int i=0; i<TempWords.size();i++){
            words[i]=TempWords.get(i);
        }
        Arrays.sort(words);
        //sorts the tetragrams alphabetically(ready for hashing for quick lookup))
    }

    private void WriteToFile(){

        Path Read = Path.of("TetrasWithFrequency.txt");

        try{
            FileWriter Writer = new FileWriter("TetrasWithFrequency.txt");
           String Existing = Files.readString(Read);
           //ensures the file is not overwritten
           String Temp;// temporary string initialised
           for(String S:words){
               Temp = S+"/"+tetragrams.get(S);
               //System.out.println(Temp);
               Existing = Existing+Temp+"#";
               //adds the pair of tetragram/frequency to a temp and then to the existing string, the # is used to seperate keypairs
           }

            Writer.write(Existing);
        } catch (IOException e){
            System.out.println("No tetragramFrequencyFile found");
        }
    }

    public void CreateCorpus(){
        CorpusCreator CC = new CorpusCreator();
        CC.WriteTetras();
        CC.SortTetras();
        CC.WriteToFile();
    }
}
