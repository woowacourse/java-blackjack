package blackjack.domain.card;

import java.util.List;

public enum CardRank {

    TWO(List.of(2), "2"), THREE(List.of(3), "3"), FOUR(List.of(4), "4"), FIVE(List.of(5), "5"), SIX(List.of(6), "6"),
    SEVEN(List.of(7), "7"), EIGHT(List.of(8), "8"), NINE(List.of(9), "9"), TEN(List.of(10), "10"),
    JACK(List.of(10), "J"), QUEEN(List.of(10), "Q"), KING(List.of(10), "K"), ACE(List.of(1, 11), "A");

    private final List<Integer> values;
    private final String description;

    CardRank(List<Integer> values, String description) {
        this.values = values;
        this.description = description;
    }

    public List<Integer> getValues() {
        return values;
    }

    public String getDescription() {
        return description;
    }
}
