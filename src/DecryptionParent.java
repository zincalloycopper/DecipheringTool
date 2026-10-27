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
     public int FindPeriodTwist(String Text, int MaxVal) {
         System.out.println("Finding period");
         int Period = 0;
         double[] EnglishSignature = DB.FetchMonos();
         Arrays.sort(EnglishSignature);

         String[][] slices = new String[MaxVal][];
         for(int i = 1; i<=MaxVal;i++){
             slices[i-1] = SHandler.SliceString(Text,i);
             //System.out.println(Arrays.toString(slices[i-1]));
         }

        double[][] Signatures = new double[MaxVal][26];
double[] temp = new double[26];
         for(int i = 0; i < slices.length; i++) {

             double[] averageSignature = new double[26];

             for (int j = 0; j < slices[i].length; j++) {

                 SHandler.setCipherText(slices[i][j]);

                 temp = SHandler.getLocalMonogramFrequencies();

                 for (int k = 0; k < 26; k++) {
                     temp[k] /= slices[i][j].length();

                 }
                 Arrays.sort(temp);
                 for (int k = 0; k < 26; k++) {
                     averageSignature[k] += temp[k];
                 }
             }

                 for (int k = 0; k < 26; k++) {
                     averageSignature[k] /= slices[i].length;
                 }


                 Signatures[i] = averageSignature;
             }
             double[] twists = new double[MaxVal];
             double leftsum;
             double rightsum;
             for (int i = 0; i < Signatures.length; i++) {
                 leftsum = 0;
                 rightsum = 0;
                 for (int x = 0; x < 13; x++) {
                     leftsum += (-Signatures[i][x] + EnglishSignature[x]);
                 }
                 for (int x = 13; x < 26; x++) {
                     rightsum += (Signatures[i][x] - EnglishSignature[x]);
                 }
                 twists[i] = leftsum + rightsum;
                 twists[i]*=50;
             }
    for(int i =0; i<twists.length;i++){
        if(twists[i]>0){
            Period = i+1;
            return Period;
        }
    }
         for(int i = 0; i < twists.length;i++){
             //System.out.println(twists[i]);
             System.out.println("Period "+(i+1)+" = "+twists[i]);
         }


double difference = 0;


         return  Period;
     }
protected double getTetraFitness(String Ciphertext){
    FManager.SetTetraFitness(Ciphertext,SHandler.getLocalTetragramFrequencies());
    return FManager.GetTetraFitness();

}

}
