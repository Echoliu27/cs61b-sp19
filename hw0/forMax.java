public class forMax {
    /** Returns the maximum value from m. */
    public static int forMax(int[] m) {
    	int max_num = 0;
    	for (int i = 0; i < m.length; i += 1){
            if (m[i] > max_num){
                max_num = m[i];
            }
        }
        return max_num;
    }
    public static void main(String[] args) {
       int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};  
       System.out.println(forMax(numbers));    
    }
}