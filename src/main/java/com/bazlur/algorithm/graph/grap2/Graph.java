package com.bazlur.algorithm.graph.grap2;

import java.util.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class Graph<T> {

	public static interface Visitor<T> {
		void visit(T vertex);
	}

	private Map<T, List<T>> edges = new HashMap<>();

	public void addEdge(T src, T dest) {
		List<T> srcNeighbors = this.edges.get(src);
		if (srcNeighbors == null) {
			this.edges.put(src,
				srcNeighbors = new ArrayList<>()
			);
		}
		srcNeighbors.add(dest);
	}

	public Iterable<T> getNeighbors(T vertex) {
		List<T> neighbors = this.edges.get(vertex);
		if (neighbors == null) {
			return Collections.emptyList();
		} else {
			return Collections.unmodifiableList(neighbors);
		}
	}

	public void preOrderTraversal(T vertex, Visitor<T> visitor) {
		preOrderTraversal(vertex, visitor, new HashSet<T>());
	}

	private void preOrderTraversal(T vertex, Visitor<T> visitor, Set<T> visited) {
		visitor.visit(vertex);
		visited.add(vertex);

		for (T neighbor : this.getNeighbors(vertex)) {
			// if neighbor has not been visited then recurse
			if (!visited.contains(neighbor)) {
				preOrderTraversal(neighbor, visitor, visited);
			}
		}
	}

	public void preformBbsTraversals(T vertex, Visitor<T> visitor) {
		Set<T> visited = new HashSet<>();
		Queue<T> queue = new LinkedList<>();

		queue.add(vertex);              //Adds to end of queue
		visited.add(vertex);

		while (!queue.isEmpty()) {
			//removes from front of queue
			vertex = queue.remove();
			visitor.visit(vertex);

			//Visit child first before grandchild
			for (T neighbor : this.getNeighbors(vertex)) {
				if (!visited.contains(neighbor)) {
					queue.add(neighbor);
					visited.add(neighbor);
				}
			}
		}
	}
}
