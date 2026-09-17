public class LinguisticDataManager {
    private StringHandler SHandler = new StringHandler();
    private FitnessManager FManager = new FitnessManager();

    public void FetchData(String Ciphertext){
        SHandler.setCipherText(Ciphertext);
        FManager.SetTetraFitness(SHandler.GetCiphertext(),SHandler.getLocalTetragramFrequencies());
        FManager.SetMonoFitness(SHandler.getLocalMonogramFrequencies());
        System.out.println("Monogram fitness of "+FManager.getMonoFitness()+" And Tetragram Fitness of "+FManager.GetTetraFitness());
    }
}
