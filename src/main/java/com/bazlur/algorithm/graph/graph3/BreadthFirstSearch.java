package com.bazlur.algorithm.graph.graph3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/2/17.
 */
public class BreadthFirstSearch<T extends Comparable<T>> {
	private List<T> shortestPath = new ArrayList<>();

	public List<T> run(Graph<T> graph, T source, T destination) {
		shortestPath.clear();

		List<T> path = new ArrayList<>();
		if (source.equals(destination) && graph.memberOf(source)) {
			path.add(source);

			return path;
		}

		ArrayDeque<T> queue = new ArrayDeque<>();
		ArrayDeque<T> visited = new ArrayDeque<>();

		queue.offer(source);

		while (!queue.isEmpty()) {
			T vertex = queue.poll();
			visited.offer(vertex);

			List<T> neighboursList = graph.getNeighbours(vertex);
			int index = 0;
			int neighboursSize = neighboursList.size();
			while (index != neighboursSize) {
				T neighbour = neighboursList.get(index);

				path.add(neighbour);
				path.add(vertex);

				if (neighbour.equals(destination)) {
					return processPath(source, destination, path);
				} else {
					if (!visited.contains(neighbour)) {
						queue.offer(neighbour);
					}
				}
				index++;
			}
		}
		return null;
	}

	private List<T> processPath(T src, T destination, List<T> path) {
		// Finds out where the destination node directly comes from.
		int index = path.indexOf(destination);
		T source = path.get(index + 1);

		// Adds the destination node to the shortestPath.
		shortestPath.add(0, destination);

		if (source.equals(src)) {
			// The original source node is found.
			shortestPath.add(0, src);

			return shortestPath;
		} else {
			// We find where the source node of the destination node
			// comes from.
			// We then set the source node to be the destination node.
			return processPath(src, source, path);
		}
	}
}
