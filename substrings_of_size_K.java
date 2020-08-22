/*
I used a sliding window approach to look at substrings of length K in every iteration. To accomplish this, I
created a counts array of 26 (lowercase alphabets as inputs only) which will keep track of count of a character
in current window. If a character is seen for the first time, the count array will have it's value as 0, we
increment the distinct character count to track number of distinct characters. If the length matches and the
number of distinct characters is equal to K-1, then add it to the result. Whenever the window length is
beyond K, we shrink the window forward. When we shrink the window, we will decrement count of the
character in counts array. When we reach 0, then we decrement the distinct character count;
Whatis the run time complexity of your solution for this code question.
Runtime: O(size(inputString)) Memory: O(26) = O(1) ignoring the result list size
Time Complexity is O(N) N is length of the s.
*/
public class Main {
    
    public List<String> subStringsLessKDist(String inputString, int num)
    {

        if(inputString == null || inputString.length() == 0 || num > inputString.length()) return new ArrayList();

        // Using set to remove duplicates in the result or else only 8 of 16test cases are passing
        Set<String> set = new HashSet<>();
        int start = 0;

        int[] count = new int[26];

        int uniqueCount = 0;

        for(int end = 0; end < inputString.length(); end++) {
            if(count[inputString.charAt(end) - 'a'] == 0) {
                uniqueCount++;
            }
            count[inputString.charAt(end)-'a']++;

            while(end - start + 1 > num || uniqueCount > num-1) {
                count[inputString.charAt(start)-'a']--;
        
                if(count[inputString.charAt(start) - 'a'] == 0) uniqueCount--

                start++;
            }

            // if it meets our requirement, add to the result
            if(end - start + 1 == num && uniqueCount == num - 1) {
                set.add(inputString.substring(start, end + 1));
            }
        }

        return new ArrayList(set);
    }
}

