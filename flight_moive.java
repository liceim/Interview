/*
Time Complexity : O(N)
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

    public static void main(String[] args) {
        test(Arrays.asList(1, 10, 25, 35, 50, 70), 90);
        test(Arrays.asList(20, 50, 40, 25, 30, 10), 90);
        test(Arrays.asList(5, 55, 40, 20, 30, 30), 90);
    }
    
    private static void test(List<Integer> nums, int target) {
        PairInt res = findPair(90, 0, nums);
        System.out.println(res.first + " " + res.second);
    }
}
