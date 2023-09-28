package za.bbd.algo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.bbd.algo.strategy.SortingStrategy;
import za.bbd.algo.strategy.SortingStrategyFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import za.bbd.Constants;

@Service
public class SortingService {

    private final SortingStrategyFactory sortingStrategyFactory;

    @Autowired
    public SortingService(SortingStrategyFactory sortingStrategyFactory) {
        this.sortingStrategyFactory = sortingStrategyFactory;
    }

    public Result sortWithAlgorithm(int[] numbers, String algorithm) {
        SortingStrategy sortingStrategy = sortingStrategyFactory.getSortingStrategy(algorithm);

        long startTime = System.nanoTime();
        int[] sortedArray = sortingStrategy.sort(numbers);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        String timeComplexity = getDefaultTimeComplexity(algorithm);

        // MeasureS memory usage in MB
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        long usedMemoryBytes = heapMemoryUsage.getUsed();
        String usedMemoryMB = (usedMemoryBytes / (1024 * 1024)) + " MB";

        return new Result(sortedArray, timeComplexity, executionTime, usedMemoryMB);
    }

    private String getDefaultTimeComplexity(String algorithm) {
     
        switch (algorithm) {
            case Constants.LINEAR_SORT:
                return "O(n)";
            case Constants.QUICK_SORT:
                return "O(n log n)";
            case Constants.BUBBLE_SORT:
                return "O(n^2)";
            case Constants.INSERTION_SORT:
                return "O(n^2)";
            default:
                return "Unknown"; // Default for unknown algorithms
        }
    }

    // Inner class for SortingResult
    public record Result(int[] sortedArray, String timeComplexity, long executionTime, String usedMemoryMB) { }
}
