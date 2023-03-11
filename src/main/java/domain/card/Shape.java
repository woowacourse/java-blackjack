package domain.card;

import java.util.Arrays;
import java.util.List;

public enum Shape {

    HEART,
    SPADE,
    DIAMOND,
    CLOVER;

    static List<Shape> findAllCardPattern() {
        return Arrays.asList(values());
    }
}
