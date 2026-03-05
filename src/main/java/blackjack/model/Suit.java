package blackjack.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Suit {
    DIAMOND("다이아몬드"),
    CLUB("클로버"),
    SPADE("스페이드"),
    HEART("하트");

    private final String korean;

    Suit(String korean) {
        this.korean = korean;
    }

    public static Suit random() {
        List<Suit> ranks = Arrays.asList(values());
        Collections.shuffle(ranks);

        return ranks.getFirst();
    }

    public String getKorean() {
        return korean;
    }
}
