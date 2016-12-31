package com.bazlur.algorithm.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class UnDirectedGraph<T extends Comparable<T>> implements Graph<T> {

	public enum State {
		UNVISITED,
		VISITED,
		COMPLETE;
	}

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;

	public List<Vertex> getVertices() {
		return vertexList;
	}

	public UnDirectedGraph() {
		this.vertexList = new ArrayList<>();
		this.edgeList = new ArrayList<>();
	}

	public void addEdge(T x, T y) {
		Edge edge = new Edge(x, y);
		edgeList.add(edge);
	}

	@Override
	public int v() {
		return vertexList.size();
	}

	@Override
	public int e() {
		return edgeList.size();
	}

	@Override
	public int degree(T v) {

		return findVertex(v)
			.map(vertex -> vertex.getAdjacent().size())
			.orElseThrow(() -> new IllegalArgumentException(v + " is not a vertex of this graph"));
	}

	@Override
	public boolean hasVertex(final T value) {

		return vertexList.stream()
			.anyMatch(vertex -> vertex.getValue().compareTo(value) == 0);
	}

	@Override
	public boolean hasEdge(T v, T w) {
		validateVertex(v);
		validateVertex(w);

		return findVertex(v)
			.map(vertex -> vertex.getAdjacent()
				.stream()
				.anyMatch(vertex1 -> vertex1.getValue().compareTo(w) == 0))
			.orElseGet(() -> Boolean.FALSE);
	}

	private void validateVertex(T value) {
		if (!hasVertex(value)) {
			throw new IllegalArgumentException(value + " is not a vertex");
		}
	}

	@Override
	public List<T> vertices() {

		return vertexList.stream()
			.map(Vertex::getValue)
			.collect(Collectors.toList());
	}

	@Override
	public List<T> adjacentTo(T value) {
		validateVertex(value);

		return findVertex(value)
			.map(vertex -> vertex.getAdjacent()
				.stream()
				.map(Vertex::getValue)
				.collect(Collectors.toList()))
			.orElseGet(Collections::emptyList);
	}

	private Optional<Vertex> findVertex(T value) {

		return vertexList.stream()
			.filter(vertex -> vertex.getValue().compareTo(value) == 0)
			.findFirst();
	}

	@Override
	public String toString() {

		return vertexList.stream()
			.map(Vertex::toString)
			.collect(Collectors.joining("\n"));
	}

	public String edgesToString() {

		return edgeList.stream()
			.map(Edge::toString)
			.collect(Collectors.joining());
	}

	class Vertex {
		T value;
		ArrayList<Vertex> adjacent;

		State state;

		public Vertex(T value) {
			this.value = value;
			this.adjacent = new ArrayList<>();
			this.state = State.UNVISITED;
		}

		public State getState() {
			return state;
		}

		public void setState(State state) {
			this.state = state;
		}

		public T getValue() {
			return value;
		}

		public void addNeighbour(Vertex vertex) {
			adjacent.add(vertex);
		}

		public ArrayList<Vertex> getAdjacent() {
			return adjacent;
		}


		@Override
		public String toString() {

			return ("Vertex: " + value + "-> "
				+ adjacent.stream()
				.map(vertex -> vertex.getValue().toString())
				.collect(Collectors.joining(", ")));
		}

	}

	class Edge {
		private Vertex x;

		private Vertex y;

		public Edge(final T x, final T y) {
			this.x = findVertex(x)
				.map(vertex -> vertex)
				.orElseGet(() -> createNewAndAddToList(x));

			this.y = findVertex(y)
				.map(vertex -> vertex)
				.orElseGet(() -> createNewAndAddToList(y));

			this.x.addNeighbour(this.y);
			this.y.addNeighbour(this.x);
		}

		private Vertex createNewAndAddToList(T x) {
			Vertex vertex = new Vertex(x);
			vertexList.add(vertex);

			return vertex;
		}

		public String toString() {
			return "Edge: " + x.getValue() + " <-> " + y.getValue() + "\n";
		}

	}
}
