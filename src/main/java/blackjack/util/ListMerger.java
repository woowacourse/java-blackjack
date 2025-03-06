package blackjack.util;

import java.util.ArrayList;
import java.util.List;

public class ListMerger {

    private ListMerger() {
    }

    public static <T> List<T> combine(T element, List<T> elements) {
        List<T> result = new ArrayList<>();
        result.add(element);
        result.addAll(elements);
        return result;
    }
}
