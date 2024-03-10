package blackjack.view.mapper;

import blackjack.domain.Rank;
import java.util.Arrays;

public enum RankMapper {
    ACE("A"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
    SIX("6"), SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"),
    JACK("J"), KING("K"), QUEEN("Q");

    private final String rankName;

    RankMapper(String rankName) {
        this.rankName = rankName;
    }

    public static RankMapper findByRank(Rank rank) {
        return Arrays.stream(values())
                .filter(rankMapper -> rank.isSameName(rankMapper.name()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("일치하는 등급이 없습니다."));
    }

    public String getRankName() {
        return rankName;
    }
}
