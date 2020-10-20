package kn.uni.nl.ds.graph;

/**
 *  Abstract data type for undirected graphs.
 *  
 *  Manages a set of nodes V and a set of edges E which is a subset of 
 *  { {u,v}: u,v in V and u != v }, that is sets of two different nodes.
 *  Note that sets have no order ({u,v} equals {v,u}) so that edges are undirected.
 *
 * @author Juergen Lerner
 *
 * @param <T> type for vertices
 */
public abstract class UndirectedGraph<T> {

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
	 * Adds {u, v} to the set of edges. Adds u and v to the set of nodes (if not
	 * already in). Has no effect if {u,v} (or {v,u}) is already an edge of this graph,
	 * or if u.equals(v).
	 * 
	 * @param u one incident node of the edge to be added
	 * @param v the other incident node of the edge to be added
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
	 * Removes {u,v} from the set of edges. Has no effect if {u,v} is not an edge
	 * of this graph (including the case if u or v are not nodes of this graph).
	 * 
	 * @param u one incident node of the edge to be removed
	 * @param v the other incident node of the edge to be removed
	 */
	public abstract void removeEdge(T u, T v);

	/**
	 * Returns true if and only if {u,v} is an edge of this graph. Note: returns
	 * false if u or v is not a node of this graph.
	 * 
	 * @param u a node
	 * @param v another node
	 * @return true iff {u,v} is an edge
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
	public abstract Iterable<UndirectedEdge<T>> edges();

	/**
	 * Returns the degree of v, that is the number of nodes u, such that
	 * {u,v} is an edge of this graph. Returns zero if v is not a node of this
	 * graph.
	 * 
	 * @param v node identifier
	 * @return deg(v)
	 */
	public abstract int degree(T v);

	/**
	 * Returns the set of neighbors of v, that is, the set of nodes u, such
	 * that {u,v} is an edge of this graph.
	 * 
	 * Returns null if v is not a node of this graph.
	 * 
	 * @param v node identifier
	 * @return {u : {u,v} is in E}
	 */
	public abstract Iterable<T> neighbors(T v);

}
