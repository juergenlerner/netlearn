package kn.uni.nl.ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Efficient implementation of a DirectedGraph in an adjacency set representation. Each node stores
 * its sets of in-neighbors and out-neighbors in a HashSet.
 * 
 * This implementation is asymptotically optimal in both space (memory) requirement and 
 * runtime of all methods (*if* the hashsets and hashmaps provide constant-time add, remove,
 * and contains operations and allow iteration in linear time).
 * 
 * Note that this implementation mostly serves to illustrate how an efficient 
 * graph data structure could be implemented. 
 * 
 * @author Juergen Lerner
 *
 */
public class DirectedGraphAS<T> extends DirectedGraph<T> {

	HashMap<T, HashSet<T>> outNeighbors;
	HashMap<T, HashSet<T>> inNeighbors;
	
	// number of edges has to be maintained explicitly to ensure O(1) runtime for numEdges()
	int numEdges;

	public DirectedGraphAS() {
		outNeighbors = new HashMap<T, HashSet<T>>();
		inNeighbors = new HashMap<T, HashSet<T>>();
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public void addNode(T v) {
		if(containsNode(v))
			return;
		outNeighbors.put(v, new HashSet<T>());
		inNeighbors.put(v, new HashSet<T>());
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public boolean containsNode(T v) {
		return outNeighbors.containsKey(v);
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
		outNeighbors.get(u).add(v);
		inNeighbors.get(v).add(u);
	}

	/*
	 * Runtime in O(outdeg+indeg+1).
	 * 
	 * In an amortized analysis one could argue for a runtime in O(1) since each unit cost
	 * for removing incident edges can be mapped to an addEdge operation.
	 *  
	 */
	@Override
	public void removeNode(T v) {
		if(!containsNode(v))
			return;
		// sets of (out-/in-)neighbors of v will be removed from the hash maps
		// in the last two lines in this method; however, v also has to be
		// removed from the sets of neighbors of its neighbors, which is 
		// done in the two loops below, where also numEdges is decreased
		for(T u : outNeighbors(v)) {
			inNeighbors.get(u).remove(v);
			--numEdges;
		}
		for(T u : inNeighbors(v)) {
			outNeighbors.get(u).remove(v);
			--numEdges;
		}
		outNeighbors.remove(v);
		inNeighbors.remove(v);
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public void removeEdge(T u, T v) {
		if(adjacent(u, v)) {
			--numEdges;
			outNeighbors.get(u).remove(v);
			inNeighbors.get(v).remove(u);
		}
	}

	/*
	 * Runtime in O(1)
	 */

	@Override
	public boolean adjacent(T u, T v) {
		if(!containsNode(u) || !containsNode(v))
			return false;
		return outNeighbors.get(u).contains(v);
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int numNodes() {
		return outNeighbors.size();
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
		return outNeighbors.keySet();
	}

	/**
	 * Returns the set of edges.
	 * 
	 * Implementation of this method in DirectedGraphAS is suboptimal as it copies the whole set
	 * of edges into a list.
	 * It would be cleaner and more efficient to return a custom Iterable, for instance, 
	 * via an anonymous inner class, that iterates successively through all nodes' lists of 
	 * outNeighbors.
	 * However this class is meant for *illustrating* the implementation of a graph data structure
	 * via an adjacency set representation and the code with the anonymous inner class 
	 * would seem to be unduly complicated (compared with the code of all other methods).
	 * 
	 * Runtime of this method is in O(|E|+|V|). It would be possible to reduce the runtime
	 * to O(|E|) by maintaining only non-empty sets of inNeighbors and outNeighbors. However,
	 * this modification would be only relevant in very sparse graphs in which the number of
	 * nodes grows asymptotically faster than the number of edges. The implementation of several
	 * methods would have to be modified as well.
	 * 
	 * @return E
	 */
	@Override
	public Iterable<DirectedEdge<T>> edges() {
		List<DirectedEdge<T>> list = new LinkedList<>();
		for(T u : nodes()) {
			for(T v : outNeighbors.get(u)) {
				list.add(new DirectedEdge<T>(u, v));
			}
		}
		return list;
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int inDegree(T v) {
		if(!containsNode(v))
			return 0;
		return inNeighbors.get(v).size();
	}

	/*
	 * Runtime in O(1)
	 */
	@Override
	public int outDegree(T v) {
		if(!containsNode(v))
			return 0;
		return outNeighbors.get(v).size();
	}

	/*
	 * Runtime for iterating over the entire Iterable is in O(inDeg(v))
	 */
	@Override
	public Iterable<T> inNeighbors(T v) {
		if(!containsNode(v))
			return null;
		return inNeighbors.get(v);
	}

	/*
	 * Runtime for iterating over the entire Iterable is in O(outDeg(v))
	 */
	@Override
	public Iterable<T> outNeighbors(T v) {
		if(!containsNode(v))
			return null;
		return outNeighbors.get(v);
	}

	/**
	 * Build up a small graph and output information about it.
	 * 
	 * Not meant as a serious test - but might still help during development.
	 * 
	 * @param args will be ignored
	 */
	public static void main(String[] args) {
		DirectedGraph<String> g = new DirectedGraphAS<String>();
		g.addEdge("A", "B");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addNode("D");
		printInfo(g); // the graph looks like  A <--> B --> C   D
		System.out.println("** removing 'A' **");
		g.removeNode("A"); 
		printInfo(g); // now the graph looks like  B --> C   D
	}

	private static void printInfo(DirectedGraph<String> g) {
		System.out.println("Graph has " + g.numNodes() + " nodes");
		System.out.println("Graph has " + g.numEdges() + " edges");
		System.out.println("(A,B) is an edge: " + g.adjacent("A", "B"));
		System.out.println("(C,B) is an edge: " + g.adjacent("C", "B"));
		System.out.print("Node set: ");
		for(String v : g.nodes()) {
			System.out.print(v + ", ");
		}
		System.out.println();
		System.out.print("Edge set: ");
		for(DirectedEdge<String> p : g.edges()) {
			System.out.print("(" + p.getSource() + ", " + p.getTarget() + "), ");
		}
		System.out.println();
		System.out.println("*** Node info ***");
		for(String v : g.nodes()) {
			System.out.print(v +  ": in-degree = " + g.inDegree(v) +  
					"; out-degree = " + g.outDegree(v));  
			System.out.print("; in-neighbors: ");
			for(String u : g.inNeighbors(v)) {
				System.out.print(u + ", ");
			}
			System.out.print("; out-neighbors: ");
			for(String u : g.outNeighbors(v)) {
				System.out.print(u + ", ");
			}
			System.out.println();
		}
	}
}
