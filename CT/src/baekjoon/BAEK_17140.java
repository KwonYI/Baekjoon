package baekjoon;

// Ǫ����
import java.io.*;
import java.util.*;

public class BAEK_17140 {

	static int r, c, k;
	static int time;
	static ArrayList<ArrayList<Integer>> arr;
	
	static ArrayList<Integer> sub = new ArrayList<>();
	static HashMap<Integer, Integer> countKey = new HashMap<>(); // �� : Ƚ��
	static TreeMap<Integer, PriorityQueue<Integer>> sortMap = new TreeMap<>(); // Ƚ�� : ��

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < 3; i++) {

			st = new StringTokenizer(bf.readLine());
			arr.add(new ArrayList<Integer>());

			for (int j = 0; j < 3; j++) {
				arr.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		
		time = 0;
		int R = 3; // �� ����
		int C = 3; // �� ����
		
		while(getRC() != k) {
			if(time > 100) {
				time = -1;
				break;
			}
			
			if(R >= C) {
				for (int row = 0; row < R; row++) {
					sort(true, row);
				}
			}else {
				for (int col = 0; col < C; col++) {
					sort(false, col);
				}
			}
			
			time++;
		}
		
		System.out.println(time);
	}
	
	private static void sort(boolean isR, int index) { // �� : Ƚ��
		countKey.clear();
		
		if(isR) { // �� ����
			for (int ele : arr.get(index)) {
				if(ele == 0) continue;
				countKey.put(ele, countKey.getOrDefault(ele, 0) + 1);
			}
		}else { // �� ����
			for (int rowIndex = 0; rowIndex < arr.size(); rowIndex++) {
				int ele = arr.get(rowIndex).get(index);
				
				if(ele == 0) continue;
				countKey.put(ele, countKey.getOrDefault(ele, 0) + 1);
			}
		}
		sortByValue(countKey);
	}

	private static void sortByValue(HashMap<Integer, Integer> map) { // Ƚ�� : ���� �迭
		for (Integer values : map.values()) {
			
		}
	}

	public static void addZero() {
		
	}
	
	public static int getRC() {
		return arr.get(r - 1).get(c - 1);
	}
}