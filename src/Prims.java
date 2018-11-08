import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Prims {
    static int[][] firstRange = {{0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE},
            {4, 0, 8, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 11, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, 8, 0, 7, Integer.MAX_VALUE, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, 2},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 7, 0, 9, 14, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 9, 0, 10, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 4, 14, 10, 0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2, 0, 1, 6},
            {8, 11, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 0, 7},
            {Integer.MAX_VALUE, Integer.MAX_VALUE, 2, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 6, 7, 0}}; // 초기 거리 저장, 연결 되어 있지 않으면 MAX VALUE로
    static PriorityQueue<Node> pq = new PriorityQueue<Node>(); // Node 들이 저장 되어 있는 priority queue
    static String[] parent = {" ", "undefined", "undefined", "undefined", "undefined", "undefined", "undefined", "undefined", "undefined"}; // 각 노드의 부모를 알기 위한 배열

    public static void main(String[] args) {
        prims(0);
    }

    /*prim's 알고리즘 함수 */
    public static void prims(int fst) {

        pq.offer(new Node("a", fst, 0));
        pq.offer(new Node("b", 1, Integer.MAX_VALUE));
        pq.offer(new Node("c", 2, Integer.MAX_VALUE));
        pq.offer(new Node("d", 3, Integer.MAX_VALUE));
        pq.offer(new Node("e", 4, Integer.MAX_VALUE));
        pq.offer(new Node("f", 5, Integer.MAX_VALUE));
        pq.offer(new Node("g", 6, Integer.MAX_VALUE));
        pq.offer(new Node("h", 7, Integer.MAX_VALUE));
        pq.offer(new Node("i", 8, Integer.MAX_VALUE));

        int Sum = 0; // 총 합

        while (!pq.isEmpty()) {
            Node u = pq.poll();

            Iterator<Node> iterator = pq.iterator();
            while (iterator.hasNext()) {
                Node temp = iterator.next();
                // 현재 노드와 연결 되어 있는 노드이면 (거리가 0이 아니거나 MAX가 아닌경우)
                if (firstRange[u.getIndex()][temp.getIndex()] != 0 && firstRange[u.getIndex()][temp.getIndex()] != Integer.MAX_VALUE) {
                    // 현재 저장되어 있는 최소값 보다 작으면 바꿔준다.
                    if (firstRange[u.getIndex()][temp.getIndex()] < temp.getDistance()) {
                        temp.setDistance(firstRange[u.getIndex()][temp.getIndex()]);
                        parent[temp.getIndex()] = u.getName(); // 그 노도의 부모 설정
                    }
                }
            }
            Sum += u.getDistance();

            /*힙 다시 빌드*/
            try {
                Node t = pq.poll();
                pq.add(t);
            } catch (Exception e) {
            }
            System.out.println("w<" + parent[u.getIndex()] + "," + u.getName() + "> = " + u.getDistance());
        }
        System.out.println();
        System.out.println("w<MST> = " + Sum);
    }
}

class Node implements Comparable<Node> {
    String name;
    int index;
    int distance;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public int getDistance() {
        return distance;
    }

    public Node(String name, int index, int distance) {
        this.name = name;
        this.index = index;
        this.distance = distance;
    }

    /*노드의 거리를 비교해 정렬해주기 위해 Override한 compareTo 함수*/
    @Override
    public int compareTo(Node t) {
        if (distance > t.distance) {
            return 1;
        } else if (distance < t.distance) {
            return -1;
        }
        return 0;
    }
}
