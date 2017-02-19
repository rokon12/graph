package com.bazlur.algorithm.graph;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public class BreadthFirstSearchTraversalTest {
	GraphImpl<String> g;

	@Before
	public void makeGraphs() {
		g = new GraphImpl<>();
		g.addEdge("A", "B");
		g.addEdge("B", "C");
		g.addEdge("B", "D");
		g.addEdge("B", "A");
		g.addEdge("B", "E");
		g.addEdge("B", "F");
		g.addEdge("C", "A");
		g.addEdge("D", "C");
		g.addEdge("E", "B");
		g.addEdge("F", "B");
	}

	private void expectIteration(String answer, Iterable<String> iterable) {
		StringBuilder sb = new StringBuilder();
		for (String anIterable : iterable) {
			sb.append(' ').append(anIterable);
		}
		System.out.println(sb.toString());
		assertEquals(answer, sb.substring(1));
	}

	@Test
	public void test() {
		expectIteration("A B C D E F", new BreadthFirstSearchTraversal<>(g, "A"));
		expectIteration("A B C D E F", new PreOrderBreadthFirstTraversal<>(g, "A"));
		expectIteration("B C A D E F", new PreOrderBreadthFirstTraversal<>(g, "B"));
	}
}