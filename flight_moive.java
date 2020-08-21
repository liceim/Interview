/*
The problem can be simplified into finding pair of movies which durations sum up to a certain value. Given a
movie with duration x, i can calculate its pair duration y. I need a fast O(1) way to look up y so i used a
HashMap. However using a HashMap to store duration of movie that i iterate through before result in us
requiring a O(N) space complexity. The algorithm is iterate through list of movies and for each movie check
HashMap to see if we already visited a movie that can form a pair. There is also additional checks to maintain
the movie pair with highest duration.

Time Complexity : O(N) where N is the number of movies. Iiterate through the movie list once
*/
class PairInt
{
    int first, second;
    PairInt(){}
    
    PairInt(int first, int second)
    {
        this.first = first;
        this.second = second;
    }
}

public class Main {
    
    public static PairInt findPair(int flightDuration, int numMovies, List<Integer> nums) {
        if (flightDuration <= 30 || numMovies == 0) {
            return new PairInt(-1, -1);
        }
        
        flightDuration -= 30;
        Map<Integer, Integer> map = new HashMap<>();
        PairInt result = new PairInt(-1, -1);
        int largest = 0;
        for (int i = 0; i < nums.size(); i++) {
            int complement = flightDuration - nums.get(i);
            if ((nums.get(i) > largest || complement > largest) && map.containsKey(complement)) {
                result.first = map.get(complement);
                result.second = i;
                largest = Math.max(nums.get(i), complement);
            }
            map.put(nums.get(i), i);
        }
        return result;
    }

    // CLASS BEGINS, THIS CLASS IS REQUIRED
    public class Solution {
        
        // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
        PairInt IDsOfmovies(int flightDuration, int numMovies,
                            ArrayList<Integer> movieDuration)
        {
            int durationSum = flightDuration -30;
            PairInt ans = new PairInt(-1,-1);
            int ansLongest = Integer.MIN_VALUE;
            Map<Integer, Integer> durationToIdMap = new HashMap<>();

            for(int i = 0; i < movieDuration.size(); i++){
                int duration = movieDuration.get(i);
                int targetDuration = durationSum - duration;
                int firstMovie = durationToIdMap.getOrDefault(targetDuration, -1);

                if(firstMovie != -1){
                    // we found match
                    int longestInThisPair = Math.max(duration, targetDuration);
                    if(longestInThisPair > ansLongest){
                        ans = new PairInt(firstMovie, i);
                        ansLongest = longestInThisPair;
                    }
                }

                durationToIdMap.put(movieDuration.get(i), i);
            }

            return ans;
        }
    // METHOD SIGNATURE ENDS
    }
    
    public static void main(String[] args) {
        test(Arrays.asList(1, 10, 25, 35, 50, 70), 90);
        test(Arrays.asList(20, 50, 40, 25, 30, 10), 90);
        test(Arrays.asList(5, 55, 40, 20, 30, 30), 90);
    }
    
    private static void test(List<Integer> nums, int target) {
        PairInt res = get2SumClosest(90, 6, nums);
        System.out.println(res.first + " " + res.second);
    }
}
