package com.prometheus.algorithms.quicksort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    private int compareCounter = 0;

    public static void main(String[] args) {
        QuickSort test = new QuickSort();
        int dataArray[] = test.createDataArray("array.txt");
        if (dataArray == null) return;

        System.out.println(Arrays.toString(dataArray));
        test.quickSort(dataArray, 0, dataArray.length - 1);
        System.out.println(test.compareCounter);
        System.out.println(Arrays.toString(dataArray));
    }

    private void quickSort(int data[], int p, int r) {
        if (p < r) {
            int q = partitionMedian(data, p, r);
            quickSort(data, p, q-1);
            quickSort(data, q + 1, r);
        }
    }

    private int partition(int data[], int p, int r) {
        compareCounter += r - p;
        int x = data[r];
        int i = p-1;
        for (int j = p; j < r; j++){
            if (data[j] <= x) {
                i = i + 1;
                changeElement(data, i, j);
            }
        }

        changeElement(data, i + 1, r);

        return i + 1;
    }

    private int partitionFirstElement(int data[], int p, int r) {
        changeElement(data, p, r);
        return partition(data, p, r);
    }

    private int partitionMedian(int data[], int p, int r) {
        int first = data[p];
        int last = data[r];
        int middle = data[(p + r) / 2];
        if ((first < last && first >= middle) || (first > last && first <= middle)) {
            changeElement(data, p, r);
            return partition(data, p, r);
        } else if ((middle <= first && middle > last) || (middle >= first && middle < last)) {
            changeElement(data, (p + r) / 2, r);
            return partition(data, p, r);
        } else {
            return partition(data, p, r);
        }
    }

    private void changeElement(int data[], int p, int r) {
        int cash = data[p];
        data[p] = data[r];
        data[r] = cash;
    }

    private int[] createDataArray(String fileName) {
        try (Scanner sc = new Scanner(new File(fileName))){
            int size = 0;
            if (sc.hasNextInt()) {
                size = sc.nextInt();
            }

            if (size > 0) {
                int dataArray[] = new int[size];
                for (int i = 0; i < size; i++) {
                    if (sc.hasNextInt()) {
                        dataArray[i] = sc.nextInt();
                    }
                }

                return dataArray;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
