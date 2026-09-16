public class MathsHandler {

Database DB = new Database();
StringHandler SH = new StringHandler();

    private double FetchLogOfTetra (String Tetra, int[] TetragramFrequencies){
        double Value;
        HashFunction LocalHash = new HashFunction();
        LocalHash.SetTetra(Tetra);

        try{
            Value = Math.log(TetragramFrequencies[LocalHash.getB26Val()]);
            return Value;
        } catch(NullPointerException N){
            return -1.0;//any values that do not occur (Which should technically have a frequency of 0) will return -1 to be less than the log of non-zero tetragrams
        }


    }

    public double FetchFreqOfMono(char C, double[] MonoFrequencies){
        double Value = MonoFrequencies[C-65];
        return Value;
    }
}
