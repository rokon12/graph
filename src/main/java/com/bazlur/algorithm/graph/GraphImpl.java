package com.bazlur.algorithm.graph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class GraphImpl<T extends Comparable<T>> implements Graph<T> {

	public enum State {
		UNVISITED,
		VISITED,
		COMPLETE;
	}

	private ArrayList<Vertex> vertexList;
	private ArrayList<Edge> edgeList;
	private boolean directed;

	public List<Vertex> getVertices() {
		return vertexList;
	}

	public GraphImpl(boolean directed) {
		this.directed = directed;
		this.vertexList = new ArrayList<>();
		this.edgeList = new ArrayList<>();
	}

	public GraphImpl() {
		this(false);
	}

	public void addEdge(T x, T y) {
		Edge edge = new Edge(x, y);
		edgeList.add(edge);
	}

	@Override
	public int getTotalVertex() {
		return vertexList.size();
	}

	@Override
	public int getTotalEdge() {
		return edgeList.size();
	}

	@Override
	public int degree(T v) {

		return findVertex(v)
			.map(vertex -> vertex.getAdjacentList().size())
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
			.map(vertex -> vertex.getAdjacentList()
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
			.map(vertex -> vertex.getAdjacentList()
				.stream()
				.map(Vertex::getValue)
				.collect(Collectors.toList()))
			.orElseGet(Collections::emptyList);
	}

	public Optional<Vertex> findVertex(T value) {

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
		private T value;
		private List<Vertex> adjacent;

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

		public List<Vertex> getAdjacentList() {
			return adjacent;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Vertex vertex = (Vertex) o;
			return Objects.equals(value, vertex.value);
		}

		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public String toString() {
			String neighbour = adjacent.stream()
				.map(vertex -> vertex.getValue().toString())
				.collect(Collectors.joining(", "));

			return isNotEmpty(neighbour) ? "Vertex: " + value + " -> " + neighbour : "Vertex: " + value;
		}

	}

	private boolean isNotEmpty(String neighbour) {
		return neighbour != null && !neighbour.isEmpty();
	}

	class Edge {
		private Vertex x;
		private Vertex y;

		Edge(final T x, final T y) {
			this.x = findVertex(x)
				.map(vertex -> vertex)
				.orElseGet(() -> createNewAndAddToList(x));

			this.y = findVertex(y)
				.map(vertex -> vertex)
				.orElseGet(() -> createNewAndAddToList(y));

			this.x.addNeighbour(this.y);
			if (!directed) {
				this.y.addNeighbour(this.x);
			}
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
