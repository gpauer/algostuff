package za.bbd.algo.strategy;

public class InsertionSort implements SortingStrategy {

    @Override
    public int[] sort(int[] numbers) {
        int n = numbers.length;
        for (int i = 1; i < n; i++) {
            int key = numbers[i];
            int j = i - 1;

            while (j >= 0 && numbers[j] > key) {
                numbers[j + 1] = numbers[j];
                j = j - 1;
            }
            numbers[j + 1] = key;
        }

        return numbers; // Return the sorted array
    }
}

