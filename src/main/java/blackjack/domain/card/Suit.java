package blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Suit {
    HEART("하트"),
    SPADE("스페이드"),
    CLOVER("클로버"),
    DIAMOND("다이아몬드");

    private final String name;

    Suit(final String name) {
        this.name = name;
    }

    public static Suit pickRandom() {
        List<Suit> suits = Arrays.asList(Suit.values());
        Collections.shuffle(suits);

        return suits.get(0);
    }

    public String getName() {
        return name;
    }
}
