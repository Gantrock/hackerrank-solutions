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
        //The number of notifications we will be returning
        int notes = 0;
        //perform d transactions first, adding the number to the trailing list and starting Counting Sort.
        for(int i = 0; i < d; i++) {
            trailing.add(expenditure[i]);
            pastActivity[expenditure[i]] = pastActivity[expenditure[i]] + 1;
}
        //starting fromd (because we've already done those transactions) we begin to check for fraud.
        for(int i = d; i < expenditure.length; i++) {
                //Check the current value against the median. If the current value is greater than ir equal to 2 * median we increment notes.
                if(expenditure[i] >= (2 * median(pastActivity, d))){
                    notes++;
                }
            //The oldest value so that we can properly decerement the Counting Sort array.
                oldest = trailing.poll();
                pastActivity[oldest] = pastActivity[oldest] - 1;
            
            //Add the new value.
                trailing.add(expenditure[i]);
                pastActivity[expenditure[i]] = pastActivity[expenditure[i]] + 1;
        }
        return notes;
    }
    
    //Finds the median value using the system described in the problem.
    static double median(int[] past, int number) {
        int index = 0;
        int counter = number / 2;
        
        //If number is even we use the even method.
        if(number % 2 == 0) {
            //Iterate through the past array subtracting the value from each counted index as we go.
            while(counter > 0) {
                counter -= past[index];
                index++;
            }
            //remove final iteration.
            index--;
            //If our index is before the array obviously that's a problem so we just return it.
            if(counter <= -1) {
                return index;
            } else {//We take the average of the two middle values.
                int firDex = index;
                int secDex = index + 1;
                //Iterate through past until we find a none-zero count.
                while(past[secDex] == 0) {
                    secDex++;
                }
                return (double) (firDex + secDex) / 2.0;
            }
        } else {//If the number is odd use the odd method.
            while(counter >= 0) {
                counter -= past[index];
                index++;
            }
            //return the middle item.
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
