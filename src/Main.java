
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
public class Main {
    public static void main(String[] args) {
        //String[] tetras= new String[Math.powExact(26,4)]; //The number of tetragrams in english is 26^4, or 456976
        Scanner UIS = new Scanner(System.in);
        /*HashFunction Hash = new HashFunction();
        String Text = UIS.nextLine();
        Hash.SetTetra(Text);
        int index = Hash.getB26Val();
        System.out.println(index);
        tetras[index] = Text;
        System.out.println(tetras[index]);

        */
        //CorpusCreator CC = new CorpusCreator();
        //CC.CreateTetraCorpus();
        //CC.CreateMonoCorpus();

        Database DB = new Database();

        String CipherText = UIS.nextLine();
        LinguisticDataManager LDM = new LinguisticDataManager(DB);
        LDM.FetchData(CipherText);

        MonoAlphabeticDecryptor MD = new MonoAlphabeticDecryptor(CipherText, DB);

        long starTime = System.currentTimeMillis();
       String plaintext = MD.DecryptCipher();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-starTime);
        System.out.println(plaintext);

        XYSeries Fitnesses = new XYSeries("Fitness");
        XYSeries Iterations = new XYSeries("Iterations");
     for(int i =0; i< MD.Indexes.size();i++){
         Fitnesses.add(MD.Indexes.get(i), MD.BestFitnesses.get(i));
         System.out.println(MD.Indexes.get(i)+" "+ MD.BestFitnesses.get(i));
         //Iterations.add(MD.BestFitnesses.get(i), MD.Indexes.get(i));

     }
XYSeriesCollection Dataset = new XYSeriesCollection();
Dataset.addSeries(Fitnesses);
Dataset.addSeries(Iterations);
JFreeChart Chart = ChartFactory.createXYLineChart("Iterations vs fitness","Iterations","Fitness",Dataset);
ChartFrame Frame = new ChartFrame("Trace of Hill climber",Chart);
Frame.pack();
Frame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
Frame.setVisible(true);
    }
    }

