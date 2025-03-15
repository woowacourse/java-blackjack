package blackjack.domain.card;

import java.util.List;

public enum CardRank {

    TWO(List.of(2)), THREE(List.of(3)), FOUR(List.of(4)), FIVE(List.of(5)), SIX(List.of(6)),
    SEVEN(List.of(7)), EIGHT(List.of(8)), NINE(List.of(9)), TEN(List.of(10)),
    JACK(List.of(10)), QUEEN(List.of(10)), KING(List.of(10)), ACE(List.of(1, 11));

    private final List<Integer> values;

    CardRank(List<Integer> values) {
        this.values = values;
    }

    public List<Integer> getValues() {
        return values;
    }
}
