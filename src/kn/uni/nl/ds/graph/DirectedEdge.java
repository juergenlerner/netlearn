package kn.uni.nl.ds.graph;

/**
 * @author Juergen Lerner
 * 
 * @param T 
 *
 */
public class DirectedEdge<T> {

	private T source;
	private T target;

	public DirectedEdge(T source, T target) {
		super();
		this.source = source;
		this.target = target;
	}

	public T getSource() {
		return source;
	}

	public void setSource(T source) {
		this.source = source;
	}

	public T getTarget() {
		return target;
	}

	public void setTarget(T target) {
		this.target = target;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((source == null) ? 0 : source.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DirectedEdge)) {
			return false;
		}
		DirectedEdge<?> other = (DirectedEdge<?>) obj;
		if (source == null) {
			if (other.source != null) {
				return false;
			}
		} else if (!source.equals(other.source)) {
			return false;
		}
		if (target == null) {
			if (other.target != null) {
				return false;
			}
		} else if (!target.equals(other.target)) {
			return false;
		}
		return true;
	}
	
}
