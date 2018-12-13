package com.prometheus.algorithms.countingsort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class CountingSort {
    private final static int MAX_LENGTH = 1000;
    public static void main(String[] args) {
        CountingSort sort = new CountingSort();
        String[] charArray = sort.createDataArray("charrows.txt");
        List<Character> mostCommon = sort.getMostCommonChar(charArray);
        sort.radixSort(charArray);
        for (Character character : mostCommon) {
            System.out.println(charArray[0] + character + charArray[MAX_LENGTH - 1]);
        }
    }

    private void radixSort(String[] strings) {
        for (int i = 2; i >= 0; i--) {
            insertionSort(strings, i);
            System.out.println(i + ": " + strings[0]);
        }
    }

    private void insertionSort(String[] strings, int digit) {
        for (int j = 1; j < strings.length; j++) {
            String key = strings[j];
            int i = j -1;
            while (i >= 0 && strings[i].charAt(digit) > key.charAt(digit)) {
                strings[i + 1] = strings[i];
                i = i -1;
            }

            strings[i + 1] = key;
        }
    }

    private List<Character> getMostCommonChar(String[] strings) {
        Map<Character, Integer> charCount = new HashMap<>();

        int maxCount = 0;
        for (String string : strings) {
            char[] row = string.toCharArray();
            for (char newValue : row) {
                charCount.merge(newValue, 1, (a, b) -> a + b);
                if (maxCount < charCount.get(newValue)) {
                    maxCount = charCount.get(newValue);
                }
            }
        }

        final int maximum = maxCount;
        return charCount.entrySet().stream()
                .filter(c -> c.getValue().intValue() == maximum)
                .map(c -> c.getKey())
                .collect(Collectors.toList());
    }

    private String[] createDataArray(String fileName) {
        try (Scanner sc = new Scanner(new File(fileName))){
            String dataArray[] = new String[MAX_LENGTH];
            for (int i = 0; i < MAX_LENGTH; i++) {
                dataArray[i] = sc.nextLine();
            }

            return dataArray;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
