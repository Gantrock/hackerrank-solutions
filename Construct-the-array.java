import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static long countArray(int n, int k, int x) {
        long MAX = 1000000007;
        long[] first = new long[n+1];
        long[] sec = new long[n+1];
        //Put our increment at index 3 in prev
        first[3] = k - 1;
        /*On the next to final step any previous element which matched n will produce k -1 children whereas any element which does not match will only produce k - 2*/
        sec[3] = k - 2;
        //Our base case for calculations
        sec[2] = 1;
        /*We start at 4 because the first element follows a non-standard pattern and the second is trivial to calculate, the third element essentially serves as our basecase*/
        for(int i = 4; i <= n; i ++) {
            //Our previous element multiplied by our increment (k-1),then modulo by MAX. We then add this element to the first array at the current index.
            first[i] = (k-1) * sec[i-1] % MAX;
            /*Our second increment type multiplied by the prev value of this track, this is added to the value of the previous value, then modulo by MAX. We then add this to the second array at the current index*/
            sec[i] = (first[i-1] + (k-2) * sec[i-1]) % MAX;
        }
        /*If we start and end with one we end up with fewer possibilities. This alters the amount  of possibilities we add on the next to last step*/
        return x == 1 ? first[n] : sec[n];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int x = in.nextInt();
        long answer = countArray(n, k, x);
        System.out.println(answer);
        in.close();
    }
}
