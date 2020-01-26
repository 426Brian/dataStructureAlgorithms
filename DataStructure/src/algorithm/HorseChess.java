package algorithm;

import java.awt.*;
import java.util.ArrayList;

public class HorseChess {
    // 棋盘列
    private static int X;

    // 棋盘横
    private static int Y;

    private static boolean visited[];

    private static boolean finish;


    public static void main(String[] args) {
        X = 6;
        Y = 6;
        int row = 1;
        int column = 1;

        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];

        long start = System.currentTimeMillis();
        traversalChess(chessboard, row - 1, column - 1, 1);

        long end = System.currentTimeMillis();

        System.out.println("所用时间  == "+(end -start)+"ms");
        for (int[] rows : chessboard) {
            for(int step :rows){
                System.out.print(step+"\t");
            }
            System.out.println();
        }
    }

    /**
     * 骑士周游
     *
     * @param chessboard 棋盘
     * @param row        马儿当前位置 行 从0 开始
     * @param column     马儿当前位置 列 从0 开始
     * @param step       马儿走的第几步 起始第 1 步
     */
    public static void traversalChess(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;

        // 该位置已经访问
        visited[row * X + column] = true;

        ArrayList<Point> pointsList = next(new Point(column, row));

        while (!pointsList.isEmpty()) {
            Point point = pointsList.remove(0);

            if (!visited[point.y * X + point.x]) {
                traversalChess(chessboard, point.y, point.x, step + 1);
            }

        }

        if (step < X * Y && !finish) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finish = true;
        }
    }

    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> points = new ArrayList<>();

        Point p1 = new Point();

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            points.add(new Point(p1));
        }

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            points.add(new Point(p1));
        }

        return points;
    }
}
