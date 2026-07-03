import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        String[] tetras= new String[Math.powExact(26,4)]; //The number of tetragrams in english is 26^4, or 456976
        Scanner UIS = new Scanner(System.in);
        HashFunction Hash = new HashFunction();
        String Text = UIS.nextLine();
        Hash.SetTetra(Text);
        int index = Hash.getB26Val();
        System.out.println(index);
        tetras[index] = Text;
        System.out.println(tetras[index]);
    }
}

