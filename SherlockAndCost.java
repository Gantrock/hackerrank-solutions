import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

/**
* No I don't know why it references Sherlock either.
*/
public class Solution {

    // Complete the cost function below.
    static int cost(int n, int[] B) {
        int max = 0;
        //Create a 2d array in order to track two possibilities
        long[][] dynamic = new long[2][n];

        //For every index from 1 to n-1
        for(int i = 1; i < n; i++) {
        
            /*Set the dynamic array at [0][index] to the Max value between two possibilies:
            1) the previous value in the first row of the dynamic array added to the absolute value of the current and previous entry in the inital array.
            2) the previous value in the second row of the dynamic array added to the absolute value of the current value of the inital array minus 1*/
            
            dynamic[0][i] = Math.max(dynamic[0][i-1] + Math.abs(B[i] - B[i-1]), dynamic[1][i-1] + Math.abs(B[i] - 1));
            
            /*Set the dynamic array at [1][index] to the Max value between two possibilities:
            1)The previous value of the first row of the dynamic array added to the absolute value of the previous value of the initial array minus 1 much like the second possibility for the above array
            2)The previous value of the second row of the dynamic array*/
            
            dynamic[1][i] = Math.max(dynamic[0][i-1] + Math.abs(B[i-1] - 1), dynamic[1][i-1]);
        }
        
    //return the max value of either the dynamic array's final element from the first or second row
    return (int)Math.max(dynamic[0][n-1], dynamic[1][n-1]);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] B = new int[n];

            String[] BItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int BItr = 0; BItr < n; BItr++) {
                int BItem = Integer.parseInt(BItems[BItr]);
                B[BItr] = BItem;
            }

            int result = cost(n, B);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
