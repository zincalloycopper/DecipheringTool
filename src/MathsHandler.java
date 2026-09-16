public class MathsHandler {

private Database DB = new Database();
private StringHandler SH = new StringHandler();
private int[] ExpectedTetras = DB.FetchTetras();
private double[] ExpectedTetraLogs = DB.FetchTetraLogs();
private double[] ExpectedMonos = DB.FetchMonos();

    public double FetchLogOfTetra (String Tetra, int[] TetragramFrequencies){
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

    public double DotProduct(double[] Vector1, double[] Vector2){
        double Sum = 0;
        if(Vector1.length> Vector2.length|| Vector2.length<Vector1.length){
          System.out.println("VECTOR DIMENSIONS DO NOT MATCH");
        }
        else{
            for ( int i=0; i< Vector1.length;i++){
                Sum = Sum+(Vector1[i]*Vector2[i]);
            }
        }
        return Sum;
    }

    public double FetchFreqOfMono(char C, double[] MonoFrequencies){
        double Value = MonoFrequencies[C-65];
        return Value;
    }


}
