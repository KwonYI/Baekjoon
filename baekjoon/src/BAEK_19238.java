import java.io.*;
import java.util.*;

public class BAEK_19238 {

	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		int dis;

		public Pos(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}

		public int compareTo(Pos o) { // �� ������ �� ���鼭 �� ����
			if (this.dis == o.dis) {
				if (this.r == o.r) return this.c - o.c;
				else return this.r - o.r;
			}
			return this.dis - o.dis;
		}
	}

	static int N, M, fuel;
	static int[][] map;
	static boolean[][] visited;
	static HashMap<Integer, Pos> passengers = new HashMap<Integer, Pos>();
	
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Pos> emptyTaxi = new PriorityQueue<Pos>(); // �� �ý�
		st = new StringTokenizer(bf.readLine());

		int tr = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken());

		emptyTaxi.add(new Pos(tr, tc, 0));

		for (int i = 2; i < M + 2; i++) {
			st = new StringTokenizer(bf.readLine());

			int pr = Integer.parseInt(st.nextToken());
			int pc = Integer.parseInt(st.nextToken());

			map[pr][pc] = i;

			int ar = Integer.parseInt(st.nextToken());
			int ac = Integer.parseInt(st.nextToken());

			passengers.put(i, new Pos(ar, ac, 0));
		}

		int passengerNum = 0; // �°� ��ȣ
		Pos taxi = null; // �ý� ��ġ
		Pos destination = null; // ������ ��ġ
		Queue<Pos> inTaxi = new LinkedList<Pos>(); // �°��� ź �ý�
		
		ex : while (true) {
			// 1. �°� ã��
			while(!emptyTaxi.isEmpty()) {
				taxi = emptyTaxi.poll();
				
				int r = taxi.r;
				int c = taxi.c;
				int dis = taxi.dis;

				if(dis > fuel) break ex; // �⸧ �����ϸ� �׸� ��
				
				if(map[r][c] > 1) {
					fuel -= dis;
					
					inTaxi.add(new Pos(r, c, 0)); // �°� �¿�
					passengerNum = map[r][c]; // �ʿ��� �����ֱ����� �˾Ƶд�
					destination = passengers.get(passengerNum); // ������ null
					map[r][c] = 0; // �°���ġ ����ֱ�
					
					break;
				}
				
				if(visited[r][c]) continue;
				visited[r][c] = true;
				
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					
					if(!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
					
					emptyTaxi.add(new Pos(nr, nc, dis + 1));
				}
			}
			
			if(destination == null) break; // �°��� �� ã������ ����
			init(); // �湮�迭 �ʱ�ȭ
			emptyTaxi.clear(); // �°�ã�� �ý�ť ����ֱ�
			
			// 2. ã�� �°����� ������ ����
			while (!inTaxi.isEmpty()) {
				taxi = inTaxi.poll();
				
				int r = taxi.r;
				int c = taxi.c;
				int dis = taxi.dis;
				
				if(dis > fuel) break ex; // �⸧ �����ϸ� �׸� ��
				
				if(r == destination.r && c == destination.c) {
					fuel -= dis;
					
					fuel += dis*2; // ���� ä��
					passengers.remove(passengerNum); // �°� ���Ⱦ�
					emptyTaxi.add(new Pos(r, c, 0)); // �� ��ġ���� �ٽ� �°� ã��
					M--; // �°��� �ٿ�
					if(M == 0) break ex; // �� �������� ����
					
					break;
				}
				
				if(visited[r][c]) continue;
				visited[r][c] = true;
				
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					
					if(!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
					
					inTaxi.add(new Pos(nr, nc, dis + 1));
				}
				
			}
			
			init(); // �湮�迭 �ʱ�ȭ
			inTaxi.clear(); // ������ ã�� ť ����ֱ�
			destination = null; // ������ ��ġ �ʱ�ȭ
		}
		
		if(M != 0) fuel = -1;
		
		System.out.println(fuel);
	}
	
	private static void init() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				visited[r][c] = false;
			}
		}
	}

	private static boolean check(int r, int c) {
		if(r > N || c > N || r < 1 || c < 1 ) return false;
		return true;
	}
}
