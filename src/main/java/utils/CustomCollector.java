package utils;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollector {

    public static <T> Collector<T, ?, T> toSingleElement() {
        return Collectors.collectingAndThen(
            Collectors.toList(),
            list -> {
                validateSize(list);
                return list.get(0);
            }
        );
    }

    private static <T> void validateSize(List<T> list) {
        if (list.size() != 1) {
            throw new IllegalStateException();
        }
    }
}
