package domain.card;

import java.util.Arrays;
import java.util.List;

public enum CardType {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    CardType(final String name) {
        this.name = name;
    }

    public static List<CardType> getAllCardTypes() {
        return Arrays.stream(CardType.values())
                .toList();
    }

    public String getName() {
        return name;
    }
}
