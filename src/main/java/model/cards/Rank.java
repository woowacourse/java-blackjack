package model.cards;

import java.util.List;
import java.util.stream.Stream;

public enum Rank {
    TWO(List.of(2), "2"),
    THREE(List.of(3), "3"),
    FOUR(List.of(4), "4"),
    FIVE(List.of(5), "5"),
    SIX(List.of(6), "6"),
    SEVEN(List.of(7), "7"),
    EIGHT(List.of(8), "8"),
    NINE(List.of(9), "9"),
    TEN(List.of(10), "10"),
    JACK(List.of(10), "J"),
    QUEEN(List.of(10), "Q"),
    KING(List.of(10), "K"),
    ACE(List.of(1, 11), "A");

    private static final String WARNING_INVALID_RANK = "[ERROR] 존재하지 않는 카드 랭크입니다.";

    private final List<Integer> value;
    private final String symbol;

    Rank(List<Integer> value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public static Rank of(String symbol) {
        return Stream.of(Rank.values())
                .filter(rank -> rank.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(WARNING_INVALID_RANK));
    }

    public String getSymbol() {
        return symbol;
    }

    public List<Integer> getValue() {
        return value;
    }
}
