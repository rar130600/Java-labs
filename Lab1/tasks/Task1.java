package tasks;

import interfaceTasks.InterfaceTask1;

public final class Task1 implements InterfaceTask1 {

    @Override
    public int[][] createMatrix(int line, int columns) {
        int[][] matrix = new int[line][columns];

        for (int i = 0; i < line; i++) {
            for (int j = 1; j < columns; j++) {
                matrix[i][j] = 0;
            }
            matrix[i][0] = 1;
        }

        return matrix;
    }

    @Override
    public void printMatrix(int[][] matrix) {
        for (int[] iline : matrix) {
            for (int j : iline) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
