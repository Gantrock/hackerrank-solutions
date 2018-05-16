import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int hackerlandRadioTransmitters(int[] x, int k) {
        //Our result.
        int transmitters = 0;
        //We will use a While loop to traverse the array so we need to initialize the index outside the loop.
        int index = 0;
        // We utilize a greedy algorithm to find the ideal location and this variable will track the ideal address
        int idealLoc = 0;
        
        //Arrays.sort is O(n log n)
        //We have to sort the array because the algorithm requires it.
        Arrays.sort(x);
        //While our index value isn't out of bounds.
        while(index < x.length) {
            //Set the idealLoc as the sum of our current value(x[index] and the radio transmitters range (k).
            idealLoc = x[index] + k;
            transmitters++;
            /*Iterate through the array checking to see if the value at index in the array is less than or equal to the idealLoc and whether we've reached the end of the array.
              If value is equal to our ideal location then we've found where you would put the transmitter we promised above.
              If the value is greater than the ideal location then there wasn't a proper spot at the ideal location.
              Regardless of the outcome we iterate through once more*/
            while(index < x.length && x[index] <= idealLoc) index++;
            /*We subtract one from the index because we will always increment the array once upon finding the spot we need.
              We then set the idealLoc again to represent the forward range of the transmitter*/
            idealLoc = x[index-1] + k;
            //Increment through the array again until we either find the end of the array or we find the end of the transmitter range
            while(index < x.length && x[index] <= idealLoc) index++;
            //Since this particular piece of logic will always increments once more then we need we can garauntee that we'll be outside of the transmitter range.
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
