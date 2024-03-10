package view;

import domain.card.Rank;

import java.util.Arrays;

public enum RankView {

    SMALL_ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    BIG_ACE("A");

    private static final String UNKNOWN_RANK = "존재하지 않는 번호입니다.";
    private final String viewName;

    RankView(final String viewName) {
        this.viewName = viewName;
    }

    public static String findName(final Rank rank) {
        return Arrays.stream(values())
                .filter(rankView -> rankView.name().equals(rank.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNKNOWN_RANK))
                .viewName;
    }
}
