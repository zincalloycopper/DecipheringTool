import java.util.Arrays;

public class DecryptionParent {

protected String Ciphertext;
protected char[] StartKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
DecryptionParent(String Ciphertext, Database DB, double[] IoCs) {
    SHandler.setCipherText(Ciphertext);
    this.Ciphertext = SHandler.GetCiphertext();
    this.FManager = new FitnessManager(DB);

    this.DB  = DB;
}
protected Database DB;
protected FitnessManager FManager;
protected StringHandler SHandler = new StringHandler();

protected int period;
/*int FindPeriod(double[]NormalMonos, double[]CipherTextMonos){
    int LocalPeriod=0;


    return LocalPeriod;
}
*/
     public int FindPeriodTwist(String CipherText, int MaxVal) {
         System.out.println("Finding period");
        int period = 0;
        double[] averagesOfSignatures = new double[26];

        for (int BlockSize = 1; BlockSize < MaxVal + 1; BlockSize++) {
            String[] slices = new String[BlockSize];

            StringBuilder Slicer = new StringBuilder();
            for (int S = 0; S < slices.length; S++) {

                for (int i = 0; i < Ciphertext.length(); i++) {
                    if (i % BlockSize == S) {
                        Slicer.append(CipherText.charAt(i));
                    }
                }
                String Slice = Slicer.toString();
                slices[S] = Slice;
                Slicer.setLength(0);
            }
            for(String S:slices){
                //System.out.println(S);
                SHandler.setCipherText(S);
                double[] Temps = SHandler.getLocalMonogramFrequencies();
                for(int i = 0; i <Temps.length;i++){
                    Temps[i] /= S.length();
                }
                //System.out.println(Arrays.toString(Temps));
                for (int i =0; i<26;i++){
                    averagesOfSignatures[i] +=Temps[i];
                }
            }
        }
        for(int i =0; i<averagesOfSignatures.length;i++){
            averagesOfSignatures[i]/=MaxVal;
        }
        Arrays.sort(averagesOfSignatures);
System.out.println(Arrays.toString(averagesOfSignatures));
        double[] EnglishSignature = DB.FetchMonos();
        Arrays.sort(EnglishSignature);
        System.out.println(Arrays.toString(EnglishSignature));
        double[] Periods = new double[MaxVal];
        double leftSum = 0;
        double rightSum = 0;
        for(int i = 0; i <MaxVal;i++){
            for (int X = 0; X<12; X++){
                leftSum+=(EnglishSignature[X]-averagesOfSignatures[X]);

            }
            for(int Y = 12; Y<26;Y++){
                rightSum+=(averagesOfSignatures[Y]-EnglishSignature[Y]);

            }
            System.out.print(leftSum + "//" + rightSum+"//");
            Periods[i] = leftSum+rightSum;
            System.out.println(Periods[i]);
            leftSum = 0;
            rightSum = 0;
        }
       double Temp = Arrays.stream(Periods).max().getAsDouble();
        System.out.println(Temp);
         for(int i = 0; i <Periods.length;i++){
             System.out.println(Periods[i]);
             if( Periods[i] == Temp){
                 period = i;
             }


         }
         System.out.println(period);
        return period;
    }
protected double getTetraFitness(String Ciphertext){
    FManager.SetTetraFitness(Ciphertext,SHandler.getLocalTetragramFrequencies());
    return FManager.GetTetraFitness();

}

}
