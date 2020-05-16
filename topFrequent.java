/*
Your team at Amazon owns a website and wants to raise the bar on customer experience. You are tasked with figuring out which parts of the website are the most confusing to customers. You need to write an algorithm that analyzes many questions typed into the Help Search box and finds the most frequently used words, excluding common words like "the" or "an".

Write an algorithm to find the most frequently used word in the help search box, excluding the commonly used words. In the context of this search, a word is an alphabetic sequence of characters having no whitespace or punctuation.


Input
The input to the function/method consists of two arguments -
helpText, a string representing many help searches.
wordsToExclude, a list of strings representing the commonly used words to be excluded while analyzing the word frequency.

Output
Return a list of strings representing the most frequently used word in the help search. In the case of a tie, return all of the most frequently used words.

Note
The words in the search query which differ only by the case are counted as the same word.
The order of words does not matter in the output list.
All words in the wordsToExclude list are unique.
Punctuation should be treated as white space.

Example
Input:
helpText = ”Purchase Order Item Help can't find item item is too much part of purchase need fix for image item delivered too fast purchase order too big is purchase order coming? Too big why""
wordsToExclude = ["help", "fix", "too", "is", "of"]

Output:
[“item”, "purchase"] or [“purchase”, "item"]

Explanation:
The word “too” has a maximum of 4 frequency but this word should be excluded while analyzing the word frequency.
The words "purchase” and "item" both have a maximum frequency of 4 and is not predetermined to be excluded from your analysis.
So the output is [“item”, "purchase"] or [“purchase”, "item"], the order of words does not matter."
*/

/*
Create a set of excluded words. Split using a delimiter.
Iterate over the map and output words with highest frequency.
Create a map of input and frequency of each of the words, while tracking the highest frequency.
O(n) n is length of helpString
*/
class Solution {
  public static void main(String[] args) {
      //String stri = "morning &&&& *** ^^^ %%% ------ !!!!!!!";
      String stri = "Tanstaafl - (there's no such thing as a free lunch no't there in the lunch area) Tanstaafl free area such";
      String[] excludes = {"a", "the", "as", "in", "there"};//area free lunch no such tanstaafl
      
      List<String> exclude = new ArrayList<>();
      Collections.addAll(exclude, excludes);
      List<String> result = getTopWords(stri, 3, exclude);
      
      System.out.println(result);
  }
  
  public static List<String> getTopWords(String helpString, int num, List<String> excludes) {
      Map<String, Integer> map = new HashMap<>();
      Set<String> keySet = new HashSet<>();
      List<String> result = new ArrayList<>();
      if (helpString == null || helpString.length() == 0) {
          return result;
      }
        
      if (num != 0) {
          for (int i = 0; i < excludes.size(); ++i) {
              keySet.add(excludes.get(i).toLowerCase());
          }
      }
        
      helpString = helpString.replaceAll("\\pP"," ");
      String[] words = helpString.split(" ");
      int max = 0;
      for (String word : words) {
          word = word.toLowerCase().trim();
          if (keySet.contains(word) || word.length() == 0) {
              continue;
          }
          char c = word.charAt(0);
          if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
              map.put(word, map.getOrDefault(word, 0) + 1);
              max = Math.max(max, map.get(word));
          }
      }
      
      for (String key : map.keySet()) {
          if (map.get(key) == max) {
              result.add(key);
          }
      }  
      
      return result;
    }
}
