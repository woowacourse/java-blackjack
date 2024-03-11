package dto;

import domain.card.Suit;
import java.util.Arrays;

public enum SuitDiplay {
    HEARTS("하트"),
    DIAMONDS("다이아몬드"),
    SPADES("스페이드"),
    CLUBS("클로버");

    private final String text;

    SuitDiplay(final String text) {
        this.text = text;
    }

    public static SuitDiplay from(final Suit suit) {
        return Arrays.stream(SuitDiplay.values())
                .filter(suitDiplay -> suitDiplay.name().equals(suit.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 값으로 변환하려고 합니다."));
    }

    public String getText() {
        return text;
    }
}
