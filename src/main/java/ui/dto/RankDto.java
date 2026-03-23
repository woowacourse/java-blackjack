package ui.dto;

import domain.card.Rank;

public enum RankDto {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    J("J"),
    Q("Q"),
    K("K");

    private final String displayValue;

    RankDto(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public static RankDto toDto(Rank rank) {
        return RankDto.valueOf(rank.name());
    }
}
