package za.bbd.algo.strategy;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SortingStrategyFactory {

    private final Map<String, SortingStrategy> strategyMap;

    public SortingStrategyFactory() {
        this.strategyMap = new HashMap<>();
       
        strategyMap.put("bubbleSort", new BubbleSort());
        strategyMap.put("quickSort", new QuickSort());
        strategyMap.put("insertionSort", new InsertionSort());
        strategyMap.put("linearSort", new LinearSort());
       
    }

    public SortingStrategy getSortingStrategy(String algorithm) {
        SortingStrategy strategy = strategyMap.get(algorithm);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
        return strategy;
    }
}

