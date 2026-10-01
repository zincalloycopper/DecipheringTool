import java.lang.foreign.MemorySegment;
import java.util.*;


public class MonoAlphabeticDecryptor extends DecryptionParent{
    MonoAlphabeticDecryptor(String Ciphertext, Database DB) {
        super(Ciphertext, DB);
    }
    public ArrayList<Double> BestFitnesses = new ArrayList<>();
    Random random = new Random();
    public String DecryptCipher() {

        double bestFitness = 0.0;
        double testFitness = 0.0;
        String Plaintext = Ciphertext;
        bestFitness = getTetraFitness(Plaintext);

            bestFitness = getTetraFitness(Plaintext);
            int count = 0;
            char[] Key = Arrays.copyOf(super.StartKey, super.StartKey.length);
            char[] LocalStartKey = StartKey;
            int X = 0;
            int Y = 0;
            char tempchar;
            int probability = 1;
            String Temp;
            while (count < 50000) {
                X = random.nextInt(StartKey.length);
                Y = random.nextInt(StartKey.length);
                tempchar = Key[X];
                Key[X] = Key[Y];
                Key[Y] = tempchar;
                Temp = Decrypt(Key, Ciphertext);
                testFitness = getTetraFitness(Temp);
                if (testFitness > bestFitness) {
                    count = 0;
                    bestFitness = testFitness;
                    BestFitnesses.add(bestFitness);
                    Plaintext = Temp;
                    LocalStartKey = Arrays.copyOf(Key, Key.length);
                    //System.out.println(bestFitness);
                    //System.out.println(Key);
                    //System.out.println(Plaintext);
                } else {

                        count++;
                        Key = Arrays.copyOf(LocalStartKey, LocalStartKey.length);

                }
                if(count >40000 && bestFitness<3){
                    LocalStartKey = generateRandomKey();
                    Key = Arrays.copyOf(LocalStartKey, LocalStartKey.length);

                    Plaintext = Decrypt(Key, Ciphertext);
                    bestFitness = getTetraFitness(Plaintext);

                    count = 0;
                }
            }

        System.out.println(BestFitnesses);
        return Plaintext;
        }


    private String Decrypt(char[] Key,String CipherText){
        char[] CopyOfCiphertext = Ciphertext.toCharArray();

       for (int i = 0; i < CopyOfCiphertext.length;i++){
           CopyOfCiphertext[i] = Key[CopyOfCiphertext[i]-65];
       }

        return String.copyValueOf(CopyOfCiphertext);
    }
    private char[] generateRandomKey(){
       char[] Key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

       for (int i =0; i<Key.length;i++){
           int j =  random.nextInt(Key.length);
           char temp = Key[i];
           Key[i] = Key[j];
           Key[j] = temp;
       }
       return Key;
    }

}
