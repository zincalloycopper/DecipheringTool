import java.util.Arrays;

public class DecryptionParent {

protected String Ciphertext;
protected char[] StartKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
DecryptionParent(String Ciphertext, Database DB, double[] IoCs){
    SHandler.setCipherText(Ciphertext);
    this.Ciphertext = SHandler.GetCiphertext();
    this.FManager = new FitnessManager(DB);
    this.period = FindPeriod(IoCs);
}
protected FitnessManager FManager;
protected StringHandler SHandler = new StringHandler();

protected int period;
int FindPeriod(double[] IoCs){
    int LocalPeriod=0;
    double max = Arrays.stream(IoCs).max().getAsDouble();
double threshold = max*0.1;
System.out.println(max +threshold);
System.out.println(max-threshold);
int address= 0;
for(int i = 0; i <IoCs.length;i++){
    if( IoCs[i] == max){

        address = i;
    }
}
System.out.println(address);
for (int i = 0; i<address;i++){
    System.out.println(IoCs[i]);
    if (IoCs[i]>(max-threshold) && IoCs[i]<(max+threshold)){
       LocalPeriod = i+1;
    }
}
if(LocalPeriod==0){
    LocalPeriod = address+1;
}


//System.out.println(Arrays.toString(scores));
System.out.println("period is "+LocalPeriod);

    return LocalPeriod;
}

protected double getTetraFitness(String Ciphertext){
    FManager.SetTetraFitness(Ciphertext,SHandler.getLocalTetragramFrequencies());
    return FManager.GetTetraFitness();

}

}
