public class HashFunction {
    //this function is designed to increase lookup speed by using direct access
    private String Tetra;
    private int B26Val;

    private int hash(String t){
        //converts the Tetragram into a base 26 number ready to be stored in the master array.
        int value = 0;
        int count = t.length()-1;
        int temp = 0;
        t = t.toUpperCase();
        for (char x: t.toCharArray()){
            temp = x-65;
            value+= temp*(Math.powExact(26,count));
            count--;
        }
        //System.out.println(value);
        return value;
    }
    //gets the base 26 value of the tetragram
        public int getB26Val(){
       B26Val= hash(Tetra);
        return B26Val;
    }

    public void SetTetra(String t){
        //passes the tetragram into the hash function
        Tetra = t;
    }
}
