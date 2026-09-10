import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class CorpusCreator {
    private String[] words;
    private Map<String,Integer> tetragrams = new HashMap<String, Integer>();
    private Map <Character,Double> Monos = new HashMap<Character,Double>();
    private char[] Alpha;
    private double CorpusLen;
    private void WriteTetras(){
        //Fetches the tetragrams from the corpus and writes them into a hashmap alongside the frequency
        Path Read = Path.of("CorpusToRead");

        try {
            String CorpusText = Files.readString(Read);
            CorpusText = CorpusText.replaceAll("\\s","");
            for (int i=0; i<CorpusText.length()-4;i++){
                String Tetra =CorpusText.substring(i,i+4).toUpperCase();
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
            File TETRAS = new File("TetrasWithFrequency.txt");
            try {
                TETRAS.createNewFile();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void GetMonosFromCorpus(){
        Path Read = Path.of("CorpusToRead");
        try{
            String CorpusText = Files.readString(Read);
            CorpusText =CorpusText.replaceAll("\\s","");
            CorpusLen=CorpusText.length();
            CorpusText.toUpperCase();
            for (char a : CorpusText.toCharArray()){
                if (!((Monos.containsKey(a)))){
                    //checks if the hashmap does NOT contain the letter already
                    Monos.put(a,1.0);
                }
                else{
                    Monos.put(a, Monos.get(a)+1);
                    //updates the frequency
                }

            }
        } catch (IOException e) {
            System.out.println("No corpus!");
        }

    }
    private void SortMonos(){
        //does the same thing as the tetragram sorter
        ArrayList<Character> TempAlpha = new ArrayList<>();
        for (Map.Entry<Character,Double> Entry : Monos.entrySet()){
            TempAlpha.add(Entry.getKey());
        }
        System.out.println("START");

        Alpha = new char[26];
        System.out.println(Monos.keySet());
        System.out.println(Monos.entrySet());
        for (int i=0; i < 26;i++){

            Alpha[i] = TempAlpha.get(i);
        }
        Arrays.sort(Alpha);

    }
    private void WriteMonos() {
        try {
            FileWriter Writer = new FileWriter("MonosWithFrequency.txt");
            String MonosToSave = new String();
            for (char C:Alpha){
                MonosToSave = MonosToSave+C+"/"+Monos.get(C)/CorpusLen+"#";

            }
            Writer.write(MonosToSave);
        } catch (IOException e) {
            System.out.println("NO MONOGRAM FILE FOUND");
            File MONOS = new File("MonosWithFrequency.txt");
            try {
                MONOS.createNewFile();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    public void CreateTetraCorpus(){

        WriteTetras();
        SortTetras();
        WriteToFile();
    }
    public void CreateMonoCorpus(){
        GetMonosFromCorpus();
        SortMonos();
        WriteMonos();
    }


}
