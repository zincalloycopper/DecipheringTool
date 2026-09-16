public class FitnessManager {
    private  MathsHandler Maths = new MathsHandler();
    private Database DB = new Database();
    private double Fitness;
    private double TetraFitness;
    private void MonoFitness(double[] ExpectedMono, double[] ActualMono){

        Fitness = Math.cos((Maths.DotProduct(ExpectedMono,ActualMono))/(Math.sqrt((Maths.DotProduct(ExpectedMono,ExpectedMono))*(Maths.DotProduct(ActualMono,ActualMono)))));
        // Fitness ranges from 0-1, with 0 being the worst and 1 being the best

    }


    private void TetragramFitness(String Ciphertext, int[] actualTetragrams,double[] ExpectedLogTetragrams){
        HashFunction Hash = new HashFunction();
        double sum = 0.0;
        String CurrentTetra;
        int index = 0;
        for(int i=0; i <Ciphertext.length()-4;i++){

            CurrentTetra= Ciphertext.substring(i,i+4);
            Hash.SetTetra(CurrentTetra);
            index = Hash.getB26Val();
            TetraFitness = TetraFitness+(actualTetragrams[index]*ExpectedLogTetragrams[index]);

        }

    }
    public void SetMonoFitness(double[] ActualMono){
        MonoFitness(DB.FetchMonos(),ActualMono);
    }

    public double GetTetraFitness(){
        return TetraFitness;
    }
    public  double getMonoFitness(){
       return Fitness;
    }
}
