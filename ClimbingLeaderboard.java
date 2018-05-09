import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the climbingLeaderboard function below.
     */
    static int[] climbingLeaderboard(int[] scores, int[] user) {
        //A value one less than the length of the user array, used to perform operations
        int userDex = user.length - 1;
        //The array we will return, contains the same amount of cells as the user has scores
        int[] result = new int[user.length];
        //The score for the current rank, is initialized to Integer.MAX_VALUE so that any integer will be lower than it at first.
        int scoreForRank = Integer.MAX_VALUE;
        //The currentRank to be used with the result array
        int currentRank = 0;
        /*iterate through scores tracking the currentRank and the score needed to attain that rank, if User scores higher than scoreForRank user receives currentRank, if User score is equal to scoreForRank user receives currentRank, if User scores below scoreForRank we move forward, if we reach the end of the list and User has not exceeded any scoreForRank user receives currentRank+1*/
        for(int i = 0; i < scores.length; i++) {
        /*If the score at i is less than the current scoreForRank we increment the currentRank (since we're working on a new rank). This lets us skip scores that we've already checked. */
            if(scores[i] < scoreForRank) {
                currentRank++;
                scoreForRank = scores[i];
                /*We iterate through the user array from the end to the beginning, we do this because we know the user scores are sorted with the highest value in the final spot. If we find a value in user that matches or exceeds the scoreForRank we add the currentRank to cell j in the result array. Decrement the userDex so that we don't have to look for other values with similar conditions.*/
                for(int j = userDex; j >= 0; j--) {
                    /*If the user's score is greater than or equal to the scoreForRank then we give the user the currentRank at the current userDex*/
                    if(alice[j] >= scoreForRank) {
                        result[j] = currentRank;
                        userDex--;
                    } else {
                        //Since we know the User scores are sorted we can exit the loop once we are no longer meeting requirements.
                        j = -1;
                    }
                }
            }

        }
        //increment the rank because we know that any values after this will have a "lower" rank than the values in scores.
        currentRank++;
        //if we didn't reach the front of the user array we know that every score that we didn't reach is lower than the currentScoreForRank
        if(userDex >= 0) {
        //iterate through the remaining indexes of result, from back to front, adding the currentRank to every cell we reach.
            for(int j = userDex; j >= 0; j--) {
                result[j] = currentRank;
            }
        }
        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = Integer.parseInt(scanner.nextLine().trim());

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");

        for (int scoresItr = 0; scoresItr < scoresCount; scoresItr++) {
            int scoresItem = Integer.parseInt(scoresItems[scoresItr].trim());
            scores[scoresItr] = scoresItem;
        }

        int userCount = Integer.parseInt(scanner.nextLine().trim());

        int[] user = new int[userCount];

        String[] userItems = scanner.nextLine().split(" ");

        for (int userItr = 0; userItr < userCount; userItr++) {
            int userItem = Integer.parseInt(userItems[userItr].trim());
            user[userItr] = userItem;
        }

        int[] result = climbingLeaderboard(scores, user);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
