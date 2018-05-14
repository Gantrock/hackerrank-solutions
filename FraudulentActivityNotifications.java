import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    /* Takes an int array expenditure and the number of transactions to perform before checking for fraud d. Returns the number of potentially fraudulent transactions.
    */
    static int activityNotifications(int[] expenditure, int d) {
        //A Queue will let us keep track of the most recent transactions.
        Queue<Integer> trailing = new LinkedList<Integer>();
        //This array will be used for counting sort.
        int[] pastActivity = new int[201];
        //The oldest
        int oldest = 0;
        int notes = 0;
        for(int i = 0; i < d; i++) {
            trailing.add(expenditure[i]);
            pastActivity[expenditure[i]] = pastActivity[expenditure[i]] + 1;
}
        for(int i = d; i < expenditure.length; i++) {
                /*Establish the median, if even take the average of the two medium values, if odd simply take the middle value*/
                if(expenditure[i] >= (2 * median(pastActivity, d))){
                    notes++;
                }
                oldest = trailing.poll();
                pastActivity[oldest] = pastActivity[oldest] - 1;
            
                trailing.add(expenditure[i]);
                pastActivity[expenditure[i]] = pastActivity[expenditure[i]] + 1;
        }
        return notes;
    }
    
    static double median(int[] past, int number) {
        int index = 0;
        int counter = number / 2;
        
        if(number % 2 == 0) {           
            while(counter > 0) {
                counter -= past[index];
                index++;
            }
            //remove final iteration.
            index--;
            if(counter <= -1) {
                return index;
            } else {
                int firDex = index;
                int secDex = index + 1;
                while(past[secDex] == 0) {
                    secDex++;
                }
                return (double) (firDex + secDex) / 2.0;
            }
        } else {
            while(counter >= 0) {
                counter -= past[index];
                index++;
            }
            return (double) index-1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int d = in.nextInt();
        int[] expenditure = new int[n];
        for(int expenditure_i = 0; expenditure_i < n; expenditure_i++){
            expenditure[expenditure_i] = in.nextInt();
        }
        int result = activityNotifications(expenditure, d);
        System.out.println(result);
        in.close();
    }
}
