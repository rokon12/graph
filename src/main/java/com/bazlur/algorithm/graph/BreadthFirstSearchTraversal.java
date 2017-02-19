package com.bazlur.algorithm.graph;

import java.util.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class BreadthFirstSearchTraversal<T extends Comparable<T>> implements Iterable<T> {
	private Set<GraphImpl.Vertex> visited;
	private Queue<GraphImpl.Vertex> queue;

	public BreadthFirstSearchTraversal(GraphImpl<T> graph, T root) {
		this.visited = new HashSet<>();
		this.queue = new LinkedList<>();

		Optional<GraphImpl<T>.Vertex> rootVertex = graph.findVertex(root);
		if (rootVertex.isPresent()) {
			GraphImpl<T>.Vertex vertex = rootVertex.get();
			this.queue.add(vertex);
			this.visited.add(vertex);
		} else {
			throw new IllegalArgumentException(root + " is not a vertex of this graph");
		}

	}

	@Override
	public Iterator<T> iterator() {


		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return !queue.isEmpty();
			}

			@Override
			public T next() {
				GraphImpl.Vertex next = queue.remove();
				List<GraphImpl.Vertex> adjacentList = next.getAdjacentList();
				for (GraphImpl.Vertex neighbour : adjacentList) {
					if (!visited.contains(neighbour)) {
						queue.add(neighbour);
						visited.add(neighbour);
					}
				}

				Comparable value = next.getValue();
				return (T) value;
			}
		};
	}
}
