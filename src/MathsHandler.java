import java.util.Arrays;

public class MathsHandler {

char[] Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public double FetchLogOfTetra (String Tetra, int[] TetragramFrequencies,int length){
        double Value;
        HashFunction LocalHash = new HashFunction();
        LocalHash.SetTetra(Tetra);

        try{
            Value = Math.log((TetragramFrequencies[LocalHash.getB26Val()])/(double)(length-3));
            return Value;
        } catch(NullPointerException N){
            System.out.println("Cannot take log of 0");
            return -100.0;//any values that do not occur (Which should technically have a frequency of 0) will return -1 to be less than the log of non-zero tetragrams
        }


    }

    public double DotProduct(double[] Vector1, double[] Vector2){
        double Sum = 0;
        if(Vector1.length> Vector2.length|| Vector2.length>Vector1.length){
          System.out.println("VECTOR DIMENSIONS DO NOT MATCH");
        }
        else{
            for ( int i=0; i< Vector1.length;i++){
                Sum = Sum+(Vector1[i]*Vector2[i]);
            }
        }
        return Sum;
    }

    public double IndexOfCoincidence(String Text) {
        double IOC = 0.0;
        int length = Text.length();
        if (length<2){
            return 0.0;
        }
        int freq[] = new int[26];
        for(char c:Text.toCharArray()){
            freq[c-'A']++;
        }
        double sum = 0.0;
        for(int f:freq){
            sum +=f*(f-1);
        }
        return sum/(length*(length-1));

    }
    public double PeriodIOC(String Text, int BlockSize){
        double period = 0;
        StringHandler Shandler = new StringHandler();
        String[] slices = new String[BlockSize];

        StringBuilder Slicer = new StringBuilder();
            for(int S =0; S<slices.length;S++){

                for(int i =0; i<Text.length();i++){
                    if (i%BlockSize == S){
                        Slicer.append(Text.charAt(i));
                    }
                }
                String Slice = Slicer.toString();
                if(Slice.length()!=0) {
                    period += IndexOfCoincidence(Slice);
                }
                Slicer.setLength(0);
            }
        period = period/BlockSize;

            return period;
    }

    public double FetchFreqOfMono(char C, double[] MonoFrequencies){
        double Value = MonoFrequencies[C-65];
        return Value;
    }


}
