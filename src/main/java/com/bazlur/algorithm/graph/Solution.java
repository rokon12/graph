package com.bazlur.algorithm.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Solution {
	private static int lonelyInteger(int[] a) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i : a) {
			if (map.get(i) != null) {
				Integer value = map.get(i);
				map.put(i, ++value);
			} else {
				map.put(i, 1);
			}
		}

		Optional<Integer> first = map.entrySet().stream().filter(integerIntegerEntry -> integerIntegerEntry.getValue() == 1).map(Map.Entry::getKey).findFirst();
		return first.get();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a = in.nextInt();
		int b = in.nextInt();

		int max = 0;

		for (int i = a; i <= b; i++) {
			for (int j = a; j <= b; j++) {
				int xor = i ^ j;
				if (xor > max) {
					max = xor;
				}
			}
		}
		System.out.println(max);
	}

}
