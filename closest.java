/*
 we can maintain a max-heap with size K. Then for each point, we add it to the heap. Once the size of the heap is greater than K,
 we are supposed to extract one from the max heap to ensure the size of the heap is always K. 
 Thus, the max heap is always maintain top K smallest elements from the first one to crruent one.
 Once the size of the heap is over its maximum capacity, it will exclude the maximum element in it, 
 since it can not be the proper candidate anymore.
Theoretically, the time complexity is O(NlogK),
*/
class Solution {
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > K) {
                maxHeap.poll();
            }
        }
        
        int[][] res = new int[K][2];
        while (K > 0) {
            res[--K] = maxHeap.poll();
        }
        return res;
    }
}
