package blackjack.model.cards;

import java.util.stream.Stream;

public enum Suit {
    CLUBS("클로버"),
    DIAMONDS("다이아몬드"),
    HEARTS("하트"),
    SPADES("스페이드");

    private static final String WARNING_INVALID_SUIT = "[ERROR] 존재하지 않는 카드 무늬입니다.";

    private final String name;

    Suit(String name) {
        this.name = name;
    }

    public static Suit of(String name) {
        return Stream.of(Suit.values())
                .filter(suit -> suit.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(WARNING_INVALID_SUIT));
    }

    public String getName() {
        return name;
    }
}
