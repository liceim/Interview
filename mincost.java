/*
The time to merge two files is the same as the sum of the size of the two files, which is the same as the size
ofthe resulting merged file. We want to merge the given files into a single merged file such that every time we
merge two files, they are the smallest available files. In order to keep track of which two files to merge next,
we can use a priority queue (min heap) which takes out the smalles file first. Then we keep taking out the
two smallest files, merging them, and putting that merged file back into the queue. We repeat this reduction
until there is only a single merged file left in the queue, which gives us the time taken to merge the original
files.
*/

//time complexity O(nlogn)
public class Solution
{
 	int minimumTime(int numOfSubFiles, List<Integer> files) {
		Queue<Integer> minHeap = new PriorityQueue<Integer>();
		for(int f : files) {
			minHeap.offer(f);
		}
		int res = 0;
		while(minHeap.size() > 1) {
			int f1 = minHeap.poll();
			int f2 = minHeap.poll();
			int tmp = f1 + f2;
			res += tmp;
			minHeap.offer(tmp);
		}
		return res;
	}
}
