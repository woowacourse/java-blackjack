package team.blackjack.domain;

import java.util.List;

public enum Rank {
    ACE("A", true, List.of(1, 11)),
    TWO("2", false, List.of(2)),
    THREE("3", false, List.of(3)),
    FOUR("4", false, List.of(4)),
    FIVE("5", false, List.of(5)),
    SIX("6", false, List.of(6)),
    SEVEN("7", false, List.of(7)),
    EIGHT("8", false, List.of(8)),
    NINE("9", false, List.of(9)),
    TEN("10", false, List.of(10)),
    JACK("J", false, List.of(10)),
    QUEEN("Q", false, List.of(10)),
    KING("K", false, List.of(10));

    private final String name;
    private final boolean isAce;
    private final List<Integer> score;

    Rank(String name, boolean isAce, List<Integer> score) {
        this.name = name;
        this.isAce = isAce;
        this.score = score;
    }

    public boolean isAce() {
        return isAce;
    }

    public List<Integer> getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}
