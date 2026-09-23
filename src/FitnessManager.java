public class FitnessManager {
    private  MathsHandler Maths = new MathsHandler();
    private Database DB;

    private double Fitness;
    private double TetraFitness;
    private double[] ExpectedLogTetragrams;

    FitnessManager(Database DB){
        this.DB = DB;
        ExpectedLogTetragrams = DB.FetchTetraLogs();
    }
    private void MonoFitness(double[] ExpectedMono, double[] ActualMono){

        Fitness = Math.cos((Maths.DotProduct(ExpectedMono,ActualMono))/(Math.sqrt((Maths.DotProduct(ExpectedMono,ExpectedMono))*(Maths.DotProduct(ActualMono,ActualMono)))));
        // Fitness ranges from 0-1, with 0 being the worst and 1 being the best

    }


    private void TetragramFitness(String Ciphertext, int[] actualTetragrams){
        HashFunction Hash = new HashFunction();
        double sum = 0.0;
        String CurrentTetra;
        int index = 0;
        TetraFitness = 0.0;
        for(int i=0; i <Ciphertext.length()-3;i++){

            CurrentTetra= Ciphertext.substring(i,i+4);
            Hash.SetTetra(CurrentTetra);

            index = Hash.getB26Val();
            //System.out.println(CurrentTetra+" "+index);
          if (ExpectedLogTetragrams[index]!=0) {
              TetraFitness = TetraFitness + ExpectedLogTetragrams[index];
          }
          else{
              TetraFitness += -100;
          }
            //System.out.println(actualTetragrams[index]);
            //System.out.println(ExpectedLogTetragrams[index]);
            //System.out.println(TetraFitness);

        }
        TetraFitness = TetraFitness/(Ciphertext.length()-3);
       // System.out.println(TetraFitness);
    }
    public void SetMonoFitness(double[] ActualMono){
        MonoFitness(DB.FetchMonos(),ActualMono);
    }
public void SetTetraFitness(String CipherText,int[] actualTetragrams){
        TetragramFitness(CipherText,actualTetragrams);
        //System.out.println(DB.FetchTetraLogs()[1]);
}
    public double GetTetraFitness(){
        return TetraFitness;
    }
    public  double getMonoFitness(){
       return Fitness;
    }
}
