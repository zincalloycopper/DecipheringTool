import java.util.HashMap;
import java.util.Map;

public class StringHandler {

    private double[] LocalMonogramFrequencies = new double[26];
    private int[] LocalTetragramFrequencies = new int[Math.powExact(26, 4)];
    private HashFunction Hash = new HashFunction();
    private String CipherText;
    private Map<String,Integer> TempMap = new HashMap<String,Integer>();
    private Map<Character,Integer> TempAlpha = new HashMap<Character,Integer>();
    private void SetLocalTetragramFrequencies(){
        for(int i=0; i<CipherText.length()-4;i++){
            String CurrentTetra = CipherText.substring(i,i+4);
            if (TempMap.containsKey(CurrentTetra)){
                TempMap.put(CurrentTetra,TempMap.get(CurrentTetra)+1);
            }
            else{
                TempMap.put(CurrentTetra,1);
            }
        }
        for(Map.Entry<String,Integer> E: TempMap.entrySet()){
            Hash.SetTetra(E.getKey());
            LocalTetragramFrequencies[Hash.getB26Val()]=E.getValue();
        }
    }
    private void SetLocalMonogramFrequencies(){
        for(char C: CipherText.toCharArray()){
            if(TempAlpha.containsKey(C)){
                TempAlpha.put(C,TempAlpha.get(C)+1);
            }
            else{
                TempAlpha.put(C,1);
            }
        }
        for(Map.Entry<Character,Integer> E: TempAlpha.entrySet()){
            System.out.println(E.getKey());
            LocalMonogramFrequencies[E.getKey()-65]=E.getValue();
        }
    }
    public void setCipherText(String Ciphertext) {
        CipherText = Ciphertext.replaceAll("\\p{Punct}","");
        CipherText = CipherText.replaceAll("\\s","");
        CipherText = CipherText.replaceAll("\\d","");
        CipherText = CipherText.toUpperCase();
        //validation to remove whitespace, numbers and punctuation
        SetLocalTetragramFrequencies();
        SetLocalMonogramFrequencies();
        //find the local data
    }
public String GetCiphertext(){
        return CipherText;
}
    public int[] getLocalTetragramFrequencies() {
        return LocalTetragramFrequencies;
    }
}
