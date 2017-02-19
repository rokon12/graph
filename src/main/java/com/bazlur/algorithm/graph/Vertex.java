package com.bazlur.algorithm.graph;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/5/17.
 */
public interface Vertex<T extends Comparable<T>> {
	State getState();

	void setState(State state);

	T getValue();

	void setValue(T value);

	void addNeighbour(Vertex<T> vertex);

	List<Vertex<T>> getNeighbours();
}
