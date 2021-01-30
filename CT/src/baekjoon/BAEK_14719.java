package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14719 {

	static int N, M;
	static int[] map;
	static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M];

		int maxIndex = 0; // ���� ���� ��� �ε��� ã��
		int maxHeight = 0;

		st = new StringTokenizer(bf.readLine());
		for (int r = 0; r < M; r++) {
			int height = Integer.parseInt(st.nextToken());
			if (height >= maxHeight) {
				maxHeight = height;
				maxIndex = r;
			}
			map[r] = height;
		}

		maxHeight = map[0]; // ���� ���� ���� ��� ����, �ʱⰪ�� ���� ����
		for (int i = 1; i < maxIndex; i++) {
			if (map[i] < maxHeight) total += (maxHeight - map[i]);
			else 	maxHeight = map[i];
		}

		maxHeight = map[M - 1]; // ���� ���� ���� ��� ����, ���� ������ ������ �ʱ�ȭ
		for (int i = M - 2; i > maxIndex; i--) {
			if (map[i] < maxHeight) total += (maxHeight - map[i]);
			else maxHeight = map[i];
		}

		System.out.println(total);
	}
}