package za.bbd.algo.strategy;

public class BubbleSort implements SortingStrategy {

    @Override
    public int[] sort(int[] numbers) {
        int n = numbers.length;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (numbers[i - 1] > numbers[i]) {
                    // Swap numbers[i-1] and numbers[i]
                    int temp = numbers[i - 1];
                    numbers[i - 1] = numbers[i];
                    numbers[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return numbers; // Return the sorted array
    }
}
