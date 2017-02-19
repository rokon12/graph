package com.bazlur.algorithm.graph.graph3;

import java.util.*;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/2/17.
 */
public class Graph<T extends Comparable<T>> {
	private ArrayList<T> nodes = new ArrayList<>();
	private Map<T, ArrayList<T>> map = new HashMap<>();


	public void addEdge(T source, T destination) {
		// Adds a new path.
		if (!map.containsKey(source)) {
		    /*
	        Stores a list of neighbours for a node.
            */
			ArrayList<T> neighbours = new ArrayList<>();
			neighbours.add(destination);
			map.put(source, neighbours);
		} else {
			// Updates a path.
			ArrayList<T> oldList = map.get(source);

			int index = 0;
			while ((index != oldList.size()) && (!oldList.get(index).equals(destination))) {
				index++;
			}
			// If the destination is not already in the path, then
			// add it to the path.
			if (index == oldList.size()) {
				oldList.add(destination);
				map.put(source, oldList);
			}
		}
		storeNodes(source, destination);
	}

	private void storeNodes(T source, T destination) {
		if (!source.equals(destination)) {
			if (!nodes.contains(destination)) {
				nodes.add(destination);
			}
		}
		if (!nodes.contains(source)) {
			nodes.add(source);
		}
	}

	public List<T> getNeighbours(T node) {
		ArrayList<T> neighboursList;
		Set<T> keys = map.keySet();
		for (T key : keys) {
			if (key.equals(node)) {
				neighboursList = map.get(key);
				return new ArrayList<>(neighboursList);
			}
		}
		return Collections.emptyList();
	}

	public boolean memberOf(T node) {
		return nodes.contains(node);
	}

	public String toString() {
		int counter = 0;
		String string = "";
		Set<T> keys = map.keySet();
		for (T key : keys) {
			if (counter == 0) {
				string = string + key + "--->" + map.get(key).toString();
			} else {
				string = string + "\n" + key + "--->" + map.get(key).toString();
			}
			counter++;
		}
		return string;
	}
}
