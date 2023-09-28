package za.bbd.algo.strategy;

import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class SortingStrategyFactory {

    private final Map<String, SortingStrategy> strategyMap;

    public SortingStrategyFactory() {
        this.strategyMap = new HashMap<>();
       
        strategyMap.put("bubble", new BubbleSort());
        strategyMap.put("quick", new QuickSort());
        strategyMap.put("insertion", new InsertionSort());
        strategyMap.put("linear", new LinearSort());
       
    }

    public SortingStrategy getSortingStrategy(String algorithm) {
        SortingStrategy strategy = strategyMap.get(algorithm);
        if (strategy == null) {
            throw new IllegalArgumentException("Invalid sorting algorithm: " + algorithm);
        }
        return strategy;
    }
}

