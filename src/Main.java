
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
        //System.out.println(MD.DecryptCipher());
        Path Read = Path.of("CorpusToRead");
        Map<Integer, Double> testvalues = new HashMap<>();
        Random rand = new Random();
        FitnessManager TestFitness = new FitnessManager(DB);
        StringHandler TestStringHandler = new StringHandler();
        try {
            String CorpusText = Files.readString(Read);

            System.out.println("Creating Graph. . .");
            for (int i = 1; i < 5000; i++) {
                int Start = rand.nextInt(0, CorpusText.length());
                try {
                    TestStringHandler.setCipherText(CorpusText.substring(Start, Start + i));
                } catch (StringIndexOutOfBoundsException e) {
                    Start = Start - i;
                    TestStringHandler.setCipherText(CorpusText.substring(Start, Start + i));
                }
                CipherText = TestStringHandler.GetCiphertext();
                testvalues.put(i, TestFitness.GetTetraFitness());
                TestFitness.SetTetraFitness(CipherText, TestStringHandler.getLocalTetragramFrequencies());
                //System.out.println(i + " " + TestFitness.GetTetraFitness());
                testvalues.put(i, TestFitness.GetTetraFitness());
            }
            CipherText = CorpusText;
            LDM.FetchData(CipherText);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(testvalues);
        XYSeries series = new XYSeries("Tetragram Fitness");

// Add data points from your map
        for (Map.Entry<Integer, Double> entry : testvalues.entrySet()) {
            series.add(entry.getKey(), entry.getValue());
        }

// Create dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

// Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Tetragram Fitness vs String Length", // Chart title
                "String Length",                      // X-axis label
                "Tetragram Fitness",                  // Y-axis label
                dataset,                              // Data
                PlotOrientation.VERTICAL,
                true,                                 // Include legend
                true,                                 // Tooltips
                false                                 // URLs
        );

// Display chart in a frame
        ChartFrame frame = new ChartFrame("Fitness Chart", chart);
        frame.pack();
        frame.setVisible(true);

    }


}
