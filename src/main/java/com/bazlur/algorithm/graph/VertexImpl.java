package com.bazlur.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 1/5/17.
 */
public class VertexImpl<T extends Comparable<T>> implements Vertex<T> {
	private State state;
	private T value;
	private List<Vertex<T>> neighbours = new ArrayList<>();

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

	@Override
	public T getValue() {
		return value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public void addNeighbour(Vertex<T> vertex) {
		neighbours.add(vertex);
	}

	@Override
	public List<Vertex<T>> getNeighbours() {
		return neighbours;
	}
}
