package blackjack.domain;

import java.util.List;

public enum Denomination {
    ACE("A", List.of(1, 11)),
    TWO("2", List.of(2)),
    THREE("3", List.of(3)),
    FOUR("4", List.of(4)),
    FIVE("5", List.of(5)),
    SIX("6", List.of(6)),
    SEVEN("7", List.of(7)),
    EIGHT("8", List.of(8)),
    NINE("9", List.of(9)),
    TEN("10", List.of(10)),
    JACK("J", List.of(10)),
    QUEEN("Q", List.of(10)),
    KING("K", List.of(10));

    private final String text;
    private final List<Integer> values;

    Denomination(String text, List<Integer> values) {
        this.text = text;
        this.values = values;
    }

    public static int changeAceValue(int sum) {
        Integer minAceValue = ACE.values.getFirst();
        Integer maxAceValue = ACE.values.getLast();

        int changedAceValue = sum + maxAceValue - minAceValue;
        if (changedAceValue <= 21) {
            return sum;
        }
        return changedAceValue;
    }

    public String getText() {
        return text;
    }

    public List<Integer> getValues() {
        return values;
    }
}
