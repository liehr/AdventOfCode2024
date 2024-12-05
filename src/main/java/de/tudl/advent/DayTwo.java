package de.tudl.advent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayTwo {
    List<int[]> numberList = new ArrayList<>();

    public void readFileInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Inputs/input_day2.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");
            int[] number = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
            numberList.add(number);
        }
    }

    public void partOne() {
        int safeCount = 0;

        for (int[] numbers : numberList) {
            if (isValid(numbers)) {
                safeCount++;
            }
        }

        System.out.println("Safe reports (Part One): " + safeCount);
    }

    public void partTwo() {
        int safeCount = 0;

        for (int[] numbers : numberList) {
            if (isValid(numbers) || canBeSafeWithDampener(numbers)) {
                safeCount++;
            }
        }

        System.out.println("Safe reports (Part Two): " + safeCount);
    }

    public boolean isValid(int[] numbers) {
        if (numbers.length < 2) return false;

        String sequenceType = null; // "ascending" or "descending"

        for (int i = 1; i < numbers.length; i++) {
            int diff = numbers[i] - numbers[i - 1];

            if (diff >= 1 && diff <= 3) {
                if (sequenceType == null) sequenceType = "ascending";

                if (!sequenceType.equals("ascending")) return false;

            } else if (diff >= -3 && diff <= -1) {
                if (sequenceType == null) sequenceType = "descending";

                if (!sequenceType.equals("descending")) return false;

            } else {
                return false; // Invalid difference
            }
        }

        return true; // All differences are valid
    }

    public boolean canBeSafeWithDampener(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            // Create a copy of the array without the i-th element
            int[] modifiedNumbers = new int[numbers.length - 1];
            for (int j = 0, k = 0; j < numbers.length; j++) {
                if (j != i) {
                    modifiedNumbers[k++] = numbers[j];
                }
            }

            // Check if the modified array is valid
            if (isValid(modifiedNumbers)) {
                return true; // It can be made safe with one removal
            }
        }

        return false; // No single removal can make it safe
    }

    public static void main(String[] args) throws IOException {
        DayTwo dayTwo = new DayTwo();
        dayTwo.readFileInput();
        dayTwo.partOne();
        dayTwo.partTwo();
    }
}
