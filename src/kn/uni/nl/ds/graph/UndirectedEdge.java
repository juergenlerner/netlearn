package kn.uni.nl.ds.graph;

/**
 * @author Juergen Lerner
 * 
 * @param T 
 *
 */
public class UndirectedEdge<T> {

	private T node1;
	private T node2;

	public UndirectedEdge(T node1, T node2) {
		super();
		this.node1 = node1;
		this.node2 = node2;
	}

	public T getNode1() {
		return node1;
	}

	public void setNode1(T node1) {
		this.node1 = node1;
	}

	public T getNode2() {
		return node2;
	}

	public void setNode2(T node2) {
		this.node2 = node2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + 
				((node1 == null) ? 0 : node1.hashCode()) 
				+ // is symmetric yielding a hash code independent of the order
				((node2 == null) ? 0 : node2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof UndirectedEdge)) {
			return false;
		}
		UndirectedEdge<?> other = (UndirectedEdge<?>) obj;
		if(equalsInSameOrder(other))
			return true;
		return equalsInReverseOrder(other);
	}

	private boolean equalsInSameOrder(UndirectedEdge<?> other) {
		if (node1 == null) {
			if (other.node1 != null) {
				return false;
			}
		} else if (!node1.equals(other.node1)) {
			return false;
		}
		if (node2 == null) {
			if (other.node2 != null) {
				return false;
			}
		} else if (!node2.equals(other.node2)) {
			return false;
		}
		return true;
	}
	
	private boolean equalsInReverseOrder(UndirectedEdge<?> other) {
		if (node1 == null) {
			if (other.node2 != null) {
				return false;
			}
		} else if (!node1.equals(other.node2)) {
			return false;
		}
		if (node2 == null) {
			if (other.node1 != null) {
				return false;
			}
		} else if (!node2.equals(other.node1)) {
			return false;
		}
		return true;
	}
	
}
