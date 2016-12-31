package com.bazlur.algorithm.graph;

import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 12/31/16.
 */
public interface Graph<T extends Comparable<T>> {
	void addEdge(T v, T w);

	int v();

	int e();

	int degree(T v);

	boolean hasVertex(T value);

	boolean hasEdge(T v, T w);

	List<T> vertices();

	List<T> adjacentTo(T value);
}

