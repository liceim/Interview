public class MinMoveStep {
	public static int minMoveStep(String str) {
		int steps = 0;
		for (int i = 0; i < str.length(); ++i) {
			int moveLength = 1;
			for (; i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1); ++i) {
				moveLength++;
			}
			steps += moveLength / 3;
		}
		return steps;
	}
	
	public static void main(String[] args) {
		System.out.println(minMoveStep("abbbbbbbbbbb"));
	}
}
