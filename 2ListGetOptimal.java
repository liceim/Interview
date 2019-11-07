/*
In my thought, we can sort forwardRouteList and returnRouteList by its second element as first. 
What we really care is the sum of route dist must be smaller than maxTravelDist and as large as possible.
We can set two pointers and traverse two sorted arrays to find the max value (and the combination of course). 
Since the sorting part complexity is O(MlogM + NlogN) and two-pointer traversal is O(M + N), 
the final complexity can be regarded as O(KlogK) where K is the longest input array.
*/
public List<List<Integer>> optimalUtilization(int maxCapacity, List<List<Integer>> fList, List<List<Integer>> rList) {
		Map<Integer, List<List<Integer>>> resultMap = new HashMap<>();
		Collections.sort(fList, (f1, f2) -> (f1.get(1) - f2.get(1)));
		Collections.sort(rList, (r1, r2) -> (r1.get(1) - r2.get(1)));

		int f = fList.size();
		int r = rList.size();
		int fIdx = 0;
		int rIdx = r - 1;

		int maxPossibleCapacity = Integer.MIN_VALUE;

		while (fIdx < f && rIdx >= 0) {
			int sumCapacity = fList.get(fIdx).get(1) + rList.get(rIdx).get(1);
			if (sumCapacity > maxCapacity) {
			    --rIdx;
				continue;
			}
      
			if (maxPossibleCapacity <= sumCapacity) {
				  List<List<Integer>> pairs = resultMap.get(sumCapacity) == null ? new ArrayList<>() : resultMap.get(sumCapacity);
				  pairs.add(Arrays.asList(fList.get(fIdx).get(0), rList.get(rIdx).get(0)));
				  maxPossibleCapacity = sumCapacity;
				
          // add similar pairs
				  for (int index = rIdx - 1; index >= 0
						&& rList.get(index).get(1) == rList.get(index + 1).get(1); index--) {
					  pairs.add(new ArrayList<>(Arrays.asList(fList.get(fIdx).get(0), rList.get(index).get(0))));
				  }
				  resultMap.put(sumCapacity, pairs);
			  }
			  ++fIdx;
		  }

		  return resultMap.get(maxPossibleCapacity);
	}
