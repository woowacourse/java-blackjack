package domain;

import java.util.Arrays;
import java.util.List;

public enum Mark {
    HEART,
    DIAMOND,
    SPADE,
    CLOVER;

    public static List<Mark> getValues() {
        return Arrays.stream(Mark.values())
                .toList();
    }
}
