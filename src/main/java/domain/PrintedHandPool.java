package domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrintedHandPool {
    Set<List<String>> printedHandPool;

    public PrintedHandPool() {
        printedHandPool = new HashSet<>();
    }

    public void add(List<String> hand) {
        printedHandPool.add(hand);
    }

    public boolean exist(List<String> hand) {
        return printedHandPool.contains(hand);
    }
}
