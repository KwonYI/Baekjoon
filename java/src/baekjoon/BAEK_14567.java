package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14567 {

	static class Lecture {
		int number;
		int semester;

		public Lecture(int number, int semester) {
			this.number = number;
			this.semester = semester;
		}
	}

	static int N, M;
	static int[] lectures; // ���� ������ ���� �������� ��
	static int[] semesters; // ���� ���������� �б�
	static boolean[] completion; // �̹� ������ ����
	static ArrayList<Integer> prereq[]; // ���������

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		lectures = new int[N + 1];
		semesters = new int[N + 1];
		completion = new boolean[N + 1];

		prereq = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			prereq[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());

			int pre = Integer.parseInt(st.nextToken());
			int post = Integer.parseInt(st.nextToken());

			lectures[post]++; // ��������++
			prereq[pre].add(post); // �������� ����
		}

		Queue<Lecture> q = new LinkedList<>();
		for (int number = 1; number <= N; number++) {
			if (lectures[number] == 0) {
				q.add(new Lecture(number, 1));
			}
		}

		while (!q.isEmpty()) {
			Lecture cur = q.poll();

			int number = cur.number;

			if (completion[number]) {
				continue;
			}

			completion[number] = true;
			semesters[number] = cur.semester;

			for (Integer post : prereq[number]) {
				lectures[post]--; // �������� ����
				if(lectures[post] == 0) {
					q.add(new Lecture(post, cur.semester + 1));
				}
			}
		}
		
		for (int number = 1; number <= N; number++) {
			sb.append(semesters[number]).append(' ');
		}
		
		System.out.println(sb);
	}
}