public class PolyalphabeticDecryptor extends DecryptionParent {
    Database DB;
    double[] IoCs;
    private MonoAlphabeticDecryptor MD;
    PolyalphabeticDecryptor(String Ciphertext, Database DB, double[] IoCs, int period) {
     super(Ciphertext,DB,IoCs);
     this.DB= DB;
     this.IoCs = IoCs;
        Plaintext = DecryptCipher();
         MD = new MonoAlphabeticDecryptor(Ciphertext,DB,IoCs);

    }

    private int period = super.period;
    public String Plaintext;

    private String DecryptCipher() {
        String[] slices = new String[period];
        StringBuilder Slicer = new StringBuilder();
        for (int S = 0; S < slices.length; S++) {
            for (int L = 0; L < Ciphertext.length(); L++) {
                if (L % period == S) {
                    Slicer.append(Ciphertext.charAt(L));
                }
            }
            String Plaintext;
            for (int i = 0; i < period; i++) {



            }
        }
        for (int n = 0; n < (1000000 * ((period) * (period))); n++) {

        }
        return Plaintext;
    }
}
