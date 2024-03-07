package dto;

import domain.Rank;

public enum RankDto {

    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), KING("K"), QUEEN("Q");

    private final String rankName;

    RankDto(String rankName) {
        this.rankName = rankName;
    }

    public static RankDto from(Rank rank) {
        return valueOf(rank.getName());
    }

    public String getRankName() {
        return rankName;
    }
}
