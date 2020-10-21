package kn.uni.nl.ds.graph;

/**
 *  Abstract data type for directed graphs.
 *  Manages a set of nodes V and a set of edges E which is a subset of the Cartesian
 *  product VxV, excluding loops, that is, edges of the form (v,v).
 *
 * @author Juergen Lerner
 *
 * @param <T> type for vertices
 */
public abstract class DirectedGraph<T> {

	/**
	 * Adds v to the set of nodes. Has no effect if v is already a node of this 
	 * graph.
	 * 
	 * @param v node to be added
	 */
	public abstract void addNode(T v);
	
	/**
	 * Returns true if and only if v is a node of this graph.
	 * 
	 * @param v node identifier to be tested for containment in V
	 * @return true iff v is in V
	 */
	public abstract boolean containsNode(T v);

	/**
	 * Adds (u, v) to the set of edges. Adds u and v to the set of nodes (if not
	 * already in). Has no effect if (u,v) is already an edge of this graph,
	 * or if u.equals(v).
	 * 
	 * @param u source node of the edge to be added
	 * @param v target node of the edge to be added
	 */
	public abstract void addEdge(T u, T v);

	/**
	 * Removes v from the set of nodes. Removes all edges incident to v.
	 * Has no effect if v is not a node of this graph.
	 * 
	 * @param v node to be removed
	 */
	public abstract void removeNode(T v);

	/**
	 * Removes (u,v) from the set of edges. Has no effect if (u,v) is not an edge
	 * of this graph (including the case if u or v are not nodes of this graph).
	 * 
	 * @param u source node of the edge to be removed
	 * @param v target node of the edge to be removed
	 */
	public abstract void removeEdge(T u, T v);

	/**
	 * Returns true if and only if (u,v) is an edge of this graph. Note: returns
	 * false if u or v is not a node of this graph.
	 * 
	 * @param u source node
	 * @param v target node
	 * @return true iff (u,v) is an edge
	 */
	public abstract boolean adjacent(T u, T v);

	/**
	 * Returns the number of nodes of this graph.
	 * 
	 * @return |V|
	 */
	public abstract int numNodes();

	/**
	 * Returns the number of edges of this graph.
	 * 
	 * @return |E|
	 */
	public abstract int numEdges();

	/**
	 * Returns the set of nodes.
	 * 
	 * @return V
	 */
	public abstract Iterable<T> nodes();

	/**
	 * Returns the set of edges.
	 * 
	 * @return E
	 */
	public abstract Iterable<DirectedEdge<T>> edges();

	/**
	 * Returns the indegree of v, that is the number of nodes u, such that
	 * (u,v) is an edge of this graph. Returns zero if v is not a node of this
	 * graph.
	 * 
	 * @param v node identifier
	 * @return indeg(v)
	 */
	public abstract int inDegree(T v);

	/**
	 * Returns the outdegree of v, that is the number of nodes u, such that
	 * (v,u) is an edge of this graph. Returns zero if v is not a node of this
	 * graph.
	 * 
	 * @param v node identifier
	 * @return outdeg(v)
	 */
	public abstract int outDegree(T v);

	/**
	 * Returns the set of in-neighbors of v, that is, the set of nodes u, such
	 * that (u,v) is an edge of this graph.
	 * 
	 * Returns null if v is not a node of this graph.
	 * 
	 * @param v node identifier
	 * @return {u : (u,v) is in E}
	 */
	public abstract Iterable<T> inNeighbors(T v);

	/**
	 * Returns the set of out-neighbors of v, that is, the set of nodes u, such
	 * that (v,u) is an edge of this graph.
	 * 
	 * Returns null if v is not a node of this graph.
	 * 
	 * @param v node identifier
	 * @return {u : (v,u) is in E}
	 */
	public abstract Iterable<T> outNeighbors(T v);
	
	/**
	 * Returns the number of transitive triples in this graph.
	 * 
	 * A transitive triple is an ordered triple of nodes (u,v,w), such that
	 * (u,v) is in E, (u,w) is in E, and (w,v) is in E.
	 * 
	 * This method is illustrative for an algorithm relying on a graph representation
	 * that allows efficient iteration over edges and neighbors and at the same time
	 * efficient adjacency tests.
	 * 
	 * The case distinction concerning the degrees of u and v can yield large runtime
	 * improvement on graphs with skewed degree distributions (few nodes with very high
	 * degrees and most nodes with very low degrees).
	 * 
	 * @return number of transitive triples.
	 */
	public int getNumTTriples() {
		int t = 0;
		for(DirectedEdge<T> e : edges()) {
			T u = e.getSource();
			T v = e.getTarget();
			if(outDegree(u) <= inDegree(v)) {
				for(T w : outNeighbors(u)) {
					if(adjacent(w, v)) ++t;
				}
			} else {
				for(T w: inNeighbors(v)) {
					if(adjacent(u, w)) ++t;
				}
			}
		}
		return t;
	}
	
}
