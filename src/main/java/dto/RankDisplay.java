package dto;

import domain.Rank;
import java.util.Arrays;

public enum RankDisplay {

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
    ACE("A");

    private final String text;

    RankDisplay(final String text) {
        this.text = text;
    }

    public static RankDisplay from(final Rank rank) {
        return Arrays.stream(RankDisplay.values())
                .filter(rankDisplay -> rankDisplay.name().equals(rank.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 수로 변환하려고 합니다."));
    }

    public String getText() {
        return text;
    }
}
