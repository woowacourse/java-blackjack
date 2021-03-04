package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum CardValue {
    ACE("A", Arrays.asList(1, 11)),
    TWO("2", Arrays.asList(2)),
    THREE("3", Arrays.asList(3)),
    FOUR("4", Arrays.asList(4)),
    FIVE("5", Arrays.asList(5)),
    SIX("6", Arrays.asList(6)),
    SEVEN("7", Arrays.asList(7)),
    EIGHT("8", Arrays.asList(8)),
    NINE("9", Arrays.asList(9)),
    TEN("10", Arrays.asList(10)),
    JACK("J", Arrays.asList(10)),
    QUEEN("Q", Arrays.asList(10)),
    KING("K", Arrays.asList(10));

    private final String name;
    private final List<Integer> values;

    CardValue(String name, List<Integer> values) {
        this.name = name;
        this.values = values;
    }

    public String getValue() {
        return this.name;
    }

    public List<Integer> getScores() {
        return new ArrayList<>(values);
    }
}
