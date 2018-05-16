import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int hackerlandRadioTransmitters(int[] x, int k) {
        int transmitters = 0;
        int index = 0;
        int idealLoc = 0;
        
        //Arrays.sort is O(n log n)
        Arrays.sort(x);
        while(index < x.length) {
            idealLoc = x[index] + k;
            transmitters++;
            while(index < x.length && x[index] <= idealLoc) index++;
            idealLoc = x[index-1] + k;
            while(index < x.length && x[index] <= idealLoc) index++;
        }
        return transmitters;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] x = new int[n];

        String[] xItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int xItem = Integer.parseInt(xItems[i]);
            x[i] = xItem;
        }

        int result = hackerlandRadioTransmitters(x, k);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
