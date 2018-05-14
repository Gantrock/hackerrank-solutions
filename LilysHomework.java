import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int lilysHomework(int[] arr) {
        //The count of how many swaps we performed.
        int count = 0;
        //If we need to swap values this variable will contain the index for the second value.
        int index = 0;
        
        //Key is the value from arr and the Value is the index of the value in arr
        HashMap<Integer, Integer> map = new HashMap<>();
        //add every element from the input array into the map.
        for(int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }
        
        //Make a shallow copy of the initial array.
        int[] sorted = Arrays.copyOf(arr, arr.length);
        //Sort the sorted array
        Arrays.sort(sorted);        
        /*we iterate n times through the input list and compare it to a presorted version of said input. If the element at index i isn't correct we find it's position from the sorted version and swap. We also increment the count indicating how many swaps we've performed.*/
        for(int i = 0; i < arr.length; i++) {
            if(sorted[i] != arr[i]) {
                //increment the number of swaps we made
                count++;
                //Find the index in the map for the value we want to swap with the value at i
                index = map.get(sorted[i]);
                //Replace the index of the current value in the map.
                map.replace(arr[i], index);
                swap(i, index, arr);
            }
        }

        return count;
    }
    
    //Perform a basic array swap with two indexes and the array.
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
        //An array that will contain all of the values of arr but will eventually be in reverse order.
        int[] rev = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
            rev[i] = arrItem;
        }
        //Reverse the order of the rev array
        for(int r = 0; r < n / 2; r++) {
            swap(r, (n-1)-r, rev);
        }
        
        //result is the minimum value between performing the lilysHomework method on the original and reversed arrays.
        int result = Math.min(lilysHomework(arr), lilysHomework(rev));

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
