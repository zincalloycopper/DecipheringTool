public class LinguisticDataManager {

    private StringHandler SHandler = new StringHandler();

    private  Database DataBase;
    LinguisticDataManager(Database DB){
        this.DataBase = DB;
         this.FManager = new FitnessManager(this.DataBase);
    }

    private FitnessManager FManager;
    public void FetchData(String Ciphertext){
        SHandler.setCipherText(Ciphertext);
        long startTime = System.currentTimeMillis();
        FManager.SetTetraFitness(SHandler.GetCiphertext(),SHandler.getLocalTetragramFrequencies());
        FManager.SetMonoFitness(SHandler.getLocalMonogramFrequencies());

        double MonoFitness = FManager.getMonoFitness();
        double TetraFitness = FManager.GetTetraFitness();
        long endTime = System.currentTimeMillis();
        System.out.println("Found in "+(endTime-startTime)+" ms");
        System.out.println("Monogram fitness of "+MonoFitness+ " Tetragram fitness of "+TetraFitness);

    }
}
