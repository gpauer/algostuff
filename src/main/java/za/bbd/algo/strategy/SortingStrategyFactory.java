package za.bbd.algo.strategy;

import org.springframework.stereotype.Component;

import za.bbd.constants;

import java.util.HashMap;
import java.util.Map;
import za.bbd.Constants;

@Component
public class SortingStrategyFactory {

    private final Map<String, SortingStrategy> strategyMap;

    public SortingStrategyFactory() {
        this.strategyMap = new HashMap<>();
       
        strategyMap.put(Constants.BUBBLE_SORT, new BubbleSort());
        strategyMap.put(Constants.QUICK_SORT, new QuickSort());
        strategyMap.put(Constants.INSERTION_SORT, new InsertionSort());
        strategyMap.put(Constants.LINEAR_SORT, new LinearSort());
       
    }

    public SortingStrategy getSortingStrategy(String algorithm) {
        SortingStrategy strategy = strategyMap.get(algorithm);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
        return strategy;
    }
}

