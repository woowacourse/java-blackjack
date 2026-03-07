package blackjack.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum Denomination {


    ACE("A", 11),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    KING("K", 10),
    JACK("J", 10),
    QUEEN("Q", 10);

    private final String symbol;
    private final int score;


    Denomination(String symbol, int score) {
        this.symbol = symbol;
        this.score = score;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Denomination pick(final String symbol) {
        return Arrays.stream(values())
            .filter(card -> Objects.equals(card.symbol, symbol))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("올바른 카드가 아닙니다."));
    }

    public int toScore() {
        return score;
    }
}
