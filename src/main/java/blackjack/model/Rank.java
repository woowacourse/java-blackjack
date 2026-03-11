package blackjack.model;

import java.util.List;

public enum Rank {
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

    private final String name;
    private final List<Integer> score;

    Rank(String name, List<Integer> score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getScore() {
        return score;
    }

}
