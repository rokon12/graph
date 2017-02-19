package com.bazlur.algorithm.graph.grap2;

import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 * <p>
 * ref: http://codereview.stackexchange.com/questions/48518/depth-first-search-breadth-first-search-implementation
 */
public class GraphTest {

	private static class CrumbTrailVisitor implements Graph.Visitor<String> {
		private StringBuilder sb = new StringBuilder();

		public void visit(String vertex) {
			sb.append(' ').append(vertex);
		}

		public String toString() {
			return sb.substring(1);
		}
	}

	public static Graph<String> graph1;

	@BeforeClass
	public static void makeGraphs() {
		Graph<String> g = graph1 = new Graph<>();
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

	@Test
	public void breadthFirstVisitorFromB() {
		Graph.Visitor<String> crumbTrailVisitor = new CrumbTrailVisitor();
		graph1.preformBbsTraversals("B", crumbTrailVisitor);
		assertEquals("B C D A E F", crumbTrailVisitor.toString());
	}

}