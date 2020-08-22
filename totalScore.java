/*
Basicly run iterate through the blocks and check the its symbol and calculate the score accordingly. Must be
cautious with the corner cases like not having enough scores to calculate the 'Z'.

Time Complexity: O(n) n is length of the input blocks;
*/
public class Main {
    public static void main(String[] args) {
        String[] tmp = new String[]{"5", "-2", "4", "Z", "X", "9", "+", "+"};
        List test = new ArrayList<>(Arrays.asList(tmp));
        System.out.println(totalScore(8,test));
    }
    
    public static int totalScore(int num, List<String> blocks)
    {
        List<Integer> scores = new ArrayList<>();
        for (String block : blocks) {
            int lastScore = scores.size() >= 1 ? scores.get(scores.size()-1) : 0;
            int newScore;

            if (block.equals("X")) {
                newScore = lastScore * 2;
            } else if (block.equals("+")) {
                if (scores.size() < 2) {
                    if (scores.size() == 1) {
                        newScore = scores.get(0);
                    } else {
                        newScore = 0;
                    }
                } else {
                    int len = scores.size();
                    newScore = scores.get(len - 2) + scores.get(len - 1);
                }
            } else if (block.equals("Z")) {
                if (scores.size() > 0) {
                    scores.remove(scores.size()-1);
                }
                continue;
            } else {
                try {
                    newScore = Integer.parseInt(block);
                } catch(Exception e) {
                    newScore = 0;
                }
            }
    
            scores.add(newScore);
        }

        int result = 0;
        for(int score: scores) {
            result += score;
        }
        return result;
    }
}
