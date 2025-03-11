package domain;

import java.util.Arrays;

public enum CardShape {
    DIA(1),
    HEART(2),
    SPADE(3),
    CLOVER(4),
    ;

    private final int index;

    CardShape(int index) {
        this.index = index;
    }

}
