import java.io.IOException;
import java.nio.file.*;
import java.math.*;
public class Database {
    private MathsHandler Maths = new MathsHandler();
    private int[] TetraFrequencies = new int[Math.powExact(26,4)];
    private double[] TetraLogFrequencies = new double[Math.powExact(26,4)];
    private double[] MonoFrequencies = new double[26];
    private void LoadFrequencies(){

        HashFunction Hash = new HashFunction();
        Path ReadTetra = Path.of("TetrasWithFrequency.txt");
        CorpusCreator CC = new CorpusCreator();
        String[] Temp = new String[2];

        try{
            String TETRAS = Files.readString(ReadTetra);
            if(TETRAS.equals("")){ //ensures that the file is not empty;
                CC.CreateTetraCorpus();
            }
            else{
                String[] TempTetrasWithFrequencies = TETRAS.split("#"); // creates a temporary array to store the tetragram/value pairs
                for(String S:TempTetrasWithFrequencies){
                    Temp = S.split("/");
                    try {
                        System.out.println(Temp[0] + " " + Temp[1]);
                        Hash.SetTetra(Temp[0]);
                        int index = Hash.getB26Val();
                        TetraFrequencies[index] = Integer.valueOf(Temp[1]);
                        TetraLogFrequencies[index] = Maths.FetchLogOfTetra(Temp[0],TetraFrequencies);
                    }
                    catch (ArrayIndexOutOfBoundsException A){
                        System.out.println("End of Tetragrams");//any incomplete tetragrams would break the program otherwise, and these should be discounted anyway

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("no such file");
        }
    }

    private void LoadMonos(){

        Path ReadMono = Path.of("MonosWithFrequency.txt");
        CorpusCreator CC = new CorpusCreator();
        String[] Temp = new String[2];

        try{
            String MONOS = Files.readString(ReadMono);
            if(MONOS.equals("")){
                CC.CreateMonoCorpus();
            }
            else{
                String[] TempMonoFrequencies = MONOS.split("#");
                for(String S: TempMonoFrequencies){
                    Temp = S.split("/");
                    char C = Temp[0].toCharArray()[0];
                    MonoFrequencies[C-65] = Double.parseDouble(Temp[1]);

                }
            }

        } catch (IOException e) {
            System.out.println("No such file");
        }

    }

    public void INITIALISE(){
        LoadFrequencies();
        LoadMonos();
    }

    public int[] FetchTetras(){
        return TetraFrequencies;
    }
    public double[] FetchMonos(){
        return MonoFrequencies;
    }
    public double[] FetchTetraLogs(){
        return TetraLogFrequencies;
    }

}
