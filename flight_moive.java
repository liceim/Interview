/*
maintain a copy of movieDuration and sort it.
use two pointers from left(biggest ones) + right (smallest ones), if the sum is smaller than d and then larger than the current maximum, then we find a solution.
update ans with the index, here we have to refer the index to the original movieDuration array.
Time Complexity : O(NlogN)
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

    private static PairInt get2SumClosest(int flightDuration, int numMovies, List<Integer> movieDuration) {
        if (flightDuration <= 30 || numMovies == 0) {
            return new PairInt(-1, -1);
        }
        
        flightDuration -= 30;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<movieDuration.size(); i++) {
            map.putIfAbsent(movieDuration.get(i), new ArrayList<>());
            map.get(movieDuration.get(i)).add(i);
        }
        
        Collections.sort(movieDuration); 
        int l = 0, r = movieDuration.size() - 1;
        int max = 0;
        int[] res = new int[]{-1, -1};
        while(l < r) {
            int sum = movieDuration.get(l) + movieDuration.get(r);
            if((sum > max || (sum == max && Math.max(movieDuration.get(l) , movieDuration.get(r)) > Math.max(res[0],  res[1]))) && sum <= flightDuration) {
                max = sum;
                res[0] = movieDuration.get(l);
                res[1] = movieDuration.get(r);
            }
            if(sum > flightDuration)
                r--;
            else
                l++;
        }
        //System.out.println(res[0] + " " + res[1]);
        if(map.get(res[0]) == map.get(res[1])) {
            res[0] = map.get(res[0]).get(0);
            res[1] = map.get(res[1]).get(1);
        }else {
            res[0] = map.get(res[0]).get(0);
            res[1] = map.get(res[1]).get(0);
        }
        
        if (res[0] > res[1]) {
            int tmp = res[0];
            res[0] = res[1];
            res[1] = tmp;
        }
        return new PairInt(res[0], res[1]);
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
