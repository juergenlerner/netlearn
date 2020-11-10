package kn.uni.nl.ds.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Efficient implementation of an UndirectedGraph in an adjacency set representation. Each node stores
 * its sets of neighbors in a HashSet.
 * 
 * This implementation is asymptotically optimal in both space (memory) requirement and 
 * runtime of all methods (if the hashsets and hashmaps provide constant-time add, remove,
 * and contains operations and allow iteration in linear time).
 * 
 * Note that this implementation mostly serves to illustrate how an efficient 
 * graph data structure could be implemented. 
 * 
 * @author Juergen Lerner
 *
 */
public class UndirectedGraphAS<T> extends UndirectedGraph<T> {

	HashMap<T, HashSet<T>> neighbors;
	
	// number of edges has to be maintained explicitly to ensure O(1) runtime for numEdges()
	int numEdges;

	public UndirectedGraphAS() {
		neighbors = new HashMap<T, HashSet<T>>();
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public void addNode(T v) {
		if(containsNode(v))
			return;
		neighbors.put(v, new HashSet<T>());
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public boolean containsNode(T v) {
		return neighbors.containsKey(v);
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public void addEdge(T u, T v) {
		if(u.equals(v) || adjacent(u, v))
			return;
		addNode(u);
		addNode(v);
		++numEdges;
		neighbors.get(u).add(v);
		neighbors.get(v).add(u);
	}

	/*
	 * Runtime in O(deg+1).
	 * 
	 * In an amortized analysis one could argue for a runtime in O(1) since each unit cost
	 * for removing incident edges can be mapped to an addEdge operation.
	 *  
	 */
	@Override
	public void removeNode(T v) {
		if(!containsNode(v))
			return;
		for(T u : neighbors(v)) {
			neighbors.get(u).remove(v);
			--numEdges;
		}
		neighbors.remove(v);
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public void removeEdge(T u, T v) {
		if(adjacent(u, v)) {
			--numEdges;
			neighbors.get(u).remove(v);
			neighbors.get(v).remove(u);
		}
	}

	/*
	 * Runtime in O(1)
	 */

	@Override
	public boolean adjacent(T u, T v) {
		if(!containsNode(u) || !containsNode(v))
			return false;
		return neighbors.get(u).contains(v);
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int numNodes() {
		return neighbors.size();
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int numEdges() {
		return numEdges;
	}

	
	/*
	 * Runtime for iterating over the entire returned iterator is in O(n), where n=|V|.
	 */
	@Override
	public Iterable<T> nodes() {
		return neighbors.keySet();
	}

	/**
	 * Returns the set of edges.
	 * 
	 * Implementation of this method in UndirectedGraphAS might be suboptimal as it copies the 
	 * whole set of edges into a hash set.
	 * It might be cleaner and more efficient to return a custom Iterable, for instance, 
	 * via an anonymous inner class, that iterates successively through all nodes' lists of 
	 * neighbors. The difficulty for this implementation of UndirectedGraph is that each 
	 * edge is stored twice (at both of its endpoints), so that the iterator would have to 
	 * recognize which edge has been returned already. Would be easy, for instance, if the node
	 * type T was totally ordered.
	 * However this class is meant for *illustrating* the implementation of a graph data structure
	 * via an adjacency set representation and the code with the anonymous inner class 
	 * would seem to be unduly complicated (compared with the code of all other methods).
	 * 
	 * Runtime of this method is still in O(|E|) which is asymptotically optimal.
	 * 
	 * @return E
	 */
	@Override
	public Iterable<UndirectedEdge<T>> edges() {
		HashSet<UndirectedEdge<T>> edgeSet = new HashSet<>();
		for(T u : nodes()) {
			for(T v : neighbors.get(u)) {
				edgeSet.add(new UndirectedEdge<T>(u, v));
			}
		}
		return edgeSet;
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int degree(T v) {
		if(!containsNode(v))
			return 0;
		return neighbors.get(v).size();
	}

	/*
	 * Runtime for iterating over the entire Iterable is in O(deg(v))
	 */
	@Override
	public Iterable<T> neighbors(T v) {
		if(!containsNode(v))
			return null;
		return neighbors.get(v);
	}

	/**
	 * Build up a small graph and output information about it.
	 * 
	 * Not meant as a serious test - but might still help during development.
	 * 
	 * @param args will be ignored
	 */
	public static void main(String[] args) {
		UndirectedGraph<String> g = new UndirectedGraphAS<String>();
		g.addEdge("A", "B");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addNode("D");
		printInfo(g); // the graph looks like  A -- B -- C   D
		System.out.println("** removing 'A' **");
		g.removeNode("A"); 
		printInfo(g); // now the graph looks like  B -- C   D
	}

	private static void printInfo(UndirectedGraph<String> g) {
		System.out.println("Graph has " + g.numNodes() + " nodes");
		System.out.println("Graph has " + g.numEdges() + " edges");
		System.out.println("{A,B} is an edge: " + g.adjacent("A", "B"));
		System.out.println("{C,B} is an edge: " + g.adjacent("C", "B"));
		System.out.println("{C,A} is an edge: " + g.adjacent("C", "A"));
		System.out.print("Node set: ");
		for(String v : g.nodes()) {
			System.out.print(v + ", ");
		}
		System.out.println();
		System.out.print("Edge set: ");
		for(UndirectedEdge<String> p : g.edges()) {
			System.out.print("{" + p.getNode1() + ", " + p.getNode2() + "}, ");
		}
		System.out.println();
		System.out.println("*** Node info ***");
		for(String v : g.nodes()) {
			System.out.print(v +  ": degree = " + g.degree(v));  
			System.out.print("; neighbors: ");
			for(String u : g.neighbors(v)) {
				System.out.print(u + ", ");
			}
			System.out.println();
		}
	}
}
