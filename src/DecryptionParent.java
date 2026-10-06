public class DecryptionParent {

protected String Ciphertext;
protected char[] StartKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
DecryptionParent(String Ciphertext, Database DB){
    SHandler.setCipherText(Ciphertext);
    this.Ciphertext = SHandler.GetCiphertext();
    this.FManager = new FitnessManager(DB);
}
protected FitnessManager FManager;
protected StringHandler SHandler = new StringHandler();


protected double getTetraFitness(String Ciphertext){
    FManager.SetTetraFitness(Ciphertext,SHandler.getLocalTetragramFrequencies());
    return FManager.GetTetraFitness();

}

}
