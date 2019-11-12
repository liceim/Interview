import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
// Use HashMap to record key and its counts, use Bucket sort all key to get top Key
// Time complexity is O(n) n is review numbers.

class Solution {
  public static void main(String[] args) {
      int numCompetitors = 6;
      int topNCompetitors = 2;
      String[] competitors = {"newshop", "shopnow","fashionbeats","mymarket","tcellular"};
      int numReviews = 6;
      String[] reviews = {"newshop is providing good services in the city; everyone should use newshop", "best services by newsshop", "fashionbeats has great services int the city","i am proud to have fashionbeats","mymarket has awesome services","Thanks Newshop for the quick delivery."};
        
      List<String> result = getTopCompetitors(numCompetitors, topNCompetitors, competitors, numReviews, reviews);
        
      System.out.println(result);
  }
  
  public static List<String> getTopCompetitors(int numCompetitors, int topNCompetitors, String[] competitors, int numReviews, String[] reviews) {
        Map<String, Integer> map = new HashMap<>();
        Set<String> keySet = new HashSet<>();
        List<String>[] bucket = new List[competitors.length + 1];
        
        for (int i = 0; i < competitors.length; ++i) {
            keySet.add(competitors[i].toLowerCase());
        }
        
        for (String review : reviews) {
            String[] words = review.toLowerCase().split(" ");
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
        
        List<String> result = new ArrayList<>();
        int k = topNCompetitors;
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
        /*
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> (a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()));
        
        for (Map.Entry entry : map.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > topNCompetitors) {
                minHeap.poll();
            }
        }
                                                                                
        String[] res = new String[topNCompetitors];
        for (int i = topNCompetitors - 1; i >= 0 && !minHeap.isEmpty(); --i) {
            Map.Entry<String, Integer> entry = minHeap.poll();
            res[i] = entry.getKey();
        }                           
        
        List<String> result = new ArrayList<>();
        for (String key : res) {
            if(key != null) {
                result.add(key);
            }
        }
        
        return result;    
        */
    }
}
