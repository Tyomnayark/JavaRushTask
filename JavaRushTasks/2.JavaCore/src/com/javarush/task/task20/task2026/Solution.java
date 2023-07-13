package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/

public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;
        int n = a.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (a[i][j] == 1) {
                    count++;
                    exploreRectangle(a, i, j);
                }
            }
        }

        return count;
    }

    private static void exploreRectangle(byte[][] a, int row, int col) {
        int n = a.length;

        if (row < 0 || row >= n || col < 0 || col >= n || a[row][col] != 1) {
            return;
        }

        a[row][col] = -1; // Пометить прямоугольник как посещенный

        exploreRectangle(a, row - 1, col); // Исследовать верхний сосед
        exploreRectangle(a, row + 1, col); // Исследовать нижний сосед
        exploreRectangle(a, row, col - 1); // Исследовать левого соседа
        exploreRectangle(a, row, col + 1); // Исследовать правого соседа
    }
}
