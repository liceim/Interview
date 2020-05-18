import java.io.*;
import java.util.*;

/*
We can solve this problem with the similar idea:
Firstly, we need to calculate the frequency of each word and store the result in a hashmap.

Secondly, we will use bucket sort to store words. Why? Because the minimum frequency is 
greater than or equal to 1 and the maximum frequency is less than or equal to the length of the input string array.

Thirdly, we can define a trie within each bucket to store all the words with the same frequency.
With Trie, it ensures that the lower alphabetical word will be met first, saving the trouble to sort the words within the bucket.

From the above analysis, we can see the time complexity is O(n).
 */

// Time complexity is O(n) n is review numbers.

class Solution {
  public static void main(String[] args) {
      int numCompetitors = 6;
      int topNCompetitors = 2;
      String[] competitors = {"newshop", "shopnow", "afshion", "fashionbeats", "mymarket", "tcellular"};
      int numReviews = 6;
      String[] reviews = {"newshop is providing good services in the city; everyone should use newshop", "best services by newsshop", "fashionbeats has great services int the city","i am proud to have fashionbeats","mymarket has awesome services","Thanks Newshop for the quick delivery."};
      
      List<String> toys = new ArrayList<>();
      Collections.addAll(toys, competitors);
      List<String> quotes = new ArrayList<>();
      Collections.addAll(quotes, reviews);
      List<String> result = getTopCompetitors(topNCompetitors, toys, quotes);
        
      System.out.println(result);
  }
  
  public static ArrayList<String> getTopCompetitors(int topToys, List<String> toys, List<String> quotes) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> keySet = new HashSet<>();
        List<String>[] bucket = new List[quotes.size() + 1];
        
        for (int i = 0; i < toys.size(); ++i) {
            keySet.add(toys.get(i).toLowerCase());
        }
        
        for (String quote : quotes) {
            String[] words = quote.toLowerCase().split(" ");
            Set<String> used = new HashSet<>();
            for (String word : words) {
                if (keySet.contains(word) && !used.contains(word)) {
                    map.put(word, map.getOrDefault(word, 0) + 1);
                    used.add(word);
                }
            }
        }
        
        for (String key : map.keySet()) {
            int freq = map.get(key);
            if (bucket[freq] == null) {
                bucket[freq] = new ArrayList<>();
            }
            bucket[freq].add(key);
        }
        
        ArrayList<String> result = new ArrayList<>();
        int k = topToys;
        for (int i = bucket.length - 1; i > 0 && k > 0; --i) {
            if (bucket[i] != null) {
                List<String> list = bucket[i];
                Collections.sort(list);
                for (int j = 0; j < list.size() && k > 0; ++j) {
                    result.add(list.get(j));
                    k--;
                }
            }
        }
        
        return result;
    }
}
