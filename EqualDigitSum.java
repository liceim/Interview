public class Maxdigitalsum {
	public static int calculateDigitalSum(int num) {
		int sum = 0;
		while (num != 0) {
			sum += num % 10;
			num = num / 10;
		}
		
		return sum;
	}
	
	public int maxSum(int[] A) {
		if (A == null || A.length < 2) {
			return -1;
		}
		
		int res = Integer.MIN_VALUE;
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < A.length; ++i) {
			int num = calculateDigitalSum(A[i]);
			if (!map.containsKey(num)) {
				map.put(num, A[i]);
			} else {
				res = Math.max(res, map.get(num) + A[i]);
				map.put(num, Math.max(A[i], map.get(num)));
			}
		}
		
		return res == Integer.MIN_VALUE ? -1 : res;
	}
	
	public static void main(String[] args) {
		Maxdigitalsum main = new Maxdigitalsum();
		int[][] testcases = {{51, 71, 17, 42, 33, 44, 24, 62}, 
	                         {51, 71, 17, 42},
	                         {42, 33, 60},
	                         {51, 32, 43}};
		
		for(int[] testcase: testcases)
			System.out.println(main.maxSum(testcase));
	}
}
