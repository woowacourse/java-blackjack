package blackjack.view;

import blackjack.domain.card.Suit;
import java.util.Arrays;

enum OutputSuit {
    HEART("하트"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드"),
    SPADE("스페이드"),
    ;

    private final String name;

    OutputSuit(String name) {
        this.name = name;
    }

    static String convertSuitToString(Suit suit) {
        return Arrays.stream(OutputSuit.values())
                .filter(outputSuit -> outputSuit.name().equals(suit.name()))
                .findAny()
                .map(OutputSuit::getName)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 무늬입니다."));
    }

    private String getName() {
        return this.name;
    }
}
