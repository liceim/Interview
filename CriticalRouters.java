//Basically, it uses dfs to travel through the graph to find if current vertex u, can travel back to u or previous vertex
//low[u] records the lowest vertex u can reach
//disc[u] records the time when u was discovered

//The Time complexity is O(graph) + O(DFS) = O(|E| + |V|) + O(|E| + |V|) = O(|E| + |V|)
private int time;
private static List<Integer> getCriticalNodes(int numRouters, int numLinks, ArrayList<ArrayList<Integer>> linkes) {
	time = 0;
	Map<Integer, Set<Integer>> map = new HashMap<>();
	
	for(List<Integer> link : links) {
		if (!map.containsKey(link.get(0))) {
			map.put(link.get(0), new HashSet<>());
		}
		map.get(link[0]).add(link[1]);
		
		if (!map.containsKey(link.get(1))) {
			map.put(link.get(1), new HashSet<>());
		}
		map.get(link[1]).add(link[0]);
	}
  
	Set<Integer> set = new HashSet<>();
	int[] low = new int[numRouters + 1];
	int[] disc = new int[numRouters + 1];
	int parent[] = new int[numRouters + 1]; 
	Arrays.fill(disc, -1);
	Arrays.fill(parent, -1);
	for(int i = 0; i <= numRouters; i++) {
		if(disc[i] == -1 && map.containsKey(i)) {
			dfs(map, low, disc, parent, i, set);
    	}
	}
	return new ArrayList<>(set);
}

private static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] disc, int[] parent, int cur, Set<Integer> res) {
	int children = 0; 
	disc[cur] = low[cur]= ++time;
	for(int nei : map.get(cur)) {
		if(disc[nei] == -1) {
			children++;
			parent[nei] = cur;
			dfs(map, low, disc, parent,nei, res);
			low[cur] = Math.min(low[cur], low[nei]);
			if((parent[cur] == -1 && children > 1) || (parent[cur] != -1 && low[nei] >= disc[cur]))
				res.add(cur);
		}
		else if(nei != parent[cur])
			low[cur] = Math.min(low[cur], disc[nei]);
	}
}
