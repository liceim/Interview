/*
guaranteed to have a word following an identifier (allows me to use indexOf ' ' freely).
letter logs need to be ordered lexicographically, so we can use built in compare function when we know we have two.
number logs need to be sorted naturally, so we just say they're all "equal" to eachother and trust java's built in sort feature to be stable.
number logs need to be after letter logs, so once we find out they're different, we return one of the other and short-circuit.

Time Complexity: O(nlogn), .

Space Complexity: .
*/

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Comparator<String> myCmp = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int s1Index = s1.indexOf(' ');
                int s2Index = s2.indexOf(' ');
                
                char s1fc = s1.charAt(s1Index + 1);
                char s2fc = s2.charAt(s2Index + 1);
                
                if (s1fc <= '9') {
                    if (s2fc <= '9') {
                        return 0;
                    } else {
                        return 1;
                    }
                }
                
                if (s2fc <= '9') {
                    return -1;
                }
                
                int postCheck = s1.substring(s1Index + 1).compareTo(s2.substring(s2Index + 1));
                
                if (postCheck == 0) {
                    return s1.substring(0, s1Index).compareTo(s2.substring(0, s2Index));
                }
                
                return postCheck;
            }
            
        };
        
        Arrays.sort(logs, myCmp);
        return logs;
    }
}
