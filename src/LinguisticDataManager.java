import java.util.Arrays;

public class LinguisticDataManager {

    private StringHandler SHandler = new StringHandler();

    private  Database DataBase;
    LinguisticDataManager(Database DB){
        this.DataBase = DB;
         this.FManager = new FitnessManager(this.DataBase);
    }
    public double[] IOCs = new double[50];
    public double MonoFitness;
    public double TetraFitness;
    public double IOC;
    private FitnessManager FManager;
    private MathsHandler Mhandler = new MathsHandler();
    public void FetchData(String Ciphertext) {
         MonoFitness = 0;
         TetraFitness = 0;
            long basicstarttime = System.nanoTime();
            SHandler.setCipherText(Ciphertext);
            long basicendtime = System.nanoTime();
            System.out.println("converted ciphertext in"+ (basicendtime-basicstarttime)+ "nanoseconds");
        long startTime = System.nanoTime();
        FManager.SetTetraFitness(SHandler.GetCiphertext(), SHandler.getLocalTetragramFrequencies());
        FManager.SetMonoFitness(SHandler.getLocalMonogramFrequencies());
        MonoFitness = FManager.getMonoFitness();
        TetraFitness = FManager.GetTetraFitness();

        long endTime = System.nanoTime();
        IOC = Mhandler.IndexOfCoincidence(SHandler.GetCiphertext());
        System.out.println(Mhandler.PeriodIOC(SHandler.GetCiphertext(), 4));

        for (int i =1; i<51;i++){
            //System.out.println(Mhandler.PeriodIOC(SHandler.GetCiphertext(), i));
            IOCs[i-1] = Mhandler.PeriodIOC(SHandler.GetCiphertext(), i);
        }
        for(double I: IOCs){

        }
        //System.out.println(Arrays.stream(IOCs).max());
        System.out.println("Found in "+(endTime-startTime)+" nanoseconds");
        System.out.println("Monogram fitness of "+MonoFitness+ " Tetragram fitness of "+TetraFitness+" IOC of "+IOC);

    }

}
