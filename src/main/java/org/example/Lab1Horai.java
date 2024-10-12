package org.example;

import java.util.Random;
import java.util.Scanner;

public class Lab1Horai {
    public static void main(String[] args) {

        int[][] FirstMatrix;
        int[][] SecondMatrix;

        try {
            System.out.println("FIRST MATRIX: ENTER ROWS & COLUMNS");
            FirstMatrix = generateMatrices();
            System.out.println("SECOND MATRIX: ENTER ROWS & COLUMNS");
            SecondMatrix = generateMatrices();
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ERROR" + e.getLocalizedMessage());
        }

        int[][] FinalMatrix;
        System.out.println("FIRST RANDOM MATRIX");
        printMatrix(FirstMatrix);
        System.out.println("SECOND RANDOM MATRIX");
        printMatrix(SecondMatrix);
        try {
            FinalMatrix = multiplication(FirstMatrix, SecondMatrix);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("ERROR: CANNOT MULTIPLY MATRICES OF THESE SIZES\n" +
                    e.getLocalizedMessage());
        }

        System.out.println("FINAL MATRIX");
        printMatrix(FinalMatrix);

        try {
            double[] Average = getAverageRowValues(FinalMatrix);
            System.out.println("AVERAGE");
            showArray(Average);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("EXCEPTION\n" +
                    e.getLocalizedMessage());
        }
    }

    public static int[][] generateMatrices() throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        int[] values = new int[2];
        for (int i = 0; i < 2; i++) {
            if (!scanner.hasNextInt()) {
                scanner.close();
                throw new IllegalArgumentException("WRONG TYPE");
            }
            int value = scanner.nextInt();
            if (value <= 0) {
                scanner.close();
                throw new IllegalArgumentException("ENTER POSITIVE NUMBER");
            }
            values[i] = value;
        }
        return generateRandomMatrix(values[0], values[1]);
    }

    public static void showArray(double[] array) {
        System.out.print("{ ");
        for (double v : array) {
            System.out.print(v + " ");
        }
        System.out.println("}");
    }

    public static double[] getAverageRowValues(int[][] matrix) throws IllegalArgumentException {
        if (matrix.length == 0) {
            throw new IllegalArgumentException("EMPTY MATRIX");
        }
        double[] result = new double[matrix.length];
        double sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                sum += matrix[i][j];
            }
            result[i] = sum / matrix[i].length;
        }
        return result;
    }

    public static int[][] multiplication(int[][] First, int[][] Second) throws IllegalArgumentException {
        try {
            validateDoubleArrays(First);
            validateDoubleArrays(Second);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("ERROR:\n" + e.getLocalizedMessage());
        }

        if (First[0].length != Second.length) {
            throw new IllegalArgumentException(String.format("Matrices have sizes not comparable with multiplying: a[%d][%d], b[%d][%d]", First.length, First[0].length, Second.length, Second[0].length));
        }

        int[][] result = new int[First.length][Second[0].length];
        for (int i = 0; i < First.length; i++) {
            for (int j = 0; j < Second[0].length; j++) {
                for (int k = 0; k < First[0].length; k++) {
                    result[i][j] += First[i][k] * Second[k][j];
                }
            }
        }
        return result;
    }

    public static void validateDoubleArrays(int[][] doubleArray) throws IllegalArgumentException {
        int rowLength = doubleArray[0].length;
        for (int[] longs : doubleArray) {
            if (longs.length != rowLength) {
                System.out.println("ERROR:");
                printMatrix(doubleArray);
                throw new IllegalArgumentException("ERROR");
            }
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] longs : matrix) {
            System.out.print("{ ");
            for (int aLong : longs) {
                System.out.print(aLong + " ");
            }
            System.out.println("}");
        }
        System.out.println();
    }

    public static int[][] generateRandomMatrix(int rows, int columns) {
        int[][] matrix = new int[rows][columns];
        Random random = new Random();
        int valueRange = 190;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt() % valueRange;
            }
        }
        return matrix;
    }
}