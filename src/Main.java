
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.knowm.xchart.*;
import org.knowm.xchart.internal.chartpart.Cursor;
import org.knowm.xchart.style.Styler;

import javax.swing.*;

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

        MonoAlphabeticDecryptor MD = new MonoAlphabeticDecryptor(CipherText, DB,LDM.IOCs);
String plaintext;
        long starTime = System.currentTimeMillis();
        if (LDM.IOC>=(1.5/26)&& LDM.TetraFitness<-50) {
             plaintext = MD.DecryptCipher();
        } else if (LDM.IOC<=(1.5/26)&&LDM.TetraFitness<-50) {
           plaintext = "POLYALPHABETIC SUBSTITUTION CIPHER";
            DecryptionParent DP = new DecryptionParent(CipherText,DB,LDM.IOCs);
            //XYSeries IoCs = new XYSeries("Indexes of coincidence");

            //for(int i =1; i< LDM.IOCs.length+1;i++){
                //IoCs.add(i,LDM.IOCs[i-1]);
            //}
            XYChart IndexChart = new XYChartBuilder().width(600).height(600).title("Index of coincidence curve").xAxisTitle("period").yAxisTitle("Index of coincidence").build();
            IndexChart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
            XChartPanel indexPanel = new XChartPanel<>(IndexChart);
            indexPanel.setCursorEnabled(true);
            IndexChart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Area);
            IndexChart.addSeries("Index of coincidence",LDM.IOCs);
            IndexChart.getStyler().setCursorColor(Color.red);
            IndexChart.getStyler().setCursorFontColor(Color.blue);
            IndexChart.getStyler().setPlotBackgroundColor(Color.BLACK);
            IndexChart.getStyler().setPlotGridLinesColor(Color.white);
            IndexChart.getStyler().setChartBackgroundColor(Color.black);
            IndexChart.getStyler().setChartFontColor(Color.white);
            IndexChart.getStyler().setAxisTickLabelsColor(Color.white);
            IndexChart.getStyler().setLegendBackgroundColor(Color.black);
            JFrame frame = new JFrame();
            frame.add(indexPanel);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        } else{
            plaintext = CipherText+" ///NOT A SUBSTITUTION CIPHER";
       }
        long endTime = System.currentTimeMillis();

        System.out.println(endTime-starTime);
        System.out.println(plaintext);
/*
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
//Frame.setVisible(true);
    }*/
    }
}

