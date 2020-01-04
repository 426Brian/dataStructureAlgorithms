import java.util.Arrays;

public class Maze {
    public static void main(String[] args) {
        int[][] map = new int[8][7];

        // 上下使用1 表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }

        // 左右全部为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        // 设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("地图情况 === ");
        for (int[] row : map) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        setWay(map, 1, 1);
        System.out.println("标识地图情况 === ");
        for (int[] row : map) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }


    /**
     * map 表示地图 i，j 表示从地图的哪个位置开始出发(1, 1)
     * 如果小球能找到 map[6][5]，则说明通路找到
     * 约定小球能到map[0][0]说明没有走过，1 表示墙，2表示可以走
     * 3 表示走过，但是走不通
     * 走迷宫时，需要确定一个策略 下—> 右->上 —> 左
     *
     * @param map 地图
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            // 通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {
                // 没走过

                // 可以走
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    // 向下走
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    // 向右走
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    // 向上走
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    // 向左走
                    return true;
                } else {
                    // 走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }

    }
}
