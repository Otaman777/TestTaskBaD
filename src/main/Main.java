package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Main {
	private static int[] mas;

	public static void main(String[] args) throws IOException {
		
		//Please, set and make sure that here is the correct pathname for 10m.txt
		File f = new File("C:\\Users\\maksi\\eclipse-workspace\\TestTaskBaD\\src\\main\\10m.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		int line;
		mas = new int[9999999];
		for (int i = 0; i < mas.length; i++) {
			mas[i] = line = Integer.parseInt(br.readLine());
		}

		System.out.println("The longest up sequence:");
		the_longest_up_sequence();
		System.out.println("\n The longest down sequence:");
		the_longest_down_sequence();
		System.out.println("\nMAX VALUE = " + max());
		System.out.println("MIN VALUE = " + min());
		System.out.println("AVERAGE VALUE = " + average());
		System.out.println("MEDIANA OF THE ARRAY = " + mediana());
	}

	private static int max() {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < mas.length - 1; i++) {
			if (mas[i] > mas[i + 1] && max < mas[i]) {
				max = mas[i];
			} else if (mas[i] < mas[i + 1] && max < mas[i + 1]) {
				max = mas[i + 1];
			}
		}
		return max;
	}

	private static int min() {
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < mas.length - 1; i++) {
			if (mas[i] < mas[i + 1] && min > mas[i]) {
				min = mas[i];
			} else if (mas[i] > mas[i + 1] && min > mas[i + 1]) {
				min = mas[i + 1];
			}
		}
		return min;
	}

	private static int mediana() {
		int mediana = 0;
//		int n = mas.length; // This algorithm works too long
//		for (int i = 0; i < n - 1; i++)
//			for (int j = 0; j < n - i - 1; j++)
//				if (mas[j] > mas[j + 1]) {
//					int temp = mas[j];
//					mas[j] = mas[j + 1];
//					mas[j + 1] = temp;
//				}
		// Unsorted mediana
		// mediana = (mas[((mas.length - 1) / 2)] + mas[((mas.length - 1) / 2) + 1]) / 2
		Arrays.sort(mas);
		mediana = (mas[((mas.length - 1) / 2)] + mas[((mas.length - 1) / 2) + 1]) / 2; // Sorted mediana
		return mediana;
	}

	private static double average() {
		long sum = 0;
		for (int i = 0; i < mas.length; i++)
			sum += mas[i];
		return (double) sum / mas.length;
	}

	private static void the_longest_up_sequence() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int length = 0;
		int max_index = 0;
		for (int i = 0; i < mas.length - 1; i++) {
			if (mas[i + 1] > mas[i]) {
				length++;

			} else if (length > 1) {
				map.put(i - length - 1, length); // i-length-1 – calculating the first cell of the sequence
				length = 0;
			}
		}

		boolean first_iteration = true;
		int max_length = 0;
		for (Map.Entry<Integer, Integer> m : map.entrySet()) {
			if (first_iteration) {
				max_length = m.getValue();
				first_iteration = false;
			} else if (!first_iteration && max_length < m.getValue()) {
				max_length = m.getValue();
				max_index = m.getKey();
			}
		}
		max_index += 2;
		for (int i = max_index; i < max_length + max_index; i++) {
			System.out.println(mas[i]);
		}
	}

	private static void the_longest_down_sequence() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int length = 0;
		int min_index = 0;
		for (int i = 0; i < mas.length - 1; i++) {
			if (mas[i + 1] < mas[i]) {
				length++;

			} else if (length > 1) {
				map.put(i - length - 1, length); // i-length-1 – calculating the first cell of the sequence
				length = 0;
			}
		}
		
		boolean first_iteration = true;
		int min_length = 0;
		for (Map.Entry<Integer, Integer> m : map.entrySet()) {
			if (first_iteration) {
				min_length = m.getValue();
				first_iteration = false;
			} else if (!first_iteration && min_length < m.getValue()) {
				min_length = m.getValue();
				min_index = m.getKey();
			}
		}
		min_index += 1;
		for (int i = min_index; i <= min_length + min_index; i++) {
			System.out.println(mas[i]);
		}
	}

}
