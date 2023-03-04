package domain;

import java.util.Arrays;
import java.util.List;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/04
 */
public enum CardInfo {
    A("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    J("J", 10),
    Q("Q", 10),
    K("K", 10);

    private final String name;
    private final int value;

    CardInfo(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int sum(final CardInfo other) {
        return this.value + other.value;
    }

    public boolean isACE(final String ace) {
        return name.equals(ace);
    }

    public int getValue() {
        return value;
    }
}
