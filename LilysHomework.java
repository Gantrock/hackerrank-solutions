import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int lilysHomework(int[] arr) {
        int count = 0;
        int index = 0;
        
        //Key is the value from arr and the Value is the index of the value in arr
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        
        int[] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sorted);        
        /*we iterate n times through the input list and compare it to a presorted version of said input. If the element at index i isn't correct we find it's position from the sorted version and swap. We also increment the count indicating how many swaps we've performed.*/
        for(int i = 0; i < arr.length; i++) {
            if(sorted[i] != arr[i]) {
                count++;
                index = map.get(sorted[i]);
                map.replace(arr[i], index);
                swap(i, index, arr);
            }
        }

        return count;
    }

    static void swap(int indOne, int indTwo, int[] arr) {
        int temp = arr[indOne];
        arr[indOne] = arr[indTwo];
        arr[indTwo] = temp;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];
        int[] rev = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
            rev[i] = arrItem;
        }
        
        int temp = 0;
        for(int r = 0; r < n / 2; r++) {
            temp = rev[r];
            rev[r] = rev[(n-1)-r];
            rev[(n-1)-r] = temp;
        }

        int result = Math.min(lilysHomework(arr), lilysHomework(rev));

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
