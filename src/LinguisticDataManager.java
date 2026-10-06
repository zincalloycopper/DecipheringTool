public class LinguisticDataManager {

    private StringHandler SHandler = new StringHandler();

    private  Database DataBase;
    LinguisticDataManager(Database DB){
        this.DataBase = DB;
         this.FManager = new FitnessManager(this.DataBase);
    }

    private FitnessManager FManager;
    public void FetchData(String Ciphertext) {
        double MonoFitness = 0;
        double TetraFitness = 0;

            SHandler.setCipherText(Ciphertext);
        long startTime = System.nanoTime();
        FManager.SetTetraFitness(SHandler.GetCiphertext(), SHandler.getLocalTetragramFrequencies());
        //FManager.SetMonoFitness(SHandler.getLocalMonogramFrequencies());
        //MonoFitness = FManager.getMonoFitness();
        TetraFitness = FManager.GetTetraFitness();

        long endTime = System.nanoTime();
        System.out.println("Found in "+(endTime-startTime)+" nanoseconds");
        System.out.println("Monogram fitness of "+MonoFitness+ " Tetragram fitness of "+TetraFitness);

    }

}
