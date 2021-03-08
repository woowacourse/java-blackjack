package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum CardValue {
    ACE("A", Arrays.asList(1, 11)),
    TWO("2", Collections.singletonList(2)),
    THREE("3", Collections.singletonList(3)),
    FOUR("4", Collections.singletonList(4)),
    FIVE("5", Collections.singletonList(5)),
    SIX("6", Collections.singletonList(6)),
    SEVEN("7", Collections.singletonList(7)),
    EIGHT("8", Collections.singletonList(8)),
    NINE("9", Collections.singletonList(9)),
    TEN("10", Collections.singletonList(10)),
    JACK("J", Collections.singletonList(10)),
    QUEEN("Q", Collections.singletonList(10)),
    KING("K", Collections.singletonList(10));

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
