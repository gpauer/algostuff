package za.bbd.algo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.bbd.algo.strategy.SortingStrategy;
import za.bbd.algo.strategy.SortingStrategyFactory;
import za.bbd.algo.model.DataRequest;
import za.bbd.algo.model.DataResponse; 

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import za.bbd.Constants;
import java.util.ArrayList;

@Service
public class SortingService {

    private final SortingStrategyFactory sortingStrategyFactory;

    //@Autowired
    public SortingService(SortingStrategyFactory sortingStrategyFactory) {
        this.sortingStrategyFactory = sortingStrategyFactory;
    }

    public DataResponse sortWithAlgorithm(DataRequest request) {
        int[] numbers = request.getData().stream().mapToInt(Integer::intValue).toArray(); 
        String algorithm = request.getAlgo();
        SortingStrategy sortingStrategy = sortingStrategyFactory.getSortingStrategy(algorithm);

        long startTime = System.nanoTime();
        int[] sortedArray = sortingStrategy.sort(numbers);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        String timeComplexity = getDefaultTimeComplexity(algorithm);

        // Measure memory usage
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
        long usedMemoryBytes = heapMemoryUsage.getUsed();
        String usedMemoryMB = (usedMemoryBytes / (1024 * 1024)) + " MB";

        DataResponse response = new DataResponse();
        ArrayList<Integer> sortedArrayList = new ArrayList<>();
        for (Integer element : sortedArray) {
        	sortedArrayList.add(element);
        }
        response.setAlgo(algorithm);
        response.setTimeInMillis(executionTime / 1_000_000); 
        response.setMemoryMBString(usedMemoryMB);
        response.setData(sortedArrayList);
        response.setTimeComplexity(timeComplexity);

        return response;
    }

    private String getDefaultTimeComplexity(String algorithm) {
        // Define default time complexities for known sorting algorithms
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
}
