package com.bazlur.algorithm.graph;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class UnDirectedGraphTest {

	@Test
	public void testCreateGraph_expectGraphIsCreated() {
		Graph<String> graph = new UnDirectedGraph<>();
		Assert.assertNotNull(graph);
	}

	@Test
	public void testAddEdge_expectVertexAndEdgeCreated() {
		UnDirectedGraph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");
		Assert.assertEquals(graph.v(), 4);
		Assert.assertEquals(graph.e(), 3);

		System.out.println(graph.toString());
		System.out.println(graph.edgesToString());
	}

	@Test
	public void testDegree_givenAVertex_expectSizeOfAdjacentList() {
		Graph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("B", "D");

		int degree = graph.degree("A");
		Assert.assertEquals(degree, 2);
	}

	@Test
	public void testHasVertex_givenEmptyGraph_expectFalse() {
		Graph<String> graph = new UnDirectedGraph<>();
		Assert.assertFalse(graph.hasVertex("X"));
	}

	@Test
	public void testHasVertex_givenAnEdge_expectFalse() {
		Graph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("x", "y");
		Assert.assertTrue(graph.hasVertex("x"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testHasVertex_givenEmptyEdge_expectIllegalArgumentException() {
		Graph<String> graph = new UnDirectedGraph<>();
		boolean x = graph.hasEdge("x", "y");
	}

	@Test
	public void testHasVertex_givenAnEdge_expectTrue() {
		Graph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("x", "y");
		boolean x = graph.hasEdge("x", "y");

		Assert.assertTrue(x);
	}

	@Test
	public void testVertices_givenEmptyGraph_expectZero() {
		Graph<String> graph = new UnDirectedGraph<>();
		List<String> vertices = graph.vertices();
		Assert.assertEquals(vertices.size(), 0);
	}

	@Test
	public void testVertices_given2EdgeWith3Vertex_expect3() {
		Graph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("x", "y");
		graph.addEdge("x", "z");
		List<String> vertices = graph.vertices();
		Assert.assertEquals(vertices.size(), 3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAdjacentTo_givenEmptyGraph_expectIllegalArgumentException() {
		Graph<String> graph = new UnDirectedGraph<>();
		List<String> vertices = graph.adjacentTo(":x");
	}

	@Test
	public void testAdjacentTo_givenEdge_expectAdjacentList() {
		Graph<String> graph = new UnDirectedGraph<>();
		graph.addEdge("x", "y");
		graph.addEdge("x", "z");
		graph.addEdge("x", "u");
		graph.addEdge("x", "v");

		List<String> vertices = graph.adjacentTo("x");
		Assert.assertEquals(vertices.size(), 4);
	}
}