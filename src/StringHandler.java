public class StringHandler {

    private double[] LocalMonogramFrequencies = new double[26];
    private int[] LocalTetragramFrequencies = new int[Math.powExact(26,4)];
    private String CipherText;

    public void setCipherText(String Ciphertext){
        CipherText = Ciphertext;
    }
}
