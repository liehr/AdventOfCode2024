package de.tudl.advent;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DayOne {
    public ArrayList<Integer> leftList = new ArrayList<>();
    public ArrayList<Integer> rightList = new ArrayList<>();

    public void readFileInput() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Inputs/input_day1.txt"));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split("\\s+");

            if (parts.length == 2) {
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            }
        }
    }

    public void PartOne() {
        try {
            readFileInput();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int[] leftArray = leftList.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArray = rightList.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

        int sum = 0;

        for (int i = 0; i < leftArray.length; i++) {
            int number = leftArray[i] - rightArray[i];
            if (number < 0)
                number *= -1;

            sum += number;
        }

        System.out.println("Total distance: " + sum);
    }

    public void PartTwo()
    {
        try
        {
            readFileInput();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int[] leftArray = leftList.stream().mapToInt(Integer::intValue).toArray();
        int[] rightArray = rightList.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(leftArray);
        Arrays.sort(rightArray);

        Map<Integer, Integer> rightFrequencyMap = new HashMap<>();
        for (int right : rightArray) {
            rightFrequencyMap.put(right, rightFrequencyMap.getOrDefault(right, 0) + 1);
        }

        long totalSum = 0;
        for (int left : leftArray) {
            int occurrences = rightFrequencyMap.getOrDefault(left, 0);
            totalSum += (long) left * occurrences;
        }

        System.out.println("Total similarity score: " + totalSum);
    }
}
