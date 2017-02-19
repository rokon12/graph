package com.bazlur.algorithm.graph;

import java.util.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class PreOrderBreadthFirstTraversal<T extends Comparable<T>> implements Iterable<T> {

	private Set<GraphImpl.Vertex> visited = new HashSet<>();
	private Deque<Iterator<GraphImpl<T>.Vertex>> stack = new LinkedList<>();
	private GraphImpl<T> graph;
	private GraphImpl.Vertex next;

	public PreOrderBreadthFirstTraversal(GraphImpl<T> graph, T root) {
		this.graph = graph;

		Optional<GraphImpl<T>.Vertex> rootVertex = graph.findVertex(root);
		if (rootVertex.isPresent()) {
			GraphImpl<T>.Vertex vertex = rootVertex.get();
			Iterator<GraphImpl<T>.Vertex> iterator = vertex.getAdjacentList().iterator();
			this.stack.push(iterator);
			next = vertex;
		} else {
			throw new IllegalArgumentException(root + " is not a vertex of this graph");
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return next != null;
			}

			@Override
			public T next() {
				if (next == null) {
					throw new NoSuchElementException();
				}
				try {
					visited.add(next);
					Comparable value = next.getValue();
					return (T) value;
				} finally {
					this.advance();
				}
			}

			private void advance() {
				Iterator<GraphImpl<T>.Vertex> neighbors = stack.peek();
				do {
					while (!neighbors.hasNext()) {
						stack.pop();
						if (stack.isEmpty()) { // we are done
							next = null;
						}

						neighbors = stack.peek();
					}

					next = neighbors.next();
				} while (visited.contains(next));

				Iterator iterator = next.getAdjacentList().iterator();
				stack.push(iterator);
			}
		};
	}
}
