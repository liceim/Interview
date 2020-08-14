/*
The solution is to maintain a window and keep a count of every character in the window. Whenever the size of the window is equaled to k and only include 1 repetitive, add it to the set.
Time Complexity is O(N) N is length of the s.
*/
public class Main {
    
    public static List<String> kSubstring(String s, int k) {
        if (s == null || s.length() < k) {
            return new ArrayList<String>();
        }
        
        List<String> res = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        Set<Character> duplicate = new HashSet<>();
        for (int start = 0, end = 0; end < s.length(); ++end) {
            char c = s.charAt(end);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                duplicate.add(c);
            }
            
            if (end - start + 1 < k) {
                continue;
            }
            
            if (duplicate.size() == 1 && map.get(duplicate.iterator().next()) == 2) {
                res.add(s.substring(start, end + 1));
            }
            
            c = s.charAt(start);
            map.put(c, map.get(c) - 1);
            if (map.get(c) <= 1) {
                duplicate.remove(c);
            } 
            start++;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(kSubstring("qqawadq", 4));
    }
}
