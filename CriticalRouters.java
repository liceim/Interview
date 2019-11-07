/*
This problem is intend to find the crtical points in a graph. So I think it could be solved by Tarjan algorithm.
And Tarjan algorithm will find the critical edges. So after find the critical edges, both of the points could be
criticle points. Inoticed the point is critical point have and only have more than one adjacent nodes.
*/

//The Time complexity is O(graph) + O(DFS) = O(|E| + |V|) + O(|E| + |V|) = O(|E| + |V|)
private List<Integer>[] adj;
private int id;
private int[] visited;
private int[] low;

private static List<Integer> getCriticalNodes(int numRouters, int numLinks, ArrayList<ArrayList<Integer>> linkes) {
	adj = new List[numRouters + 2];
	Arrays.setAll(adj, (i) -> new ArrayList<>());
	for (ArrayList<Integer> l : links) {
		int u = l.get(0);
		int v = l.get(0);
		adj[u].add(v);
		adj[v].add(u);
	}
	
	visited = new int[numRouters + 2];
	low = new int[numRouters + 2];
	id = 1;
	
	List<Integer> res = new ArrayList<>();
	dfs(1, -1, res);
	return res;
}

private static void dfs(int at, int parent, List<Integer> res) {
	visited[at] = id;
	low[at] = id;
	id++;
	
	for (int to : adj[at]) {
		if (to == parent) {
			continue;
		}
		if (visited[to] == 0) {
			dfs(to, at, res);
			low[at] = Math.min(low[at], low[to]);
			
			if (visited[at] < low[at]) {
				if (adj[to].size() > 1 && !res.contains(to)) {
					res.add(to);
				}
				
				if (adj[at].size() > 1 && !res.contains(at)) {
					res.add(at);
				}
			}
		} else {
			low[at] = Math.min(low[at], visited[to]);
		}
	}
}
