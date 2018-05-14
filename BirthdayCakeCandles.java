import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the birthdayCakeCandles function below.
     */
    static int birthdayCakeCandles(int n, int[] ar) {
        //Set a count and a max value.      
        int count = 1;
        //The max is set to Integer.MIN_VALUE so that all int values will be higher.
        int max = Integer.MIN_VALUE;
        //Iterate through the array until we finish
        for(int i = 0; i < ar.length; i++) {
            //If ar[i] is greater than max we set ar[i] as the new max and reset count to 1.
            if(ar[i] > max) {
                max = ar[i];
                count = 1;
            //If ar[i] is the same size as the biggest candle we increment count.
            } else if (ar[i] == max) {
                count++;
            }
        }
        return count;
    }

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(scan.nextLine().trim());

        int[] ar = new int[n];

        String[] arItems = scan.nextLine().split(" ");

        for (int arItr = 0; arItr < n; arItr++) {
            int arItem = Integer.parseInt(arItems[arItr].trim());
            ar[arItr] = arItem;
        }

        int result = birthdayCakeCandles(n, ar);

        bw.write(String.valueOf(result));
        bw.newLine();

        bw.close();
    }
}
